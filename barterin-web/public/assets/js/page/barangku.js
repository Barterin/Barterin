$(document).ready(function () {
    //------------- GET BARANG LIST ---------------//
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
                if (element.item_for == 1){
                    html += `
                        <div class="card mt-3 item-card" data-id="${element.id}">
                            <div class="row g-0">
                                <div class="col-md-2">
                                <img src="${element.image[0]}" class="img-fluid rounded-start image-list" alt="...">
                                </div>
                                <div class="col-md-7">
                                    <div class="card-body">
                                        <h5 class="card-title" name="name">${element.name}</h5>
                                        <p class="card-text" name="purchase_price">Rp. ${element.purchase_price}</p>
                                        <p class="card-text" name="description">${element.description}</p>
                                        <p class="card-text" name="used_time"><small class="text-muted">${element.used_time}</small></p>
                                    </div>
                                </div>
                                <div class="col-md-3 row align-items-center">
                                    <div class="card-body col">
                                        <button class="btn btn-primary detailBarang" type="button" title="See Detail" data-id="${element.id}" ><i class="bi bi-eye-fill"></i></button>
                                        <button class="btn btn-warning editBarang" type="button" title="Edit" data-id="${element.id}"><i class="bi bi-pencil-square"></i></button>
                                        <button class="btn btn-danger deleteBarang" type="button" title="Delete" data-id="${element.id}"><i class="bi bi-trash-fill"></i></button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    `;
                }
            });
            $(`#listBarang`).html(html);
            }
        },
        error: function (e) {
            console.log(e);
        },
    }).done(() => {
        $(".detailBarang").click(function (e) {
            const id = $(this).data("id");
            loadPage(`${baseUrl}/barang/donasi/${id}`);
        });
        $(".editBarang").click(function (e) {
            const id = $(this).data("id");
            //alert(id);
            loadPage(`${baseUrl}/barang/editBarang/${id}`);
        });
        $(".deleteBarang").click(function (e) {    
            const id = $(this).data("id");    

            $.ajax({
                url: `${apiUrl}/member/items/delete/${id}`,
                method: "DELETE",
                headers: {
                    Authorization: `Bearer ${__access_token}`,
                },
                success: function (e) {
                    e.statusCode == 200 && msgSweetSuccess(e.message);
                    location.reload();
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
        });
    });
    

})

