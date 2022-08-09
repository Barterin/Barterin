<?php

include('conn.php');

$id = $_POST['id'];
$queries    = $_POST['queries'];
$replies    = $_POST['replies'];


if (!empty($replies)) {
    $insert = mysqli_query($db1, "insert into list_queries set queries='$queries', replies='$replies'");
    $delete = mysqli_query($db1, "DELETE FROM list_request WHERE id = '$id'");

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
