const chatListContainer = document.getElementById("chat-list");
const chatContentContainer = document.getElementById("chat-content");
const chatFooterContainer = document.getElementById("chat-content-footer");
const chatContentBody = document.getElementById("chat-content-body");
const btnChatBack = document.getElementById("chat-btn-back");
const messageInput = document.getElementById("message-input");
const segment = document.URL.split("/").slice(3);
const socketUrl = document
    .querySelector(`meta[name="socket-url"]`)
    .getAttribute("content");
const apiUrl = document
    .querySelector(`meta[name="api-url"]`)
    .getAttribute("content");
const __access_token = document
    .querySelector(`meta[name="token"]`)
    .getAttribute("content");
const idTarget = document
    .querySelector(`meta[name="user-target"]`)
    .getAttribute("content");
const sc = io(socketUrl);
let isTyping = false;
let sender, target;

moment.locale("id");

onResize();
initChatItemClick();

function onResize() {
    const width = document.documentElement.clientWidth;
    // document.s
    if (width <= 912) {
        chatListContainer.classList.remove("active");
        chatContentContainer.classList.add("active");
        btnChatBack.classList.add("active");
    }

    if (width > 921) {
        chatListContainer.classList.add("active");
        chatContentContainer.classList.add("active");
        btnChatBack.classList.remove("active");
    }
}

function chatItemClick() {
    // if (screen.width <= 921) chatListContainer.classList.remove("active");

    // chatContentContainer.classList.add("active");
    // chatContentBody.scrollTo(0, chatContentBody.scrollHeight);
    const id = $(this).data("id");
    location.href = `/${__access_token}/${id}`;
}

function initChatItemClick() {
    const chatItem = document.getElementsByClassName("chat-item");
    Object.values(chatItem).forEach((element) => {
        element.removeEventListener("click", chatItemClick);
        element.addEventListener("click", chatItemClick);
    });
}

window.addEventListener("resize", onResize);

btnChatBack.addEventListener("click", function (e) {
    chatListContainer.classList.add("active");
    chatContentContainer.classList.remove("active");
    chatContentContainer.style.setProperty("display", "none", "!important");
});

messageInput.addEventListener("input", () => {
    const count = countLines(messageInput);
    if (count >= 2) {
        chatFooterContainer.style.setProperty(
            "height",
            `${70 + (15 * count - 1) > 220 ? 220 : 70 + (15 * count - 1)}px`
        );
        chatContentContainer.style.setProperty(
            "height",
            `calc(100vh - (${
                70 + (15 * count - 1) > 220 ? 220 : 70 + (15 * count - 1)
            }px))`
        );
    }
    if (count == 1) {
        chatFooterContainer.style.setProperty("height", `${70}px`);
        chatContentContainer.style.setProperty("height", `calc(100vh - 70px)`);
    }
});

function HtmlEncode(s) {
    var el = document.createElement("div");
    el.innerText = el.textContent = s;
    s = el.innerHTML;
    return s;
}
const _buffer = document.createElement("textarea");
function countLines(textarea) {
    // _buffer = document.createElement("textarea");
    _buffer.style.border = "none";
    _buffer.style.height = "0";
    _buffer.style.overflow = "hidden";
    _buffer.style.padding = "0";
    _buffer.style.position = "absolute";
    _buffer.style.left = "0";
    _buffer.style.top = "0";
    _buffer.style.zIndex = "-1";
    document.body.appendChild(_buffer);

    var cs = window.getComputedStyle(textarea);
    var pl = parseInt(cs.paddingLeft);
    var pr = parseInt(cs.paddingRight);
    var lh = parseInt(cs.lineHeight);

    // [cs.lineHeight] may return 'normal', which means line height = font size.
    if (isNaN(lh)) lh = parseInt(cs.fontSize);

    // Copy content width.
    _buffer.style.width = textarea.clientWidth - pl - pr + "px";

    // Copy text properties.
    _buffer.style.font = cs.font;
    _buffer.style.letterSpacing = cs.letterSpacing;
    _buffer.style.whiteSpace = cs.whiteSpace;
    _buffer.style.wordBreak = cs.wordBreak;
    _buffer.style.wordSpacing = cs.wordSpacing;
    _buffer.style.wordWrap = cs.wordWrap;

    // Copy value.
    _buffer.value = textarea.value;

    var result = Math.floor(_buffer.scrollHeight / lh);
    if (result == 0) result = 1;
    return result;
}

function sendMessage() {
    const message = messageInput.value;
    if (message.trim() == "") return;
    html = `
    <div class="chat-row message-out">
        <div class="chat-bubble">
            <p class="message-time">${moment().format("Do MMM YYYY, H:mm")}</p>
            <p class="message-content sender">
                ${HtmlEncode(message)
                    .replace(/\n/g, "<br>")
                    .replace(/ /g, "&nbsp")}
            </p>
        </div>
    </div>`;
    chatContentBody.innerHTML += html;
    chatContentBody.scrollTo(0, chatContentBody.scrollHeight);
    messageInput.value = "";
    chatFooterContainer.style.setProperty("height", `${70}px`);
    chatContentContainer.style.setProperty("height", `calc(100vh - 70px)`);
    sc.emit("sendMessage", {
        data: segment,
        user: [sender, target],
        apiUrl: apiUrl,
        message: message.trim(),
    });
}

messageInput.addEventListener("keydown", function (e) {
    // alert(e.key);
    if (!(e.key == "Undefined" || e.shiftKey) && e.key == "Enter") {
        e.preventDefault();
        sendMessage();
    }
});

$(document).ready(function () {
    $.ajax({
        url: `${apiUrl}/member/message/list`,
        method: "GET",
        timeout: 0,
        headers: {
            Authorization: `Bearer ${__access_token}`,
        },
        dataType: "JSON",
        success: function (response) {
            if (response.statusCode != 200) return;
            const data = response.data;
            const user = response.user;
            sc.emit("connected", { data: segment, user: user, apiUrl: apiUrl });
            sender = user.id;
            let html = ``;
            data.forEach((chat) => {
                html += `
                    <div class="chat-item d-flex align-items-center px-3 py-2 justify-content-between" data-id="${chat.user_id}">
                        <img
                            class="chat-profile-picture img img-fluid rounded-circle"
                            src="${chat.profile_picture}"
                        />
                        <div class="summary w-100 ms-3">
                            <p class="chat-name">${chat.name}</p>
                            <p class="chat-last-chat">${chat.last_message}</p>
                        </div>
                    </div>
                `;
            });
            $(`#chat-list`).html(html);
        },
    }).done(function () {
        initChatItemClick();
    });

    if (idTarget != "") {
        $.ajax({
            url: `${apiUrl}/member/message/get-from`,
            method: "GET",
            data: { id: idTarget },
            timeout: 0,
            headers: {
                Authorization: `Bearer ${__access_token}`,
            },
            dataType: "JSON",
            success: function (response) {
                if (response.statusCode != 200) return;
                const data = response.data;
                const user = response.user;
                target = user.id;
                $(`#name`).text(user.name);
                $(`#user-image`).attr("src", user.profile_picture);
                let html = ``;
                data.forEach((chat) => {
                    if (chat.direction == "out") {
                        html += `
                            <div class="chat-row message-out">
                                <div class="chat-bubble">
                                    <p class="message-time">${moment(
                                        chat.time
                                    ).format("Do MMM YYYY, H:mm")}</p>
                                    <p class="message-content sender">
                                        ${HtmlEncode(chat.message)
                                            .replace(/\n/g, "<br>")
                                            .replace(/ /g, "&nbsp")}
                                    </p>
                                </div>
                            </div>
                        `;
                    } else {
                        html += `
                            <div class="chat-row">
                                <div class="chat-bubble message-in align-items-start">
                                    <p class="message-time">${moment(
                                        chat.time
                                    ).format("Do MMM YYYY, H:mm")}</p>
                                    <p class="message-content">
                                        ${HtmlEncode(chat.message)
                                            .replace(/\n/g, "<br>")
                                            .replace(/ /g, "&nbsp")}
                                    </p>
                                </div>
                            </div>
                        `;
                    }
                });
                $(`#chat-content-body`).html(html);
                chatContentBody.scrollTo(0, chatContentBody.scrollHeight);
            },
        });
    }

    sc.on("receiveMessage", (data) => {
        if (data.sender != target) return;
        console.log(data);
        $(`#chat-content-body`).append(`
            <div class="chat-row">
                <div class="chat-bubble message-in align-items-start">
                    <p class="message-time">${moment(data.date).format(
                        "Do MMM YYYY, H:mm"
                    )}</p>
                    <p class="message-content">
                        ${HtmlEncode(data.message)
                            .replace(/\n/g, "<br>")
                            .replace(/ /g, "&nbsp")}
                    </p>
                </div>
            </div>
        `);
        chatContentBody.scrollTo(0, chatContentBody.scrollHeight);
    });
});
