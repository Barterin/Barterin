const socket = io('/')
$(document).ready(function() {
    let segment = document.URL.split('/').slice(3);
    console.log(segment)
    let userId = segment[0]
    $("#userId").html(userId.split('-')[0]);
    socket.emit('connected', segment)

    $("#formStoreMessage").on('submit', function(e) {
        e.preventDefault()
        let message = $("#message").val()
        if (message.length == 0) return
        socket.emit('sendMessage', segment, message.trim())
        $("#message").val('')
    })

    socket.on('receiveMessage', (userId, message, date) => {
        // if (userId != segment[1]) return
        // let html = `
        //     <div class="card p-2 d-flex flex-row align-items-center shadow">
        //         <div class="img-profile-user border rounded-circle ml-2 mr-3">
        //             <img src="/dist/img/AdminLTELogo.png" alt="">
        //         </div>
        //         <div class="d-flex flex-column">
        //             <div class="user">
        //                 <b>${userId}</b> - ${date}
        //             </div>
        //             <div class="message">
        //                 ${message}
        //             </div>
        //         </div>
        //     </div>
        // `
        let html = `
            <div class="message-in mx-3 my-2 p-2">
                <img src="/img/user2.jpg" class="img-responsive rounded-circle"
                    style="height: 30px; width: 30px; object-fit: cover"> <b>${userId}</b> - ${date}
                <p>
                    ${message}
                </p>
            </div>
        `
        $("#chatContent").append(html)
        document.getElementById('chatContent').scrollTo(0,document.getElementById('chatContent').scrollHeight);
    })

    $("#message").on('blur', function() {
        $(this).val($(this).val().replace(/[^@_.a-zA-Z0-9]/g, ''))
    })

    // socket.on('user-connected', userId => {
    //     console.log("user connected", userId);

    //     let html = `
    //         <li class="nav-item">
    //             <a href="">
    //                 <div class="user-panel pt-2 pb-2 d-flex listUser" data-username="user1" id="user-user1">
    //                     <div class="image"> <img src="http://chatapp.test/assets/img/user.png" class="img-circle elevation-2"
    //                         alt="User Image"> </div>
    //                     <div class="info"> <span class="d-block">${userId}</span> </div>
    //                 </div>
    //             </a>
    //         </li>
    //     `
    //     userId != segment[0] && $("#userList").append(html)
    // })
})


socket.on('user-disconnected', userId => {
    console.log("user disconnected", userId);
})