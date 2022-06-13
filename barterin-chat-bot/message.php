<?php
// koneksi ke database
$conn = new mysqli("localhost", "root", "", "barterin") or die("Database Error");

// get pesan dari user
$getMesg = $conn->real_escape_string($_POST['text']);

//cek user query dengan yang ada di database
$check_data = "SELECT replies FROM list_queries WHERE queries LIKE '%$getMesg%'";
$run_query = $conn->query($check_data) or die("Error");

// jika query user sama dengan yang ada dalam database maka akan dibalas sesuai query-nya
if($run_query->num_rows > 0){
    $fetch_data = $run_query->fetch_assoc();
    //menyetorkan balasan ke variabel yang kemudian dikirimkan ke ajax
    $replay =  nl2br(htmlspecialchars($fetch_data['replies']));
    echo $replay;
}elseif ($getMesg == 1)
{
    echo "Silahkan pilih Pertanyaan yang ingin ditanyakan 
    <p>11. Apa itu Barter ?</p>
    <p>12. Bagaimana cara barter di barterin ?</p>";
}elseif ($getMesg == 2)
{
    echo "Silahkan pilih Pertanyaan yang ingin ditanyakan
    <p>21. Apa itu Donasi ?</p>
    <p>22. Bagaimana cara Beronasi di barterin ?</p>
    <p>23. Bagaimana cara mengambil donasi ?</p>";
}elseif ($getMesg == 3)
{
    echo "Silahkan pilih Pertanyaan yang ingin ditanyakan </p>
    <p>31. Bagaimana cara mengubah data diri ?</p>
    <p>32. Bagaimana cara mengganti nomor handphone</p>
    <p>33. </p>";
}elseif ($getMesg == 11)
{
    $read = "SELECT replies FROM list_queries WHERE id LIKE '36'";
    $run_query = $conn->query($read) or die("Error");
    $fetch_data = $run_query->fetch_assoc();
    //menyetorkan balasan ke variabel yang kemudian dikirimkan ke ajax
    $replay =  nl2br(htmlspecialchars($fetch_data['replies']));
    echo $replay;
}elseif ($getMesg == 12)
{
    $read = "SELECT replies FROM list_queries WHERE id LIKE '3'";
    $run_query = $conn->query($read) or die("Error");
    $fetch_data = $run_query->fetch_assoc();
    //menyetorkan balasan ke variabel yang kemudian dikirimkan ke ajax
    $replay =  nl2br(htmlspecialchars($fetch_data['replies']));
    echo $replay;
}elseif ($getMesg == 21)
{
    $read = "SELECT replies FROM list_queries WHERE id LIKE '37'";
    $run_query = $conn->query($read) or die("Error");
    $fetch_data = $run_query->fetch_assoc();
    //menyetorkan balasan ke variabel yang kemudian dikirimkan ke ajax
    $replay =  nl2br(htmlspecialchars($fetch_data['replies']));
    echo $replay;
}elseif ($getMesg == 22)
{
    $read = "SELECT replies FROM list_queries WHERE id LIKE '4'";
    $run_query = $conn->query($read) or die("Error");
    $fetch_data = $run_query->fetch_assoc();
    //menyetorkan balasan ke variabel yang kemudian dikirimkan ke ajax
    $replay =  nl2br(htmlspecialchars($fetch_data['replies']));
    echo $replay;
}else{
    echo "Maaf, aku bantu cek ya. Yuk infoin kendalanya dengan detail
    <p>Atau pilih kategori yang ingin ditanyakan
    <p>1. Barter</p>
    <p>2. Donasi</p>
    <p>3. Akun dan keamanan</p>";
}
?>
