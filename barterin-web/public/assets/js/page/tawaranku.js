$(document).ready(function () {
    //------------- GET BARANG LIST ---------------//
    $.ajax({
        url: `${apiUrl}/member/offer/list`,
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
                // console.log(element)
                if (element.barang.status == 0) {
                    html += `
                        <div class="card mt-3 item-card detailTawaran" data-id="${element.barang.id}">
                            <div class="row g-0">
                                <div class="col-md-2">
                                    <img src="${element.barang.image}" class="img-fluid rounded-start image-list" alt="...">
                                </div>
                                <div class="col-md-7">
                                <div class="card-body">
                                    <h5 class="card-title" name="name">${element.barang.name}</h5>
                                    <p class="card-text" name="purchase_price">${element.barang.user}</p>
                                    <p class="card-text" name="description"><small class="text-muted">${element.barang.region}</small></p>
                                </div>
                                </div>
                            </div>
                        </div>
                    `;
                    }
                });
                $(`#listTawaran`).html(html);
            }
        },
        error: function (e) {
            console.log(e);
        },
    }).done(() => {
        $(".detailTawaran").click(function (e) {
            const idBarang = $(this).data("id");
            // console.log($(this).data())
            // alert(idBarang);
            loadPage(`${baseUrl}/barang/tawaran/${idBarang}`);
        });
    });

    //GET TAWARAN LIST
    const idBarang = $("#idBarang").val();
    console.log(idBarang);
    $.ajax({
        url: `${apiUrl}/member/offer/list/bidder?itemId=${idBarang}`,
        method: "get",
        dataType: "JSON",
        headers: {
            Authorization: `Bearer ${__access_token}`,
        },
        success: function (e) {
            if (e.statusCode == 200) {
                const data = e.data;
                const keywords = e.keywords;
                let html = "";
            data.forEach((element) => {
                console.log(element)
                html += `
                <div class="card mt-3 item-card detailTawaran" data-id="${element.barang.id}">
                <input type="hidden" value="${element.id}" name="offerId">
                    <div class="row g-0 align-items-center">
                        <div class="col-md-2">
                            <img src="${element.barang.image}" class="img-fluid rounded-start image-list" alt="...">
                        </div>
                        <div class="col-md-7">
                            <div class="card-body">
                                <h5 class="card-title" name="name">${element.barang.name}</h5>
                                <p class="card-text" name="purchase_price">${element.barang.user}</p>
                                <p class="card-text" name="description"><small class="text-muted">${element.barang.region}</small></p>
                            </div>
                        </div>
                        <div class="col-md-3">
                            <div class="card-body">
                                <a href="http://localhost:6902/${__access_token}/${element.barang.user_id}" class="btn btn-primary" type="button" id="chatBidder" >Chat Penawar</a>
                                <button class="btn btn-primary mt-1 acceptOffer" type="submit">Terima Tawaran</button>
                            </div>
                        </div>
                    </div>
                </div>
                `;
                });
                $(`#detailTawaran`).append(html);
            }
        },
        error: function (e) {
            console.log(e);
        },
    });

    //ACCEPT OFFER
    $(document).on("click", ".acceptOffer", function (e) {
        e.preventDefault();
        const data = new FormData($(`#detailTawaran`).get(0));
        $.ajax({
            url: `${apiUrl}/member/offer/accept`,
            method: "post",
            timeout: 0,
            data: data,
            headers: {
                Authorization: `Bearer ${__access_token}`,
            },
            processData: false,
            contentType: false,
            dataType: "JSON",
            beforeSend: function () {
                disableButton();
            },
            complete: function () {
                enableButton();
            },
            success: function (e) {
                e.statusCode == 200 && msgSweetSuccess(e.message);
                window.location = `${baseUrl}/barang/tawaranku`;
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

function onlyUnique(value, index, self) {
    return self.indexOf(value) === index;
}

function inArray(needle, haystack) {
    var length = haystack.length;
    for (var i = 0; i < length; i++) {
        if (haystack[i] == needle) return true;
    }
    return false;
}
