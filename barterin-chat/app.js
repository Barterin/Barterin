const express = require("express");
const app = express();
const server = require("http").Server(app);
const dotenv = require("dotenv");
dotenv.config();
const { v4: uuidV4 } = require("uuid");

app.set("view engine", "ejs");
app.use(express.static("public"));

app.get("/(:token)", (req, res) => {
    res.render("main", {
        token: req.params.token,
        idTarget: "",
        socketUrl: process.env.SOCKET_URL,
        apiUrl: process.env.API_URL,
    });
});

app.get("/(:token)/(:idTarget)", (req, res) => {
    res.render("main", {
        token: req.params.token,
        idTarget: req.params.idTarget,
        socketUrl: process.env.SOCKET_URL,
        apiUrl: process.env.API_URL,
    });
});

server.listen(process.env.APP_PORT);
