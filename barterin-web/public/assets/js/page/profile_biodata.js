$(document).ready(function () {
    //------------- GET ID ---------------//
    const profileInput = document.getElementById("pictureInput")
    const profilePic = document.getElementById("picture")
    $.ajax({
        url: `${apiUrl}/auth/user-profile`,
        method: "post",
        headers: {
            Authorization: `Bearer ${__access_token}`,
        },
        dataType: "JSON",
        success: function (e) {
            // console.log(e);
            if (e.statusCode == 200) {
                const firstName = e.data.fullname.split(" ");
                $("#name-menu").prepend(`
                Hi, ${firstName[0]} / Biodata Diri
                `);
                // get image
                if (e.data.profile_picture != null){
                    profilePic.src = `${apiUrl}/uploads/images/profiles/${e.data.profile_picture}`
                }
                Object.keys(e.data).forEach((key) => {
                    // console.log(key);
                    $(`[name="${key}"]`).val(e.data[key])?.change();
                });
            }
        },
    });

    //---------------- POST PROFILE ---------------- //
    $("#userProfileForm").submit((e) => {
        e.preventDefault();

        const data = new FormData($(`#userProfileForm`).get(0));
        $.ajax({
            url: `${apiUrl}/member/user/update`,
            method: "POST",
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
                disableButton();
            },
            complete: function () {
                enableButton();
            },
            success: function (e) {
                e.statusCode == 200 && msgSweetSuccess(e.message);
            },
            error: function (e) {
                const response = e.responseJSON;
                if (response.statusCode == 500)
                    msgSweetError(response.message, 1);
                if (response.statusCode == 401)
                    msgSweetWarning(response.message);
                validate(response.input);
                enableButton();
            },
        });
    });

    //---------------- POST PROFILE PICTURE---------------- //
    $("#profilePic").submit((e) => {
        e.preventDefault();

        const data = new FormData($(`#profilePic`).get(0));
        $.ajax({
            url: `${apiUrl}/member/user/upload-photo`,
            method: "POST",
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
                disableButton();
            },
            complete: function () {
                enableButton();
            },
            success: function (e) {
                e.statusCode == 200 && msgSweetSuccess(e.message);
            },
            error: function (e) {
                const response = e.responseJSON;
                if (response.statusCode == 500)
                    msgSweetError(response.message, 1);
                if (response.statusCode == 401)
                    msgSweetWarning(response.message);
                validate(response.input);
                enableButton();
            },
        });
    });

    //---------------- CHANGE PROF. PIC ONCHANGE---------------- //
    profileInput.onchange = event => {
        const [file] = profileInput.files
        if (file) {
          profilePic.src = URL.createObjectURL(file)
        }
      }
});
