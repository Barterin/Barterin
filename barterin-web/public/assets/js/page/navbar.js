$(document).ready(function () {
    $.ajax({
        url: `${apiUrl}/auth/user-profile`,
        method: "post",
        headers: {
            Authorization: `Bearer ${__access_token}`,
        },
        dataType: "JSON",
        success: function (e) {
            if (e.statusCode == 200) {
                const firstName = e.data.fullname.split(" ");
                $("#login-section").html(`
                    <div class="btn dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown"
                        aria-expanded="false">
                        <img src="${
                            e.data.profile_picture == "-"
                                ? "../../assets/image/profile.png"
                                : `${apiUrl}/uploads/images/profiles/${e.data.profile_picture}`
                        }" alt="" class="rounded-circle head-profile-picture" />
                        <span class="nav-full-name">Hi, ${firstName[0]}</span>
                    </div>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuClickable">
                        <li><a class="dropdown-item" href="${baseUrl}/profile/biodata">Profile</a></li>
                        <li><a class="dropdown-item" href="${baseUrl}/barang/upload">Unggah Produk</a></li>
                        <li><a class="dropdown-item" href="${baseUrl}/barang/barangku">Barangku</a></li>
                        <li><a class="dropdown-item" href="${baseUrl}/barang/tawaranku">Daftar Tawaran</a></li>
                        <li><a class="dropdown-item" href="#" id="btnLogout">Logout</a></li>
                    </ul>
                `);

                // -------------- LOGOUT --------------- //
                $("#btnLogout").click((e) => {
                    e.preventDefault();
                    $.ajax({
                        url: `${apiUrl}/auth/logout`,
                        method: "post",
                        headers: {
                            Authorization: `Bearer ${__access_token}`,
                        },
                        dataType: "JSON",
                        success: function (e) {
                            e.statusCode == 200 && loadPage(baseUrl);
                        },
                        error: function (e) {
                            const response = e.responseJSON;
                            if (response.statusCode == 500)
                                msgSweetError(response.message, 1);
                            if (response.statusCode == 401)
                                msgSweetWarning(response.message);
                        },
                    });
                });
            }
        },
        error: function (e) {
            const response = e.responseJSON;
            if (response.statusCode == 401) {
                $("#login-section").html(`
                    <a href="${baseUrl}/auth/login" class="btn btn-primary">Login</a>
                `);
            }
        },
    }).done(function () {
        initahref();
    });
});
