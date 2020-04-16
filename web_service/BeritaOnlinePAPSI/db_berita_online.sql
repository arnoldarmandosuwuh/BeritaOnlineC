-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 16, 2020 at 09:27 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_berita_online`
--

-- --------------------------------------------------------

--
-- Table structure for table `berita`
--

CREATE TABLE `berita` (
  `id` int(11) NOT NULL,
  `id_penulis` int(11) NOT NULL,
  `judul` varchar(500) NOT NULL,
  `isi` longtext NOT NULL,
  `gambar` varchar(9999) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `berita`
--

INSERT INTO `berita` (`id`, `id_penulis`, `judul`, `isi`, `gambar`, `created_at`) VALUES
(4, 2, 'Judul 2', 'Ini adalah isi dari judul 2', 'https://images.freeimages.com/images/large-previews/f2c/effi-1-1366221.jpg', '2020-02-13 10:51:52'),
(5, 20, 'Berita Kedua', 'isi berita kedua', 'https://images.freeimages.com/images/large-previews/f2c/effi-1-1366221.jpg', '2020-03-12 14:44:56'),
(6, 3, 'test', 'Halo test', 'https://images.freeimages.com/images/large-previews/f2c/effi-1-1366221.jpg', '2020-04-16 08:49:36');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `id_penulis` int(11) NOT NULL,
  `rating` int(11) NOT NULL,
  `komentar` varchar(200) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`id`, `id_penulis`, `rating`, `komentar`, `created_at`) VALUES
(1, 3, 5, 'sangat bagus', '2020-04-16 13:27:14'),
(2, 20, 4, 'Menarique', '2020-04-16 14:07:57');

-- --------------------------------------------------------

--
-- Table structure for table `penulis`
--

CREATE TABLE `penulis` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `foto` varchar(999) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf32;

--
-- Dumping data for table `penulis`
--

INSERT INTO `penulis` (`id`, `nama`, `username`, `password`, `foto`, `created_at`) VALUES
(2, 'Zuwfadli', 'fadli', 'pakaya', NULL, '2020-02-06 12:03:32'),
(3, 'Danang', 'danang', '6a17faad3b1275fd2558d5435c58440e', NULL, '2020-02-27 10:35:13'),
(17, 'kholish', 'kholish', '9df6fb9d43d35e3afff5559744adc0e0', NULL, '2020-02-27 10:54:24'),
(20, 'Arnold', 'arnold', 'b88e4ca262c575efb123a249b2861dd0', NULL, '2020-02-27 13:13:56'),
(25, 'Vian', 'vian', '2ceefdecaeaba09fd7d342302d91debf', NULL, '2020-02-27 13:24:32');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `berita`
--
ALTER TABLE `berita`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_penulis` (`id_penulis`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_penulis_2` (`id_penulis`),
  ADD KEY `id_penulis` (`id_penulis`);

--
-- Indexes for table `penulis`
--
ALTER TABLE `penulis`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `berita`
--
ALTER TABLE `berita`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `penulis`
--
ALTER TABLE `penulis`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `berita`
--
ALTER TABLE `berita`
  ADD CONSTRAINT `berita_ibfk_1` FOREIGN KEY (`id_penulis`) REFERENCES `penulis` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
