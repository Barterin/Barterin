maxLength = 1;
$(".field-name").keyup(function () {
    if (this.value.length == this.maxLength) {
      $(this).next('.field-name').focus();
    }
  });