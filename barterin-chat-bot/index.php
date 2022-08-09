<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Chatbot Barterin</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='assets\css\bootstrap.min.css'>
    <script defer src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script defer src='assets\js\bootstrap.min.js'></script>
    <script type="text/javascript" defer src="fungsiChatbot.js"></script>

    <style>
        .sticky-footer {
            /* position: sticky; */
            top: 100%;
        }

        ::-webkit-scrollbar {
            width: 5px;
            border-radius: 5px;
        }

        ::-webkit-scrollbar-track {
            background: rgba(0, 0, 0, 0);
        }

        ::-webkit-scrollbar-thumb {
            background: #888;
        }

        ::-webkit-scrollbar-thumb:hover {
            background: #555;
        }
    </style>
</head>

<body style="height: 50vh; background-color: #fff;">
    <nav class="navbar navbar-dark bg-primary fixed-top">
        <div class="container">
            <a href="index.php">
                <img src="assets/image/Logo_barterin.svg" class="p-2" style="width: 140px;" alt="">
            </a>
            <a href="https://www.instagram.com/barterin.co.id/">
                <img src="assets/image/ig.png" class="p-8" style="width: 140px;" alt="">
            </a>
        </div>
    </nav>

    <!--contact-->
    <div class="container" style="padding-top: 50px; padding-bottom: 70px;">
        <section id="contact">
            <div class="my-5 row text-center">
                <h1>Chatbot Barterin</h1>
            </div>
            <div class="row">
                <form method="post" class="form-user" id="formInput">
                    <div class="col-md-8">
                        <div class="mb-3">
                            <div class="card" style="border-radius: 20px 20px 20px 0;">
                                <div class="card-header bg-primary" style="color: white; border-radius: 20px 20px 0 0;">
                                    <p class="mb-0">Hai, ada yang bisa kami bantu?</p>
                                    <p class="mb-0">Silahkan pilih kategori yang ingin ditanyakan</p>
                                </div>
                                <div class="card-body" style="background-color:  #f5f3f4; border-radius: 0 0 20px 0;">
                                    <p class="mb-0">1. Barter</p>
                                    <p class="mb-0">2. Donasi</p>
                                    <p class="mb-0">3. Akun dan keamanan</p>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
        </section>

        <footer class="fixed-bottom">
            <hr class="m-0">
            <div style="background-color: #f5f3f4;">
                <div class="container">
                    <div class="row p-2">
                        <div class="col-10 px-1">
                            <input type="text" id="data" autocomplete="off" style="border-radius: 5px; height: 40px; width: 100%; padding-left: 10px; border: 1px solid rgba(0,0,0,.2);" placeholder="Ketikan Nomor Kategori" required>
                        </div>
                        <div class="col-2 d-grid gap-2 px-1">
                            <button class="btn btn-primary" style="border-radius: 5px;">Kirim</button>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </footer>
    </div>
    <!--end services-->
</body>

</html>