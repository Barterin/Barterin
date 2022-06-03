$(document).ready(function () {
    // alert('loaded') hello sas
    $(`#formRegister`).submit(e => {
        e.preventDefault()
        // testHello()
        // alert("hello")
        const data = new FormData($(`#formRegister`).get(0))
        $.ajax({
            url: `${apiUrl}/auth/register`,
            method: 'post',
            data: data,
            processData: !1,
            contentType: !1,
            cache: !1,
            dataType: "JSON",
            beforeSend: function () {
                disableButton()
            },
            complete: function () {
                enableButton()
            },
            success: function (e) {
                e.statusCode == 200 && msgSweetSuccess(e.message).then(() => {
                    window.location.href = `${baseUrl}/auth/login`
                })
            },
            error: function (e) {
                const response = e.responseJSON
                if (response.statusCode == 500) 
                    msgSweetError(response.message, 1)
                if (response.statusCode == 401)
                    msgSweetWarning(response.message)
                validate(response.input)
                enableButton()
            }
        })
    })
})