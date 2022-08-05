<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Admin</title>
    <script defer src='assets\js\bootstrap.min.js'></script>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel='stylesheet' type='text/css' media='screen' href='assets\css\bootstrap.min.css'>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css">
    <script defer src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script defer src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script defer src='assets\js\bootstrap.min.js'></script>
    <script defer type="text/javascript" src="fungsiRequest.js"></script>

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
                <h1>Chatbot Barterin</h1>
            </div>
            <div class="container">
                <div class="col-md-12">
                    <div class="row">

                        <table class="table table-bordered" id="data">
                            <thead class="thead-dark">
                                <th>No</th>
                                <th>Request</th>
                                <th>Action</th>
                            </thead>
                            <tbody>
                                <?php
                                include 'conn.php';
                                $no = 1;
                                $query = "SELECT * FROM list_request ORDER BY id DESC";
                                $dewan1 = $db1->prepare($query);
                                $dewan1->execute();
                                $res1 = $dewan1->get_result();

                                while ($row = $res1->fetch_assoc()) {
                                    $id = $row['id'];
                                    $queries = $row['queries'];
                                ?>
                                    <tr>
                                        <td><?php echo $no++; ?></td>
                                        <td><?php echo $queries; ?></td>
                                        <td>
                                            <a href=" viewEditRequest.php?id=<?= $id ?>">[Tambah]</a>
                                            <input type="button" id="delPOIbutton" value="delete" data-id='<?= $id ?>'"/>
                                    </td>
                                </tr>
                            <?php } ?>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>
    </div>
</body>

</html>