$(document).ready(function () {
    $.ajax({
        url: `${apiUrl}/member/address/list`,
        method: "GET",
        headers: {
            Authorization: `Bearer ${__access_token}`,
        },
        dataType: "JSON",
        success: function (e) {
            if (e.statusCode == 200) {
                const selectAddress = $("#userAddress");
                const data = e.data;
                data.forEach((item) => {
                    selectAddress.append(
                        `<option value="${item.id}">${item.label} - ${item.alamat_lengkap}</option>`
                    );
                });
            }
        },
        error: function (e) {
            console.log(e);
        },
    });

    $.ajax({
        url: `${apiUrl}/public/category`,
        method: "GET",
        dataType: "JSON",
        success: function (e) {
            if (e.statusCode == 200) {
                const selectCategory = $("#itemsCategory");
                const data = e.data;
                data.forEach((item) => {
                    selectCategory.append(
                        `<option value="${item.id}">${item.name}</option>`
                    );
                });
            }
        },
        error: function (e) {
            console.log(e);
        },
    });

    $(`#itemsCategory`).change(function () {
        const idCategory = $(this).val();
        const selectType = $("#itemsType");
        selectType.html("");
        $.ajax({
            url: `${apiUrl}/public/type`,
            method: "GET",
            data: { categoryId: idCategory },
            dataType: "JSON",
            success: function (e) {
                if (e.statusCode == 200) {
                    const data = e.data;
                    data.forEach((item) => {
                        selectType.append(
                            `<option value="${item.id}">${item.name}</option>`
                        );
                    });
                }
            },
            error: function (e) {
                console.log(e);
            },
        });
    });

    //---------------- POST DATA ---------------//
    $(`#formUploadBarang`).submit((e) => {
        e.preventDefault();
        const data = new FormData($(`#formUploadBarang`).get(0));
        $.ajax({
            url: `${apiUrl}/member/items/store`,
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
                    if (response.statusCode == 400)
                    msgSweetWarning(response.message);
                validate(response.input);
                enableButton();
            },
        })
    })
});
