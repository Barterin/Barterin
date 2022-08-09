let table;

$(document).ready(function () {
    var table = $("#data").DataTable();

    $("#data tbody").on("click", "#detail", function () {
        var current_row = $(this).parents("tr");
        if (current_row.hasClass("child")) {
            current_row = current_row.prev();
        }
        var data = table.row(current_row).data();
        console.log(data);

        document.getElementById("id").value = data[0];
        document.getElementById("queries").value = data[1];
        document.getElementById("replies").value = data[2];

        $("#viewModal").modal("show");
    });
});

// $(".tombol-simpan").click(function () {
//     var data = $(".form-tambah").serialize();
//     $.ajax({
//         type: "POST",
//         url: "mEditRequest.php",
//         data: data,
//         success: function () {
//             alert("input data berhasil");
//         },
//         error: function (error) {
//             alert("terdapat kesalahan");
//         },
//     });
// });

$(`#formEdit`).submit(function (e) {
    e.preventDefault();
    $.ajax({
        type: "POST",
        url: "mEditRequest.php",
        data: new FormData(this),
        processData: false,
        contentType: false,
        cache: false,
        dataType: "JSON",
        success: function (e) {
            if (e.statusCode == 200) return alert(e.message);
            alert(e.message);
        },
        error: function (error) {
            alert("terdapat kesalahan");
        },
    });
});

function deleteRow($id) {
    var i = row.parentNode.parentNode.rowIndex;
    document.getElementById("POITable").deleteRow(i);
}

$("#data tbody #delPOIbutton").on("click", function (e) {
    const row = $(this);
    if (confirm(`Anda yakin ingin menghapus data ini ?`) == true) {
        const idData = $(this).data("id");
        // ajax post buat hapus
        $.ajax({
            type: "POST",
            url: "mHapusRequest.php",
            data: { id: idData },
            dataType: "JSON",
            success: function (data) {
                table.row(row.parents("tr")).remove().draw();
            },
            error: function (error) {
                alert("terdapat kesalahan");
            },
        });
    }
});
