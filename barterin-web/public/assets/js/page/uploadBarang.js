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
});
