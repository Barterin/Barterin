const sc = io('/')
$(document).ready(function() {
    let segment = document.URL.split('/').slice(3);
    console.log(segment)
    let userId = segment[0]
    let userIdTarget = segment[1]
    $("#userId").html(userId);
    $("#userIdTarget").html(userIdTarget);
    sc.emit('connected', segment)

    $("#formStoreMessage").on('submit', function(e) {
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
        document.getElementById('chatContent').scrollTo(0,document.getElementById('chatContent').scrollHeight);
        sc.emit('sendMessage', {
            sender: userId,
            receiver: userIdTarget,
            messageId: messageId,
            message: message.trim(),
            date: date.toLocaleString('en')
        })
        $("#message").val('')
    })

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
        document.getElementById('chatContent').scrollTo(0,document.getElementById('chatContent').scrollHeight);
    })

    sc.on('messageSend', (messageId) => {
        const message = $(`#${messageId}`)
        message.attr('class', 'fas fa-check')
    })
})


sc.on('user-disconnected', userId => {
    console.log("user disconnected", userId);
})