$(document).ready(function (e) {
    // alert("hello");
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
                // console.log(data);
                $("#productName").html(data.item.name);
                $("#productImage").attr("src", data.item.image[0]);
                $("#user-name").html(data.user.name);
            }
        },
    }).done(() => {});
});
