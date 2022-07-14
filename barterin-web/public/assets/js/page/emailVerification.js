$(".form-control").keydown(function (e) {
    if (this.value.length == 1 && [37, 39, 8].indexOf(e.keyCode) == -1) {
        $(this).next(".form-control").focus();
    }
    if (e.keyCode == 39 && this.value.length == 0) {
        $(this).next(".form-control").focus();
    }
    if (e.keyCode == 37 && this.value.length == 0) {
        $(this).prev(".form-control").focus();
    }
});

//Allow only number
function setInputFilter(textbox, inputFilter, errMsg) {
    [
        "input",
        "keydown",
        "keyup",
        "mousedown",
        "mouseup",
        "select",
        "contextmenu",
        "drop",
        "focusout",
    ].forEach(function (event) {
        textbox.addEventListener(event, function (e) {
            if (inputFilter(this.value)) {
                // Accepted value
                if (["keydown", "mousedown", "focusout"].indexOf(e.type) >= 0) {
                    this.classList.remove("input-error");
                    this.setCustomValidity("");
                }
                this.oldValue = this.value;
                this.oldSelectionStart = this.selectionStart;
                this.oldSelectionEnd = this.selectionEnd;
            } else if (this.hasOwnProperty("oldValue")) {
                // Rejected value - restore the previous one
                this.classList.add("input-error");
                this.setCustomValidity(errMsg);
                this.reportValidity();
                this.value = this.oldValue;
                this.setSelectionRange(
                    this.oldSelectionStart,
                    this.oldSelectionEnd
                );
            } else {
                // Rejected value - nothing to restore
                this.value = "";
            }
        });
    });
}
setInputFilter(
    document.getElementById("uintTextBox"),
    function (value) {
        return /^\d*$/.test(value);
    },
    "Ketik hanya angka"
);

//Posting Data
$(document).ready(function () {
    $(`#formVerification`).submit((e) => {
        e.preventDefault();
        let codes = $(`input[name^="code"]`);
        let verifyCode = "";
        for (let i = 0; i < codes.length; i++) {
            const element = codes[i];
            verifyCode += $(element).val();
        }
        $.ajax({
            url: `${apiUrl}/auth/verify-email`,
            method: "post",
            headers: {
                Authorization: `Bearer ${__access_token}`,
            },
            data: { verify_code: verifyCode },
            dataType: "JSON",
            beforeSend: function () {
                disableButton();
            },
            complete: function () {
                enableButton();
            },
            success: function (e) {
                e.statusCode == 200 &&
                    msgSweetSuccess(e.message).then(() => {
                        console.log(e.access.access_token);
                    });
            },
            error: function (e) {
                const response = e.responseJSON;
                if (response.statusCode == 500)
                    msgSweetError(response.message, 1);
                if (response.statusCode == 401)
                    msgSweetWarning(response.message);
                enableButton();
            },
        });
    });
});
