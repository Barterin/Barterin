<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ChatBot</title>
    <link rel="stylesheet" href="style.css">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

    <div class="wrapper">
        <div class="title">ChatBot Barterin</div>
        <div class="form">
            <div class="bot-inbox inbox">
            <div class="icon">
                    <i class="fas fa-user"></i>
                </div>
                <div class="msg-header">
                    <p>Hai, ada yang bisa kami bantu?</p>
                    <p>Silahkan pilih kategori yang ingin ditanyakan</p>
                    <p>1. Barter</p>
                    <p>2. Donasi</p>
                    <p>3. Akun dan keamanan</p>
                </div>
            </div>
        </div>
        <div class="typing-field">
            <div class="input-data">
                <input id="data" type="text" placeholder="Ketikan nomor kategori/pertanyaan" required>
                <button id="send-btn">Kirim</button>
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function()
        {
            $("#send-btn").on("click", function()
            {
                $value = $("#data").val();
                $msg = '<div class="user-inbox inbox"><div class="msg-header"><p>'+ $value +'</p></div></div>';
                $(".form").append($msg);
                $("#data").val('');

                // start ajax code
                $.ajax({
                    url: 'message.php',
                    type: 'POST',
                    data: 'text='+$value,
                    success: function(result)
                    {
                        $replay = '<div class="bot-inbox inbox"><div class="icon"><i class="fas fa-user"></i></div><div class="msg-header"><p>'+ result +'</p></div></div>';
                        $(".form").append($replay);
                        // scroll bar akan otomatis kebawah mengikuti chat
                        $(".form").scrollTop($(".form")[0].scrollHeight);
                    }
                });
            });
        });
    </script>
</body>
</html>