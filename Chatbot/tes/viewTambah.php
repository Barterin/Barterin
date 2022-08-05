<!DOCTYPE html>
<html>

<head>
    <!-- <img class="head" src="assets\image\Logo Barterin (Logo).png"> -->
    <title>Admin</title>
    <script defer src='assets\js\bootstrap.min.js'></script>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='assets\css\bootstrap.min.css'>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css">
    <script defer src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script defer src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script defer src='assets\js\bootstrap.min.js'></script>
    <script defer type="text/javascript" src="fungsi.js"></script>

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

<body style="height: 100vh; background-color: #fff;">
    <nav class="navbar navbar-dark bg-primary fixed-top">
        <div class="container">
            <a class="col-md-1 navbar-brand" href="index.php" style="color: #fff;">
                Barterin
            </a>
            <a class="navbar-brand" href="viewRequest.php">Data Request</a>
        </div>
    </nav>
    <div class="container" style="padding-top: 50px; padding-bottom: 70px;">
        <section id="contact">
            <div class="my-5 row text-center">
                <h1>Tambah data</h1>
            </div>
            <p><a href="index.php">Back</a></p>
            <form method="post" class="form-user" id="formTambah">
                <table class="table">
                    <tr>
                        <td>Pertanyaan</td>
                        <td>:</td>
                        <td><input class="form-control" autocomplete="off" size="50" type="text" name="queries" required></td>
                    </tr>
                    <tr>
                        <td>Jawaban</td>
                        <td>:</td>
                        <td><textarea class="form-control" autocomplete="off" cols="47" rows="5" type="text" name="replies" required></textarea></td>
                    </tr>
                </table>
                <p>
                    <button class="tombol-simpan" type="submit">Simpan</button>
                </p>
            </form>
        </section>
    </div>
</body>

</html>