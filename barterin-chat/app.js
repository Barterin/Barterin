const express = require('express')
const app = express()
const server = require('http').Server(app)
const io = require('socket.io')(server)
const dotenv = require('dotenv');
dotenv.config();
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
        socket.join(userId[0])
        console.log("user-connected", userId);
        users.push(userId)

        io.emit('user-connected', userId)

        socket.on('disconnect', () => {
            io.emit('user-disconnected', userId)
            console.log("user disconnected", userId);
            users.splice(userId, 1);
        })

        socket.on('sendMessage', (data) => {
            io.to(data.receiver).emit('receiveMessage', { 
                sender: data.sender, 
                messageId: data.messageId,
                message: data.message, 
                date: data.date
            })
            io.to(data.sender).emit('messageSend', data.messageId)
            console.log("send message", data);
        })

        socket.on('userTyping', (data) => {
            io.to(data.receiver).emit('targetTyping', data)
        })

        socket.on('userStopTyping', (data) => {
            io.to(data.receiver).emit('targetStopTyping', data)
        })
    })
})


server.listen(process.env.APP_PORT)