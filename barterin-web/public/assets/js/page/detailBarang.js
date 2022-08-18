$(document).ready(function (e) {
    // alert("hello");

    //Get Barang
    const idBarang = $("#idBarang").val();
    console.log(idBarang)
    $.ajax({
        url: `${apiUrl}/public/items/barter`,
        method: "get",
        data: {
            itemsId: idBarang,
        },
        dataType: "JSON",
        success: function (e) {
            let html = "";
            if (e.statusCode == 200) {
                const data = e.data[0];
                console.log(data);
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

    //Get Barangku
    $.ajax({
        url: `${apiUrl}/member/items/list`,
        method: "GET",
        headers: {
            Authorization: `Bearer ${__access_token}`,
        },
        dataType: "JSON",
        success: function (e) {
            if (e.statusCode == 200) {
                const data = e.data;
                let html = "";
                data.forEach((element) => {
                    console.log(element)
                    if (element.item_for == 0 && element.status == 0){
                        html += `
                        <div class="wrapper mt-1">
                            <label class="option row align-items-center">
                                <input type="radio" value="${element.id}" name="with_item_id" />
                                <img src="${element.image[0]}" class="col m-1" style="max-width:100px; margin-right:100px;" />
                                <figcaption class="col" id="caption"><b>${element.name}</b>
                                <br><a>Rp.${element.purchase_price}</a></figcaption>
                            </label>
                        </div>
                        `;
                    }
                });
                $(`#barterItems`).html(html);
            }
        },
        error: function (e) {
            console.log(e);
        },
    })


    //POST TAWARAN
    $(`#formTawaranBarter`).submit((e) => {
        e.preventDefault();
        const data = new FormData($(`#formTawaranBarter`).get(0));
        $.ajax({
            url: `${apiUrl}/member/offer/store`,
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
})