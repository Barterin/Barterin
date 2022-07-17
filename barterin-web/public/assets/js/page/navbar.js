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
                $("#login-section").html(`
                    <div class="btn dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown"
                        aria-expanded="false">
                        <img src="../../assets/image/profile.png" class="rounded-circle head-profile-picture">
                        <span class="fw-bold">${e.data.fullname}</span>
                    </div>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuClickable">
                        <li><a class="dropdown-item internal" href="${baseUrl}/profile/biodata">Profile</a></li>
                        <li><a class="dropdown-item" href="#">Setting</a></li>
                        <li><a class="dropdown-item" href="#">Transaksi</a></li>
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
                            e.statusCode == 200 && location.reload();
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
            console.log(e.message);
            if (response.statusCode == 401) {
                $("#login-section").html(`
                    <a href="${baseUrl}/auth/login" class="btn btn-primary">Login</a>
                `);
            }
        },
    });
});
