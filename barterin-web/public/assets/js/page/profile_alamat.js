$(document).ready(function () {

    // -------------- POST ALAMAT ------------- //
    $("#formAlamat").submit((e) => {
        e.preventDefault();

        const data = new FormData($(`#formAlamat`).get(0));
        $.ajax({
            url: `${apiUrl}/member/address/store`,
            method: "POST",
            timeout: 0,
            headers: {
                Authorization: `Bearer ${__access_token}`,
            },
            data: data,
            processData: false,
            contentType: false,
            mimeType: "multipart/form-data",
            dataType: "JSON",
            beforeSend: function () {
                disableButton();
            },
            complete: function () {
                enableButton();
            },
            success: function (e) {
                e.statusCode == 200 && msgSweetSuccess(e.message);
            },
            error: function (e) {
                const response = e.responseJSON;
                if (response.statusCode == 500)
                    msgSweetError(response.message, 1);
                if (response.statusCode == 401)
                    msgSweetWarning(response.message);
                validate(response.input);
                enableButton();
            },
        });
    });

    // --------------- GET LIST ALAMAT ---------------- //

    $.ajax({
        url: `${apiUrl}/member/address/list`,
        method: "GET",
        headers: {
            Authorization: `Bearer ${__access_token}`,
        },
        dataType: "JSON",
        success: function (e) {
            if (e.statusCode == 200) {
                const data = e.data;
                let html = "";
                data.forEach((item) => {
                    html += `
                        <div class="row d-flex justify-content-between" style="margin-top: 34px;">
                            <div class="col-10">
                                <p style="margin-bottom: 10px; font-size: 14px; font-weight: bold;">${item.label} <span
                                        class="badge text-bg-primary"
                                        style="background-color: rgba(0, 52, 240, 0.2) !important; color: #0034F0 !important;">
                                        Utama </span> </p>
                                <p style="margin-bottom: 10px; font-size: 18px;">${item.penerima}</p>
                                <p style="margin-bottom: 10px; font-size: 16px;">${item.nohp}</p>
                                <p style="margin-bottom: 10px; font-size: 16px;">${item.alamat_lengkap}</p>
                                <!-- Button trigger modal for ALamat -->
                                <a href="#" class="text-decoration-none ubahAlamat" data-id="${item.id}">
                                    Ubah Alamat
                                </a>
                            </div>
                            <div class="col-2 m-auto d-flex justify-content-end">
                                <div class="btn btn-primary" style="background: #0034F0; ">Pilih</div>
                            </div>
                            <hr style="margin: 5px 0 ;">
                        </div>
                    `;
                });
                $(`#addressList`).html(html);
            }
        },
        error: function (e) {
            console.log(e);
        },
    }).done(function () {
        $(".ubahAlamat").click(function (e) {
            const id = $(this).data("id");
            alert(id);
        });
    });
});
