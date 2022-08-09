<?php
   include("conn.php");
    // $sql = mysqli_query($connect, "SELECT * FROM list_queries");
    // $result = array();
    
    // while ($row = mysqli_fetch_assoc($sql)) {
    //     $data[] = $row;
    // }

    // echo json_encode(array("result" => $data));
    
    if($_POST['type'] == "tampil"){
        $data = [];
        $query = mysqli_query($db1, "SELECT * FROM list_queries ORDER BY id DESC ");
        while($row = mysqli_fetch_object($query)){
            $data[] = $row;
        }
        echo json_encode($data);
    }
?>
