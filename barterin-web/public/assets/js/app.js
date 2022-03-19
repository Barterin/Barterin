const loadFile=function(url){return new Promise((resolve,reject)=>{if(__loadedFiles.includes(url))return;const format=url.split(".").pop();if("js"==format){const script=document.createElement("script");return script.setAttribute("defer",""),script.src=url,script.addEventListener("load",(function(){resolve(!0)})),document.head.appendChild(script),__loadedFiles.push(url)}if("css"==format){const link=document.createElement("link");link.rel="stylesheet",link.type="text/css",link.href=url,link.addEventListener("load",(function(){return resolve(!0)})),document.head.appendChild(link),__loadedFiles.push(url)}})};let __loadedFiles=[],sc;const loadFiles=function(arrayJs){return new Promise((resolve,reject)=>{let total=arrayJs.length,current=0;arrayJs.forEach((async function(url){await loadFile(url),current++,total==current&&resolve(!0)}))})};

const socketUrl = document.querySelector(`meta[name="socket-url"]`).getAttribute('content')
const baseUrl = document.querySelector(`meta[name="base-url"]`).getAttribute('content')

loadFiles([
    `${baseUrl}/assets/bootstrap/css/bootstrap.css`,
    `${baseUrl}/assets/bootstrap/js/bootstrap.bundle.min.js`,
    `${baseUrl}/assets/plugins/jquery/jquery.min.js`,
    `${socketUrl}/socket.io/socket.io.js`,
    // `/autosize.min.js`,
]).then(function () {
    sc = io(socketUrl)
    $(document).ready(function () {
        
    })
})