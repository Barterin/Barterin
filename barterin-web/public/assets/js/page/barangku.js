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
                console.log(element)
                html += `
                    <div class="card mt-3 item-card" data-id="${element.id}">
                        <div class="row g-0">
                            <div class="col-md-2">
                            <img src="${element.image[0]}" class="img-fluid rounded-start image-list" alt="...">
                            </div>
                            <div class="col-md-7">
                            <div class="card-body">
                                <h5 class="card-title" name="name">${element.name}</h5>
                                <p class="card-text" name="purchase_price">${element.purchase_price}</p>
                                <p class="card-text" name="description">${element.description}</p>
                                <p class="card-text" name="used_time"><small class="text-muted">${element.used_time}</small></p>
                            </div>
                            </div>
                        </div>
                    </div>
                `;
            });
            $(`#listBarang`).html(html);
            }
        },
        error: function (e) {
            console.log(e);
        },
    }).done(() => {
        $(".item-card").click(function (e) {
            const id = $(this).data("id");
            // alert(id);
            loadPage(`${baseUrl}/barang/donasi/${id}`);
        });
    });
})

