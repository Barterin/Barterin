const loadFile = function (url) {
    return new Promise((resolve, reject) => {
        if (__loadedFiles.includes(url)) return;
        const format = url.split(".").pop();
        if ("js" == format) {
            const script = document.createElement("script");
            return script.setAttribute("defer", ""), script.src = url, script.addEventListener("load", (function () {
                const js = url.split("/").pop()
                console.log(js);
                // import { init } from `page/${js}`
                // init()
                resolve(!0)
            })), document.head.appendChild(script), __loadedFiles.push(url)
        }
        if ("css" == format) {
            const link = document.createElement("link");
            link.rel = "stylesheet", link.type = "text/css", link.href = url, link.addEventListener("load", (function () {
                return resolve(!0)
            })), document.head.appendChild(link), __loadedFiles.push(url)
        }
    })
};
let __loadedFiles = [],
    sc;
const loadFiles = function (arrayJs) {
    return new Promise((resolve, reject) => {
        let total = arrayJs.length,
            current = 0;
        arrayJs.forEach((async function (url) {
            await loadFile(url), current++, total == current && resolve(!0)
        }))
    })
};

let currentPage = location.href

function loadPage(url, change = false) {
	// if (url == currentPage && change == false) return typeof refreshData === 'function' && refreshData()
	if (url == currentPage && change == false) return 
	if (url == "#") return
	// nanobar.go(80)
	currentPage = url
	const e = $(`a.menu-item[href='${url.trim()}']`)
	change == false && window.history.pushState("", window.title, url)
	// $('a.menu-item').removeClass('active'), e.addClass('active')
	$.ajax({
		url: url,
		headers: { "Load-From-Ajax": true },
		success: function (data) {
			// try {
			// 	const dataJson = JSON.parse(data)
			// 	if (dataJson.status == '401') return msgSweetWarning("Sesi Anda berakhir !").then(msg => {
			// 		doLogoutAjax()
			// 	})
			// } catch (e) {
			// 	// return false;
			// }
			$("#contentId").html($(data).find('#contentId').html())
			$(".webTitle").html($(data).filter('title').text())
			// $("#rotiId").html($(data).find('#rotiId')).html()
			// $("#customJsNa").html($(data).filter('#customJsNa').html())
		}
	}).fail(function (err) {
		$("#contentId").html(`<div class="container">${err.statusText}</div>`)
		// nanobar.go(100)
		errorCode(err)
	}).done(function () {
		// nanobar.go(100)
	})
}

const socketUrl = document.querySelector(`meta[name="socket-url"]`).getAttribute('content')
const apiUrl = document.querySelector(`meta[name="api-url"]`).getAttribute('content')
const baseUrl = document.querySelector(`meta[name="base-url"]`).getAttribute('content')
const detectLoadJs=function(){const result=document.evaluate("//*[starts-with(name(),'loadjs-')]",document,null,XPathResult.ANY_TYPE,null);let nodes=[],anode=null;for(;anode=result.iterateNext();)nodes.push(anode);nodes.forEach((value,index)=>{loadFile(`/assets/js/page/${value.localName.split("-")[1]}.js`)})};

function testHello() {
    loadFiles([`${baseUrl}/assets/js/page/login.js`]).then(function() {
        init()
    })
}

loadFiles([
    `${baseUrl}/assets/bootstrap/css/bootstrap.css`,
    `${baseUrl}/assets/bootstrap/js/bootstrap.bundle.min.js`,
    `${baseUrl}/assets/plugins/jquery/jquery.min.js`,
    `${socketUrl}/socket.io/socket.io.js`,
    // `/autosize.min.js`,
]).then(function () {
    sc = io(socketUrl)
    detectLoadJs()
    $(document).ready(function () {
        
    })

    

})