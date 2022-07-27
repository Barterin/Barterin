$(document).ready(function() {
    // -------------- POST ALAMAT ------------- //
    $("#formAlamat").submit(e => {
        e.preventDefault()

        const data = new FormData($(`#formAlamat`).get(0))
        $.ajax({
            url: `${apiUrl}/member/address/store`,
            method: 'POST',
            timeout: 0,
            headers: {
                Authorization: `Bearer ${__access_token}`,
            },
            data: data,
            processData: false,
            contentType: false,
            mimeType: "multipart/form-data",
            dataType: "JSON",
            beforeSend: function () {
                disableButton()
            },
            complete: function () {
                enableButton()
            },
            success: function (e) {
                e.statusCode == 200 && msgSweetSuccess(e.message)
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

    // --------------- GET LIST ALAMAT ---------------- //
    
})