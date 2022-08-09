<?php

include('conn.php');
    
    $id = $_POST['id'];
    $queries    = $_POST['queries'];
    $replies    = $_POST['replies'];
    
    $sql = mysqli_query($db1, "UPDATE list_queries SET queries = '$queries', replies = '$replies' WHERE id = '$id'");

    if ($sql) {
        echo json_encode([
            "statusCode" => 200,
            "message" => "Berhasil Mengubah data"
        ]);
    } else {
        echo json_encode([
            "statusCode" => 500,
            "message" => "Gagal Mengubah data"
        ]);
    }
      
?>