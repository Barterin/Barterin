$(".field-name").keyup(function () {

  if (this.value.length == this.maxLength) {
    $(this).next('.field-name').focus();
  }
  
});

$(`#formVerification`).submit(function(){
  var code1 = document.getElementById("code1").value;
  var code2 = document.getElementById("code2").value;
  var code3 = document.getElementById("code3").value;
  var code4 = document.getElementById("code4").value;
  var code5 = document.getElementById("code5").value;
  var code6 = document.getElementById("code6").value;
  var verify_code = document.getElementById(verify_code).value;

  verify_code = code1+code2+code3+code4+code5+code6;
});

var x = document.getElementById("code1").value;

//Posting Data
$(document).ready(function () {
  $(`#formVerification`).submit(e => {
      e.preventDefault()
      const data = new FormData($(`#formVerification`).get(0))
      $.ajax({
          url: `${apiUrl}/auth/verify-email`,
          method: 'post',
          data: data,
          processData: !1,
          contentType: !1,
          cache: !1,
          dataType: "JSON",
          beforeSend: function () {
              disableButton()
          },
          complete: function () {
              enableButton()
          },
          success: function (e) {
              e.statusCode == 200 && msgSweetSuccess(e.message).then(() => {
                  console.log(e.access.access_token);
              })
          },
          error: function (e) {
              const response = e.responseJSON
              if (response.statusCode == 500) 
                  msgSweetError(response.message, 1)
              if (response.statusCode == 401)
                  msgSweetWarning(response.message)
              validate(response.input)
              enableButton()
          }
      })
  })
})