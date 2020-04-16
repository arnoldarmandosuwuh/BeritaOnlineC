<?php
include "koneksi.php";

$id_penulis = $_POST['id_penulis'];
$rating = $_POST['rating'];
$komentar = $_POST['komentar'];

$sql = "INSERT INTO feedback (id_penulis, rating, komentar) VALUES ($id_penulis, $rating, '$komentar')";

$result = array();

if ($conn->query($sql) === TRUE) {
	$result['status'] = 0;
	$result['message'] = "Feedback berhasil";
}
else {
	$result['status'] = 1;
	$result['message'] = "Error: ".$conn->error;
}

$conn->close();
echo json_encode($result);