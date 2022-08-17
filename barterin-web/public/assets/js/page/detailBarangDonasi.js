$(document).ready(function (e) {
    // alert("hello");

    //Get Barang
    const idBarang = $("#idBarang").val();
    console.log(idBarang)
    $.ajax({
        url: `${apiUrl}/public/items/donate`,
        method: "get",
        data: {
            itemsId: idBarang,
        },
        dataType: "JSON",
        success: function (e) {
            let html = "";
            if (e.statusCode == 200) {
                const data = e.data[0];
                //console.log(data);
                $("#productName").html(data.item.name);
                $("#productImage").attr("src", data.item.image[0]);
                $("#user-name").html(data.user.name);
                $("#productPrice").html(`Rp. ${data.item.purchase_price}`)
                $("#usedTime").html(`Waktu Penggunaan: ${data.item.used_time}`)
                $("#category").html(`Kategori ${data.category.name}`)
                $("#type").html(`Type: ${data.type.name}`)
                $("#description").html(`${data.item.description}`)
                html += `<button class="btn btn-outline-primary" id="chatbtn" data-id="${data.user.id}">Chat</button>`
                $(`#chat`).html(html);
            }
            
        },
    }).done(() => {
        $("#chatbtn").click(function(e){
            const idUser = $(this).data("id")
            //alert(idUser)
            window.location = `http://localhost:6902/${__access_token}/${idUser}`
        })
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
