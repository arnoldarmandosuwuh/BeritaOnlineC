<?php
include "koneksi.php";

$id_penulis = $_POST['id_penulis'];
$judul = $_POST['judul'];
$isi = $_POST['isi'];
$gambar = '';

if (isset($_POST['gambar']) && !empty($_POST['gambar'])) {
	$gambar = $_POST['gambar'];
}

$sql = "INSERT INTO berita (id_penulis, judul, isi, gambar) VALUES ($id_penulis, '$judul', '$isi', '$gambar')";

$result = array();

if ($conn->query($sql) === TRUE) {
	$result['status'] = 0;
	$result['message'] = "Tambah berita sukses";
}
else {
	$result['status'] = 1;
	$result['message'] = "Error: ".$conn->error;
}

$conn->close();
echo json_encode($result);