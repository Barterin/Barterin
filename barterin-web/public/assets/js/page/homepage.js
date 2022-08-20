// alert("jhgfvx")

function initSlick() {
    $(".barter-slider").slick({
        dots: false,
        infinite: true,
        speed: 300,
        arrows: true,
        slidesToShow: 8,
        slidesToScroll: 1,
        swipe: true,
        swipeToSlide: true, // You can unslick at a given breakpoint now by adding:
    });
}

function initSlickDonasi() {
    $(".donasi-slider").slick({
        dots: false,
        infinite: true,
        speed: 300,
        arrows: true,
        slidesToShow: 8,
        slidesToScroll: 1,
        swipe: true,
        swipeToSlide: true, // You can unslick at a given breakpoint now by adding:
    });
}

function initSlickCategory() {
    $(".category").slick({
        dots: false,
        infinite: true,
        speed: 300,
        arrows: true,
        slidesToShow: 2,
        slidesToScroll: 2,
        swipe: true,
        autoplaySpeed: 2000,
        autoplay: true,
        responsive: true,
        swipeToSlide: true, // You can unslick at a given breakpoint now by adding:
    });
}

$(document).ready(function () {
//     // GET Barter Item
//     $.ajax({
//         url: `${apiUrl}/public/items/barter`,
//         method: "get",
//         data: {
//             skip: 0,
//             take: 9,
//         },
//         dataType: "JSON",
//         success: function (e) {
//             if (e.statusCode == 200) {
//                 let html = "";
//                 const data = e.data;
//                 console.log(data);
//                 data.forEach((element) => {
//                     const image = element.item.image;
//                     const item = element.item;
//                     // console.log(image);
//                     html += `
//                         <div class="card donate-item-container donate-card item-card m-1 col-1" aria-hidden="true" style="width: 10rem; height: 20rem;" data-id="${item.id}">
//                             <img src="${image[0]}" class="card-img-top img img-fluid" alt="" style="width: 160px; height: 160px">
//                             <div class="card-body">
//                                 <h5 class="card-title placeholder-glow">
//                                     <span class="placeholder col-6 bg-dark"></span>
//                                     ${item.name}
//                                 </h5>
//                                 <p class="card-text placeholder-glow">
//                                     <i class="placeholder col-7 bi bi-geo-alt-fill"></i>
//                                     <span class="placeholder col-7">${item.address_city}, ${item.address_region}</span>
//                                 </p>
//                             </div>
//                         </div>
//                     `;

//                 });
//                 $(`#barterSliderItems`).html(html);
//             }
//         },
//     }).done(() => {
//         initSlick();
//         $(".barter-card").click(function (e) {
//             const id = $(this).data("id");
//             // alert(id);
//             loadPage(`${baseUrl}/barang/donasi/${id}`);
//         });
//         $(`#searchResult`).append(html);
//         }
//     },
//     error: function (e) {
//         console.log(e)
//     },
// });

    //GET Donation Item
    $.ajax({
        url: `${apiUrl}/public/items/donate`,
        method: "get",
        data: {
            skip: 0,
            take: 9,
        },
        dataType: "JSON",
        success: function (e) {
            if (e.statusCode == 200) {
                let html = "";
                const data = e.data;
                //console.log(data);
                data.forEach((element) => {
                    const image = element.item.image;
                    const item = element.item;
                    html += `
                        <div class="card donate-item-container donate-card item-card m-1 col-1" aria-hidden="true" style="width: 10rem; height: 20rem;" data-id="${item.id}">
                            <img src="${image[0]}" class="card-img-top img img-fluid" alt="" style="width: 160px; height: 160px">
                            <div class="card-body">
                                <h5 class="card-title placeholder-glow">
                                    <span class="placeholder col-6 bg-dark"></span>
                                    ${item.name}
                                </h5>
                                <p class="card-text placeholder-glow">
                                    <i class="placeholder col-7 bi bi-geo-alt-fill"></i>
                                    <span class="placeholder col-7">${item.address_city}, ${item.address_region}</span>
                                </p>
                            </div>
                        </div>
                    `;
                });
                $(`#donasiSliderItems`).html(html);
            }
        },
    }).done(() => {
        initSlickDonasi();
        $(".donate-card").click(function (e) {
            const id = $(this).data("id");
            // alert(id);
            loadPage(`${baseUrl}/barang/donasi/${id}`);
        });
    });

// GET NEAR YOU


    // ----------------- Init Slick Category ---------------//
    initSlickCategory();

    // ----------------- CATEGORY ---------------------//
    $("#electronic").click(function() {
        const search = "Elektronik"
        //alert(search)
        loadPage(`${baseUrl}/barang/search/${search}`);
    });
    $("#fashion").click(function() {
        const search = "Fashion"
        //alert(search)
        loadPage(`${baseUrl}/barang/search/${search}`);
    });
    $("#cooking").click(function() {
        const search = "Alat Memasak"
        //alert(search)
        loadPage(`${baseUrl}/barang/search/${search}`);
    });
});