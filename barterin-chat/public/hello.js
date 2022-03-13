$("#message").on('keypress', (e) => {
    if (e.keyCode == 13) {
        e.preventDefault()
        sendMessage()
    }
})