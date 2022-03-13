const express = require('express')
const app = express()
const server = require('http').Server(app)
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
    res.render('main3', {
        socketUrl: process.env.SOCKET_URL
    })
})

server.listen(process.env.APP_PORT)