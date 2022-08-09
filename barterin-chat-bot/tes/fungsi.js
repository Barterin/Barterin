// $(document).ready(function() {
//     selesai();
// });

// function selesai() {
// 	setTimeout(function() {
// 		update();
// 		selesai();
// 	}, 200);
// }

// function update() {
// 	// alert(["hello"]);
// 	$(document).ready(function(){
// 	var app = {
// 	show: function(){
// 		$("tbody").html("")
// 		$.ajax({
// 			url: 'dataQueries.php',
// 			method : 'POST',
// 			data: {type: 'tampil'},
// 			success: function(response){
// 				var todos = JSON.parse(response);
// 				todos.forEach(function(value){
// 					$("tbody").append(`
// 					<tr>
// 						<th></th>
// 						<td>${value.queries}</td>
// 						<td>${value.replies}</td>
// 					</tr>`);
// 				})
// 			}
// 		})
// 	}
// 	}
// 	})
// }

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

$(`#formTambah`).submit(function (e) {
    e.preventDefault();
    $.ajax({
        type: "POST",
        url: "mSimpan.php",
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

// $(".tombol-edit").click(function () {
//     var data = $(".form-edit").serialize();
//     $.ajax({
//         type: "POST",
//         url: "mEdit.php",
//         data: data,
//         success: function (e) {
//             alert("Edit data berhasil");
//         },
//         error: function (error) {
//             alert("terdapat kesalahan");
//         },
//     });
// });

$(`#formInput`).submit(function (e) {
    e.preventDefault();
    $.ajax({
        type: "POST",
        url: "mEdit.php",
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
            url: "mHapus.php",
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
