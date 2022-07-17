$(document).ready(function() {
    // -------------- LOGOUT --------------- //
    $("#btnLogout").click((e) => {
        e.preventDefault();
        $.ajax({
            url: `${apiUrl}/auth/logout`,
            method: "post",
            headers: {
                Authorization: `Bearer ${__access_token}`,
            },
            dataType: "JSON",
            success: function (e) {
                e.statusCode == 200 && location.reload();
            },
            error: function (e) {
                const response = e.responseJSON;
                if (response.statusCode == 500)
                    msgSweetError(response.message, 1);
                if (response.statusCode == 401)
                    msgSweetWarning(response.message);
            },
        });
    });
})