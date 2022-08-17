const loadFile = function (url) {
    return new Promise((resolve, reject) => {
        const script = document.createElement("script");
        return (
            script.setAttribute("defer", ""),
            (script.src = url),
            script.addEventListener("load", function () {
                resolve(!0);
            }),
            !__loadedJs.includes(url) && document.head.appendChild(script),
            __loadedJs.push(url),
            1
        );
    });
};
let __loadedJs = [];
const loadJs = function (arrayJs) {
    return new Promise((resolve, reject) => {
        let total = arrayJs.length,
            current = 0;
        arrayJs.forEach(async function (url) {
            await loadFile(url), current++, total == current && resolve(!0);
        });
    });
};
let sc;

const segment = document.URL.split("/").slice(3);
const socketUrl = document
    .querySelector(`meta[name="socket-url"]`)
    .getAttribute("content");
const userId = segment[0];
let userIdTarget = segment[1];
let isTyping = false;
let isNotTyping;

function sendMessage() {
    let message = $("#message").val();
    const messageId = Date.now();
    let date = new Date();
    if (message.length == 0) return;
    let html = `
        <div class="message-in mx-3 my-2 p-2">
            <img src="/img/user2.jpg" class="img-responsive rounded-circle"
                style="height: 30px; width: 30px; object-fit: cover"> <b>${userId}</b> - ${date.toLocaleString(
        "en"
    )} <i id="${messageId}" class="fas fa-clock"></i> 
            <p>
                ${message}
            </p>
        </div>
    `;
    $(`#chatContent[data-id="${userIdTarget}"]`).append(html);
    document
        .getElementById("chatParent")
        .scrollTo(0, document.getElementById("chatParent").scrollHeight);
    sc.emit("sendMessage", {
        sender: userId,
        receiver: userIdTarget,
        messageId: messageId,
        message: message.trim(),
        date: date.toLocaleString("en"),
    });
    $("#message").val("");
}

function sendIsTypingToUser() {
    if (!isTyping) {
        sc.emit("userTyping", {
            sender: userId,
            receiver: userIdTarget,
        });
        isTyping = true;
    }
}

function sendIsNotTyping() {
    sc.emit("userStopTyping", {
        sender: userId,
        receiver: userIdTarget,
    });
    isTyping = false;
}

async function addUserToList(data) {
    const user = $(`div[data-id="${data.sender}"]`);
    if (user.length == 0) {
        let userList = `
            <div class="user-item rounded px-3 py-2 mx-2" data-id="${data.sender}">
                <img src="/img/user1.jpg" class="img-responsive rounded-circle me-2" alt="User Image">
                <span>${data.sender}</span>
            </div>
        `;
        $(`#userList`).append(userList);
        let chatParent = `
            <div class="d-none" id="chatContent" data-id="${data.sender}">
            </div>
            <span id="typingIndicator" data-id="${data.sender}" class="d-none"></span>
        `;
        $("#chatParent").append(chatParent);
    }
    $("#userList div").off("click");
    $("#userList div").on("click", function (e) {
        let userId = $(this).data("id");
        $(`#chatContent[data-id="${userIdTarget}"]`).addClass("d-none");
        $(`.user-item[data-id="${userIdTarget}"]`).removeClass("active");
        $(`#typingIndicator[data-id="${userIdTarget}"]`).addClass("d-none");
        userIdTarget = userId;
        $(`#typingIndicator[data-id="${userId}"]`).removeClass("d-none");
        $(`#chatContent[data-id="${userId}"]`).removeClass("d-none");
        $(`.user-item[data-id="${userId}"]`).addClass("active");
    });
}

loadJs([
    "https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js",
    "/plugins/jquery/jquery.min.js",
    `${socketUrl}/socket.io/socket.io.js`,
    "/autosize.min.js",
]).then(function () {
    sc = io(socketUrl);
    loadJs(["/hello.js"]);
    $(document).ready(function () {
        addUserToList({
            sender: userIdTarget,
        });
        $("#userId").html(userId);
        $("#userIdTarget").html(userIdTarget);
        sc.emit("connected", segment);
        $("#chatContent").removeClass("d-none");
        $("#chatContent").attr("data-id", userIdTarget);
        $("#message").on("keydown", function (e) {
            // e.preventDefault();
            console.log(e);
            // sendMessage();
        });

        $("#message").on("keypress", (e) => {
            if (isNotTyping != undefined) clearTimeout(isNotTyping);
            isNotTyping = setTimeout(sendIsNotTyping, 5000);
            if (e.keyCode == 13) {
                sendIsNotTyping();
                clearTimeout(isNotTyping);
                return;
            }
            sendIsTypingToUser();
        });

        sc.on("receiveMessage", async (data) => {
            await addUserToList(data);
            let html = `
                <div class="message-in mx-3 my-2 p-2">
                    <img src="/img/user2.jpg" class="img-responsive rounded-circle"
                        style="height: 30px; width: 30px; object-fit: cover"> <b>${data.sender}</b> - ${data.date} 
                    <p>
                        ${data.message}
                    </p>
                </div>
            `;
            $(`#chatContent[data-id="${data.sender}"]`).append(html);
            document
                .getElementById("chatParent")
                .scrollTo(
                    0,
                    document.getElementById("chatParent").scrollHeight
                );
        });

        sc.on("messageSend", (messageId) => {
            const message = $(`#${messageId}`);
            message.attr("class", "fas fa-check");
        });

        sc.on("targetTyping", (data) => {
            $(`#typingIndicator[data-id="${data.sender}"]`).text(
                `${data.sender} mengetik . . .`
            );
            $(`#typingIndicator[data-id="${data.sender}"]`).removeClass(
                "d-none"
            );
        });

        sc.on("targetStopTyping", (data) => {
            $(`#typingIndicator[data-id="${data.sender}"]`).text(``);
            $(`#typingIndicator[data-id="${data.sender}"]`).addClass("d-none");
        });

        autosize($("textarea"));
    });

    sc.on("user-disconnected", (userId) => {
        console.log("user disconnected", userId);
    });
});
