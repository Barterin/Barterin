const loadFile = function (url, keep = false) {
    return new Promise((resolve, reject) => {
        const format = url.split(".").pop();
        if ("js" == format) {
            const script = document.createElement("script");
            return script.setAttribute("defer", ""), keep == false && script.setAttribute("temp", ""), script.src = `${url}${keep == false ? `?${+ new Date()}` : ''}`, script.addEventListener("load", (function () {
                resolve(!0)
            })), document.head.appendChild(script)
        }
        if ("css" == format) {
            const link = document.createElement("link");
            link.rel = "stylesheet", keep == false && link.setAttribute("temp", ""), link.type = "text/css", link.href = url, link.addEventListener("load", (function () {
                return resolve(!0)
            })), document.head.appendChild(link)
        }
    })
};
let sc, nanobar, __access_token
const loadFiles = function (arrayJs, keep = false) {
    return new Promise((resolve, reject) => {
        let total = arrayJs.length,
            current = 0;
        arrayJs.forEach((async function (url) {
            await loadFile(url, keep), current++, total == current && resolve(!0)
        }))
    })
};
const redirect = function(url) {
	window.location.replace(url)
}
let currentPage = location.href

const socketUrl = document.querySelector(`meta[name="socket-url"]`).getAttribute('content')
const apiUrl = document.querySelector(`meta[name="api-url"]`).getAttribute('content')
const baseUrl = document.querySelector(`meta[name="base-url"]`).getAttribute('content')
const environmentStatus = document.querySelector(`meta[name="environment"]`).getAttribute('content')
__access_token = document.querySelector(`meta[name="access-token"]`).getAttribute('content')

const detectLoadJs = function (source = document) {
    const tempScript = document.querySelectorAll('script[temp]')
    tempScript.forEach((element, index) => {
        element.remove()
    })
    const result = source.evaluate("//*[starts-with(name(),'loadjs-')]", source, null, XPathResult.ANY_TYPE, null);
    let nodes = [],
        anode = null;
    for (; anode = result.iterateNext();) nodes.push(anode);
    nodes.forEach((value, index) => {
        // console.log(value)
        loadFile(`/assets/js/page/${value.localName.split("-")[1]}${environmentStatus == 'production' ? '.min' : ''}.js`)
        value.remove()
    })
};
const detectLoadCSS = function (source = document) {
	const tempScript = document.querySelectorAll('link[temp]')
    tempScript.forEach((element, index) => {
        element.remove()
    })
    const result = source.evaluate("//*[starts-with(name(),'loadcss-')]", source, null, XPathResult.ANY_TYPE, null);
    let nodes = [],
        anode = null;
    for (; anode = result.iterateNext();) nodes.push(anode);
    nodes.forEach((value, index) => {
        // console.log(value)
        loadFile(`/assets/css/page/${value.localName.split("-")[1]}${environmentStatus == 'production' ? '.min' : ''}.css`)
        value.remove()
    })
};

const loadPage = function(url, change = false) {
	if (url == currentPage && change == false) return 
	if (url == "#") return
	nanobar.go(80)
	currentPage = url
	const e = $(`a.menu-item[href='${url.trim()}']`)
	change == false && window.history.pushState("", window.title, url)
	// $('a.menu-item').removeClass('active'), e.addClass('active')
	$.ajax({
		url: url,
		headers: { "Load-From-Ajax": true },
		success: function (data) {
			$("main").html($(data).filter('main').html())
			$(".webTitle").html($(data).filter('title').text())
		}
	}).fail(function (err) {
		$("#contentId").html(`<div class="container">${err.statusText}</div>`)
		nanobar.go(100)
		// errorCode(err)
	}).done(function () {
		detectLoadJs()
		detectLoadCSS()
		initahref()
		nanobar.go(100)
	})
}

const disableButton = function() {
	$(":submit").append(' <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>'), $(":submit").attr("disabled", !0)
	// $(":submit").attr("disabled", !0)
}

const enableButton = function() {
	$(":submit").find("span").remove(), $(":submit").removeAttr("disabled")
}

const msgSweetError = function(pesan, options = {
	title: "Error",
	timer: 3000
}) {
	return Swal.fire({
		icon: "error",
		html: pesan,
		title: options.title,
		timer: options.timer,
		timerProgressBar: !0
	})
}

const msgSweetSuccess = function(pesan, options = {
	title: "Sukses",
	timer: 3000
}) {
	return Swal.fire({
		icon: "success",
		html: pesan,
		title: options.title,
		timer: options.timer,
		timerProgressBar: !0
	})
}

const msgSweetWarning = function(pesan, options = {
	title: "Peringatan",
	timer: 3000
}) {
	return Swal.fire({
		icon: "warning",
		html: pesan,
		title: options.title,
		timer: options.timer,
		timerProgressBar: !0
	})
}

const msgSweetInfo = function(pesan, options = {
	title: "Informasi",
	timer: 3000
}) {
	return Swal.fire({
		icon: "info",
		html: pesan,
		title: options.title,
		timer: options.timer,
		timerProgressBar: !0
	})
}

const confirmSweet = function(pesan, options = {
	title: "Konfirmasi",
	confirmBtn: "Ya",
	cancelBtn: "Tidak"
}) {
	return Swal.fire({
		icon: "warning",
		title: options.title,
		html: pesan,
		showCancelButton: !0,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: options.confirmBtn,
		cancelButtonText: options.cancelBtn
	})
}

const isConfirmed = function(sweetResult) {
	return sweetResult.isConfirmed == true ? true : false
}

const validate = function(data) {
    $(`input[class*='validate']`).removeClass('is-invalid validate')
    Object.keys(data).forEach(element => {
        $(`input[name="${element}"]`).addClass('is-invalid validate')
        $(`[id='validate_${element}']`).addClass("invalid-feedback")
        $(`[id='validate_${element}']`).html(data[element][0])
    });
    $(`[name="${Object.keys(data)[0]}"].is-invalid`).select()
}

const initahref = function() {
	$(`a.internal`).unbind().click(function(e) {
		e.preventDefault()
		let url = $(this).attr('href')
		loadPage(url)
    })
}

loadFiles([
    `${baseUrl}/assets/bootstrap/css/bootstrap.css`,
    `${baseUrl}/assets/bootstrap/js/bootstrap.bundle.min.js`,
    `${baseUrl}/assets/plugins/jquery/jquery.min.js`,
    `${baseUrl}/assets/plugins/sweetalert2/sweetalert2.css`,
    `${baseUrl}/assets/css/style.css`,
    `${baseUrl}/assets/plugins/sweetalert2/sweetalert2.min.js`,
    `${baseUrl}/assets/plugins/nanobar/nanobar.min.js`,
    `${socketUrl}/socket.io/socket.io.js`,
    // `/autosize.min.js`,
], true).then(function () {
	nanobar = new Nanobar({
		classname: "loadingGan",
	})
    sc = io(socketUrl)
	detectLoadJs()
	initahref()
	$(window).on('popstate', function(e){
		if (currentPage.replace(/#/g, '') != location.href.replace(/#/g, '')) (currentPage = location.href, loadPage(currentPage, true))
	});
})
detectLoadCSS()