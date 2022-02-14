const sc = io('/')
$(document).ready(function () {
    const segment = document.URL.split('/').slice(3);
    const userId = segment[0]
    const userIdTarget = segment[1]
    let isTyping = false;
    let isNotTyping
    $("#userId").html(userId);
    $("#userIdTarget").html(userIdTarget);
    sc.emit('connected', segment)
    $("#formStoreMessage").on('submit', function (e) {
        e.preventDefault()
        let message = $("#message").val()
        const messageId = Date.now()
        let date = new Date()
        if (message.length == 0) return
        let html = `
            <div class="message-in mx-3 my-2 p-2">
                <img src="/img/user2.jpg" class="img-responsive rounded-circle"
                    style="height: 30px; width: 30px; object-fit: cover"> <b>${userId}</b> - ${ date.toLocaleString('en') } <i
                        id="${messageId}" class="fas fa-clock"></i> 
                <p>
                    ${message}
                </p>
            </div>
        `
        $("#chatContent").append(html)
        document.getElementById('chatContent').scrollTo(0, document.getElementById('chatContent').scrollHeight);
        sc.emit('sendMessage', {
            sender: userId,
            receiver: userIdTarget,
            messageId: messageId,
            message: message.trim(),
            date: date.toLocaleString('en')
        })
        $("#message").val('')
    })

    $("#message").on('keypress', (e) => {
        if (isNotTyping != undefined) clearTimeout(isNotTyping);
        isNotTyping = setTimeout(sendIsNotTyping, 5000);
        if (e.keyCode == 13) {
            sendIsNotTyping()
            clearTimeout(isNotTyping)
            return
        }
        sendIsTypingToUser()
    })

    function sendIsTypingToUser() {
        if (!isTyping) {
            sc.emit('userTyping', {sender: userId, receiver: userIdTarget})
            isTyping = true
        }
    }

    function sendIsNotTyping() {
        sc.emit('userStopTyping', {sender: userId, receiver: userIdTarget})
        isTyping = false
    }

    sc.on('receiveMessage', (data) => {
        let html = `
            <div class="message-in mx-3 my-2 p-2">
                <img src="/img/user2.jpg" class="img-responsive rounded-circle"
                    style="height: 30px; width: 30px; object-fit: cover"> <b>${data.sender}</b> - ${data.date} 
                <p>
                    ${data.message}
                </p>
            </div>
        `
        $("#chatContent").append(html)
        document.getElementById('chatContent').scrollTo(0, document.getElementById('chatContent').scrollHeight);
    })

    sc.on('messageSend', (messageId) => {
        const message = $(`#${messageId}`)
        message.attr('class', 'fas fa-check')
    })

    sc.on('targetTyping', (data) => {
        if (data.sender != userIdTarget) return
        $("#typingIndicator").text(`${data.sender} mengetik . . .`)
        $("#typingIndicator").removeClass('d-none')
    })

    sc.on('targetStopTyping', (data) => {
        if (data.sender != userIdTarget) return
        $("#typingIndicator").text(``)
        $("#typingIndicator").addClass('d-none')
    })
})


sc.on('user-disconnected', userId => {
    console.log("user disconnected", userId);
})