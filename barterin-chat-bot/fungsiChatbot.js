$(document).ready(function () {
    $("#formInput").submit((e) => {
        e.preventDefault();
        // alert("hello")
        // return
        $value = $("#data").val();
        $msg =
            '<div class="row justify-content-center"><div class="col-5"><hr></div><div class="col-2 text-center">Hasil dari pilihan ' +
            $value +
            '</div><div class="col-5"><hr></div></div>';
        $(".form-user").append($msg);
        $("#data").val("");

        // start ajax code
        $.ajax({
            url: "message.php",
            type: "POST",
            data: "text=" + $value,
            success: function (result) {
                $replay = '<div class="col-md-8"><p>' + result + "</p></div>";
                $(".form-user").append($replay);
                // scroll bar akan otomatis kebawah mengikuti chat
                $(".form-user").scrollTop($(".form-user")[0].scrollHeight);
            },
        });
    });
});
