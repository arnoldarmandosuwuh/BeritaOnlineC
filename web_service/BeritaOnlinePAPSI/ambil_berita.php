<?php

include 'koneksi.php';

if(isset($_GET["id_penulis"]))
	$id_penulis = $_GET["id_penulis"];
if(isset($_GET["isi"]))
	$isi = $_GET["isi"];
if(isset($_GET["tgl_awal"]))
	$tgl_awal = $_GET["tgl_awal"];
if (isset($_GET["tgl_akhir"]))
	$tgl_akhir = $_GET["tgl_akhir"];

$sql = "SELECT b.*, p.nama FROM berita b JOIN penulis p ON b.id_penulis = p.id WHERE 1=1";

if (isset($id_penulis))
	$sql .= " AND b.id_penulis = $id_penulis";
if (isset($isi))
	$sql .= " AND (b.isi LIKE '%$isi%' OR b.judul LIKE '%$isi%')";
if (isset($tgl_awal))
	$sql .= " AND b.created_at > STR_TO_DATE('$tgl_awal', '%d/%m/%Y')";
if (isset($tgl_akhir))
	$sql .= " AND b.created_at < STR_TO_DATE('$tgl_akhir', '%d/%m/%Y') + INTERVAL 1 DAY";

$sql .= " ORDER BY b.created_at DESC";

$query = $conn->query($sql);

$result = array();
if ($query) {
	$list = array();
	while ($row = $query->fetch_assoc()) {
		$berita['id'] = $row['id'];
		$berita['id_penulis'] = $row['id_penulis'];
		$berita['nama_penulis'] = $row['nama'];
		$berita['judul'] = $row['judul'];
		$berita['isi'] = $row['isi'];
		$berita['gambar'] = $row['gambar'];
		$berita['created_at'] = $row['created_at'];

		array_push($list, $berita);
	}
	$result['status'] = 0;
	$result['message'] = "Success";
	$result['data'] = $list;
} else {
	$result['status'] = 1;
	$result['message'] = "0 result";
}

$conn->close();
echo json_encode($result);