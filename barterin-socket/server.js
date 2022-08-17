const express = require("express");
const app = express();
const server = require("http").Server(app);
const request = require("request");
const io = require("socket.io")(server, {
    cors: {
        origin: "*",
    },
});
const dotenv = require("dotenv");
dotenv.config();
app.get("/", (req, res) => {
    res.send(":)");
});

let users = [];

io.on("connection", (socket) => {
    socket.on("connected", (data) => {
        console.log("user-connected", data.user);
        const userId = data.data;
        socket.join(data.user.id);
        // users.push(data);
        let date = +new Date();

        request(
            {
                method: "POST",
                url: `${data.apiUrl}/auth/set-status`,
                headers: {
                    Authorization: `Bearer ${userId[0]}`,
                },
                formData: {
                    status: "1",
                },
            },
            function (error, response) {
                if (error) throw new Error(error);
                console.log(response.body);
            }
        );

        io.emit("user-connected", userId);

        socket.on("disconnect", () => {
            io.emit("user-disconnected", userId);
            console.log("user disconnected", userId);
            users.splice(userId, 1);
            request(
                {
                    method: "POST",
                    url: `${data.apiUrl}/auth/set-status`,
                    headers: {
                        Authorization: `Bearer ${userId[0]}`,
                    },
                    formData: {
                        status: "0",
                    },
                },
                function (error, response) {
                    if (error) throw new Error(error);
                    console.log(response.body);
                }
            );
        });

        socket.on("sendMessage", (data) => {
            io.to(data.user[1]).emit("receiveMessage", {
                sender: data.user[0],
                message: data.message,
                date: date,
            });
            // io.to(data.sender).emit("messageSend", data.messageId);
            console.log("send message", data);
            request(
                {
                    method: "POST",
                    url: `${data.apiUrl}/member/message/store`,
                    headers: {
                        Authorization: `Bearer ${userId[0]}`,
                    },
                    formData: {
                        target_id: userId[1],
                        message: data.message,
                    },
                },
                function (error, response) {
                    if (error) throw new Error(error);
                    console.log(response.body);
                }
            );
        });

        socket.on("userTyping", (data) => {
            io.to(data.receiver).emit("targetTyping", data);
        });

        socket.on("userStopTyping", (data) => {
            io.to(data.receiver).emit("targetStopTyping", data);
        });
    });
});

server.listen(process.env.APP_PORT);
