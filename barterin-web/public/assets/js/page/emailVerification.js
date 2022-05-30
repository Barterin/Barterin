$(".field-name").keyup(function () {

    if (this.value.length == this.maxLength) {
        $(this).next('.field-name').focus();
    }

});

//Posting Data
$(document).ready(function () {
    $(`#formVerification`).submit(e => {
        e.preventDefault()
        let codes = $(`input[id^="code"]`)
        let verifyCode = ''
        for (let i = 0; i < codes.length; i++) {
            const element = codes[i];
            verifyCode += $(element).val()
        }
        $.ajax({
            url: `${apiUrl}/auth/verify-email`,
            method: 'post',
            headers: {
                Authorization: `Bearer ${__access_token}`
            },
            data: {verify_code : verifyCode},
            dataType: "JSON",
            beforeSend: function () {
                disableButton()
            },
            complete: function () {
                enableButton()
            },
            success: function (e) {
                e.statusCode == 200 && msgSweetSuccess(e.message).then(() => {
                    console.log(e.access.access_token);
                })
            },
            error: function (e) {
                const response = e.responseJSON
                if (response.statusCode == 500)
                    msgSweetError(response.message, 1)
                if (response.statusCode == 401)
                    msgSweetWarning(response.message)
                enableButton()
            }
        })
    })
})