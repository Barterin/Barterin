$(document).ready(function () {
//GET TAWARAN LIST
const searchRes = $("#searchRes").val();
console.log(searchRes);
$.ajax({
    url: `${apiUrl}/public/items/donate?search=${searchRes}`,
    method: "get",
    dataType: "JSON",
    headers: {
        Authorization: `Bearer ${__access_token}`,
    },
    success: function (e) {
        if (e.statusCode == 200) {
            const data = e.data;
            let html = "";
        data.forEach((element) => {
            console.log(element)
            html += `
                <div class="col-sm-2">
                    <div class="card donate-item-container donate-card item-card m-1 col-1" aria-hidden="true" style="width: 10rem; height: 20rem;" data-id="${element.item.id}">
                        <img src="${element.item.image[0]}" class="card-img-top img img-fluid" alt="" style="width: 160px; height: 160px">
                        <div class="card-body">
                            <h5 class="card-title placeholder-glow">
                                <span class="placeholder col-6 bg-dark"></span>
                                ${element.item.name}
                            </h5>
                            <p class="card-text placeholder-glow">
                                <i class="placeholder col-7 bi bi-geo-alt-fill"></i>
                                <span class="placeholder col-7">${element.item.address_city}, ${element.item.address_region}</span>
                            </p>
                        </div>
                    </div>
                </div>
            `;
        });
        $(`#searchResult`).append(html);
        }
    },
    error: function (e) {
        console.log(e)
    },
});
})