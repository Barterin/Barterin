function init() {
    $(document).ready(function() {
        // alert('loaded')
        $(`#formLogin`).submit(e => {
            e.preventDefault()
            // testHello()
            // alert("hello")
            const data = new FormData($(`#formLogin`).get(0))
            $.ajax({
                url: `${apiUrl}/auth/login`,
                method: 'post',
                data: data,
                processData: !1,
                contentType: !1,
                cache: !1,
                dataType: "JSON",
                beforeSend: function () {
                    // disableButton()
                },
                complete: function () {
                    // enableButton()
                },
                success: function (e) {
                    // validate(e.validate.input), e.validate.success && ("ok" == e.status ? msgSweetSuccess(e.message).then(() => {
                    //     redirect(ADMIN_PATH)
                    // }) : msgSweetWarning(e.message).then(() => {
                    //     $("input[name='username']").select()
                    // }))
                },
            })
        }) 
    })
}

init()