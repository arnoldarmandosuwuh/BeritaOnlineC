<?php
include "koneksi.php";

$nama = $_POST['nama'];
$username = $_POST['username'];
$password = $_POST['password'];

$sql = "INSERT INTO penulis (nama, username, password) VALUES ('$nama', '$username', md5('$password'))";

$result = array();

if ($conn->query($sql) === TRUE) {
	$result['status'] = 0;
	$result['message'] = "Registrasi berhasil";
}
else {
	$result['status'] = 1;
	$result['message'] = "Error: ".$conn->error;
}

$conn->close();
echo json_encode($result);