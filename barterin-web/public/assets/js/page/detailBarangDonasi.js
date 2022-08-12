$(document).ready(function (e) {
    // alert("hello");

    //Get Barang
    const idBarang = $("#idBarang").val();
    $.ajax({
        url: `${apiUrl}/public/items/donate`,
        method: "get",
        data: {
            itemsId: idBarang,
        },
        dataType: "JSON",
        success: function (e) {
            if (e.statusCode == 200) {
                const data = e.data[0];
                $("#productName").html(data.item.name);
                $("#productImage").attr("src", data.item.image[0]);
                $("#user-name").html(data.user.name);
                $("#productPrice").html(`Rp. ${data.item.purchase_price}`)
                $("#usedTime").html(`Waktu Penggunaan: ${data.item.used_time}`)
                $("#category").html(`Kategori ${data.category.name}`)
                $("#type").html(`Type: ${data.type.name}`)
                $("#description").html(`${data.item.description}`)
            }
        },
    });

    

    //Post Tawaran
    $(`#formTawaran`).submit((e) => {
        e.preventDefault();
        const data = new FormData($(`#formTawaran`).get(0));
        $.ajax({
            url: `${apiUrl}/member/offer-donate/store`,
            method: "post",
            timeout: 0,
            data: data,
            processData: false,
            contentType: false,
            dataType: "JSON",
            headers: {
                Authorization: `Bearer ${__access_token}`,
            },
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
        });
    });
});
