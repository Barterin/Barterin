$(document).ready(function() {
    //------------- GET ID ---------------//
    var name = document.getElementById("fullname");
    var bornYear = document.getElementById("born");
    var gender = document.getElementById("gender");
    var phone = document.getElementById("phone"); 
    $.ajax({
        url: `${apiUrl}/auth/user-profile`,
        method: "post",
        headers: {
            Authorization: `Bearer ${__access_token}`,
        },
        dataType: "JSON",
        success: function (e) {
            console.log(e);
            if (e.statusCode == 200) {
                const firstName = e.data.fullname.split(" ");
                $("#name-menu").html(`
                Hi, ${firstName[0]} / Biodata Diri
                `);
                // get image
                $("#profile-picture,#profile-picture-preview").html(`
                    <img src="${e.data.profile_picture == "-" ? "../../assets/image/profile.png" : `${apiUrl}/uploads/images/profiles/${e.data.profile_picture}`}" alt="" class="rounded-circle profile-picture" />
                `);
                
                name.value = e.data.fullname;
                bornYear.value = e.data.born;
                gender.value = e.data.gender;
                phone.value = e.data.phone;                
            }
        }
    })
    //---------------- POST PROFILE ---------------- //
    $.ajax({
        
    })
})