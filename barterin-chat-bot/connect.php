<?php

$conn = mysqli_connect('localhost', 'root', '', 'barterin');

if (mysqli_connect_error()) {
	echo 'Gagal melakukan koneksi ke Database : ' . mysqli_connect_error();
} else {
}
