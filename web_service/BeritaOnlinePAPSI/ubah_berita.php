<?php

include "koneksi.php";

$id = $_POST['id'];
$judul = $_POST['judul'];
$isi = $_POST['isi'];
$gambar = '';

if (isset($_POST['gambar']) && !empty($_POST['gambar'])) {
	$gambar = $_POST['gambar'];
}

$sql = "UPDATE berita SET judul='$judul', isi='$isi', gambar='$gambar' WHERE id=$id";

$result = array();
if ($conn->query($sql) === TRUE) {
	$result['status'] = 0;
	$result['message'] = "Update success";
} else {
	$result['status'] = 1;
	$result['message'] = "Error: ".$conn->error;
}

$conn->close();
echo json_encode($result);