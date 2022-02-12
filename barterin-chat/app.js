const express = require('express')
const app = express()
const server = require('http').Server(app)
const io = require('socket.io')(server)
const {
    v4: uuidV4
} = require('uuid')

app.set('view engine', 'ejs')
app.use(express.static('public'))

app.get('/', (req, res) => {
    res.send(":)")
})

app.get('/(:id)/(:idTarget)', (req, res) => {
    res.render('main3')
})

let users = []

io.on('connection', socket => {
    socket.on('connected', (userId) => {
        // socket.join(roomId)
        // socket.to(roomId).emit('user-connected', userId)

        socket.join(userId[0])
        socket.join(userId[1])

        console.log("user-connected", userId);
        users.push(userId)

        io.emit('user-connected', userId)

        socket.on('disconnect', () => {
            io.emit('user-disconnected', userId)
            console.log("user disconnected", userId);
            users.splice(userId, 1);
        })

        socket.on('sendMessage', (users, message) => {
            let date = new Date()
            io.to(users[0]).to(users[1]).emit('receiveMessage', users[0], message, date.toLocaleString('en'))
            console.log("send message", users, message);
        })
    })
})


server.listen(6901)