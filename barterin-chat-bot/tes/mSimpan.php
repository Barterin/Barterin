<?php

include('conn.php');

$queries    = $_POST['queries'];
$replies    = $_POST['replies'];

if (!empty($queries)) {
    $insert = mysqli_query($db1, "insert into list_queries set queries='$queries', replies='$replies'");

    if ($insert) {
        echo json_encode([
            "statusCode" => 200,
            "message" => "Berhasil Menyimpan data"
        ]);
    } else {
        echo json_encode([
            "statusCode" => 500,
            "message" => "Gagal Menyimpan data"
        ]);
    }
}
