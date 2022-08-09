<?php 

include('conn.php');

$idData = $_POST['id'];
 
$delete = mysqli_query($db1, "DELETE FROM list_queries WHERE id = '$idData'");

if ($delete) {
    echo json_encode([
        "statusCode" => 200,
        "message" => "Berhasil menghapus data"
    ]);
}

?>