-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 05 Agu 2022 pada 11.30
-- Versi server: 10.4.21-MariaDB
-- Versi PHP: 7.4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `barterin`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `list_queries`
--

CREATE TABLE `list_queries` (
  `id` int(11) NOT NULL,
  `queries` varchar(100) NOT NULL,
  `replies` varchar(5000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `list_queries`
--

INSERT INTO `list_queries` (`id`, `queries`, `replies`) VALUES
(1, 'Hallo', 'Hallo, Ada yang bisa kami bantu ?'),
(2, 'Hai', 'Hai, Ada yang bisa kami bantu ?'),
(3, 'halo', 'Hallo, Ada yang bisa kami bantu ?'),
(4, 'error', 'Jika Anda mengalami kendala pada situs Barterin, seperti: \r\n1.        Bermasalah saat memuat halaman situs\r\n2.        Tidak dapat melakukan pembayaran\r\n3.        Tidak bisa memasukkan produk ke keranjang\r\n4.        Tidak dapat mengirim pesan melalui fitur Chat Barterin \r\nBerikut adalah beberapa solusi yang dapat dilakukan:\r\nSolusi untuk kendala situs\r\n1.        Tutup dan buka kembali situs Barterin (tutup paksa situs jika perlu).\r\n2.        Logout dan login kembali ke akun Barterin Anda.\r\nSolusi untuk kendala koneksi\r\n1.        Jika Anda terhubung melalui data seluler, pastikan Anda memiliki koneksi internet yang stabil.\r\n2.        Jika Anda terhubung melalui Wi-Fi, restart modem atau router yang Anda gunakan untuk memperbarui koneksi internet.\r\n3.        Hidupkan dan matikan Mode Pesawat di handphone Anda untuk mengatur ulang koneksi.\r\nSolusi untuk kendala perangkat\r\n1.        Tutup semua situs lain yang sedang berjalan.\r\n2.        Restart handphone/laptop/komputer dan masuk ke akun B'),
(5, 'blank page', 'Jika Anda mengalami kendala pada situs Barterin, seperti: \r\n1.        Bermasalah saat memuat halaman situs\r\n2.        Tidak dapat melakukan pembayaran\r\n3.        Tidak bisa memasukkan produk ke keranjang\r\n4.        Tidak dapat mengirim pesan melalui fitur Chat Barterin \r\n\r\nBerikut adalah beberapa solusi yang dapat dilakukan:\r\n\r\nSolusi untuk kendala situs\r\n1.        Tutup dan buka kembali situs Barterin (tutup paksa situs jika perlu).\r\n2.        Logout dan login kembali ke akun Barterin Anda.\r\n\r\nSolusi untuk kendala koneksi\r\n1.        Jika Anda terhubung melalui data seluler, pastikan Anda memiliki koneksi internet yang stabil.\r\n2.        Jika Anda terhubung melalui Wi-Fi, restart modem atau router yang Anda gunakan untuk memperbarui koneksi internet.\r\n3.        Hidupkan dan matikan Mode Pesawat di handphone Anda untuk mengatur ulang koneksi.\r\n\r\nSolusi untuk kendala perangkat\r\n1.        Tutup semua situs lain yang sedang berjalan.\r\n2.        Restart handphone/laptop/komputer dan masuk ke akun Barterin'),
(6, 'blank page website', 'Jika Anda mengalami kendala pada situs Barterin, seperti: \r\n1.        Bermasalah saat memuat halaman situs\r\n2.        Tidak dapat melakukan pembayaran\r\n3.        Tidak bisa memasukkan produk ke keranjang\r\n4.        Tidak dapat mengirim pesan melalui fitur Chat Barterin \r\n\r\nBerikut adalah beberapa solusi yang dapat dilakukan:\r\n\r\nSolusi untuk kendala situs\r\n1.        Tutup dan buka kembali situs Barterin (tutup paksa situs jika perlu).\r\n2.        Logout dan login kembali ke akun Barterin Anda.\r\n\r\nSolusi untuk kendala koneksi\r\n1.        Jika Anda terhubung melalui data seluler, pastikan Anda memiliki koneksi internet yang stabil.\r\n2.        Jika Anda terhubung melalui Wi-Fi, restart modem atau router yang Anda gunakan untuk memperbarui koneksi internet.\r\n3.        Hidupkan dan matikan Mode Pesawat di handphone Anda untuk mengatur ulang koneksi.\r\n\r\nSolusi untuk kendala perangkat\r\n1.        Tutup semua situs lain yang sedang berjalan.\r\n2.        Restart handphone/laptop/komputer dan masuk ke akun Barterin'),
(7, 'blank page aplikasi', 'Jika Anda mengalami kendala pada situs Barterin, seperti: \r\n1.        Bermasalah saat memuat halaman situs\r\n2.        Tidak dapat melakukan pembayaran\r\n3.        Tidak bisa memasukkan produk ke keranjang\r\n4.        Tidak dapat mengirim pesan melalui fitur Chat Barterin \r\n\r\nBerikut adalah beberapa solusi yang dapat dilakukan:\r\n\r\nSolusi untuk kendala situs\r\n1.        Tutup dan buka kembali situs Barterin (tutup paksa situs jika perlu).\r\n2.        Logout dan login kembali ke akun Barterin Anda.\r\n\r\nSolusi untuk kendala koneksi\r\n1.        Jika Anda terhubung melalui data seluler, pastikan Anda memiliki koneksi internet yang stabil.\r\n2.        Jika Anda terhubung melalui Wi-Fi, restart modem atau router yang Anda gunakan untuk memperbarui koneksi internet.\r\n3.        Hidupkan dan matikan Mode Pesawat di handphone Anda untuk mengatur ulang koneksi.\r\n\r\nSolusi untuk kendala perangkat\r\n1.        Tutup semua situs lain yang sedang berjalan.\r\n2.        Restart handphone/laptop/komputer dan masuk ke akun Barterin'),
(8, 'login', 'Berikut adalah cara untuk masuk ke akun barterin :\r\n1. Masuk ke aplikasi Barterin\r\n2. Pilih masuk menggunakan Google atau Facebook\r\n3. Jika berhasil, maka akan masuk ke halaman Home'),
(9, 'cara login', 'Berikut adalah cara untuk masuk ke akun barterin :\r\n1. Masuk ke aplikasi Barterin\r\n2. Pilih masuk menggunakan Google atau Facebook\r\n3. Jika berhasil, maka akan masuk ke halaman Home'),
(10, 'bagaimana cara login', 'Berikut adalah cara untuk masuk ke akun barterin :\r\n1. Masuk ke aplikasi Barterin\r\n2. Pilih masuk menggunakan Google atau Facebook\r\n3. Jika berhasil, maka akan masuk ke halaman Home'),
(11, 'tidak bisa login', 'Upaya log in yang gagal ke akun Barterin Anda dapat terjadi karena alasan berikut:\r\n\r\n1. Password atau Email salah\r\nPeriksa kembali apakah Anda telah memasukkan password dengan benar. \r\n\r\n2. Koneksi yang kurang stabil\r\nPeriksa kembali koneksi internet yang digunakan\r\n\r\n3. Masalah teknis\r\nKesalahan login terkadang dapat terjadi karena masalah teknis. Pastikan aplikasi Barterin Anda diperbarui ke versi terbaru di Play Store. Gunakan juga internet yang stabil saat memperbarui aplikasi Barterin.'),
(12, 'kenapa tidak bisa login', 'Upaya log in yang gagal ke akun Barterin Anda dapat terjadi karena alasan berikut:\r\n\r\n1. Password atau Email salah\r\nPeriksa kembali apakah Anda telah memasukkan password dengan benar. \r\n\r\n2. Koneksi yang kurang stabil\r\nPeriksa kembali koneksi internet yang digunakan\r\n\r\n3. Masalah teknis\r\nKesalahan login terkadang dapat terjadi karena masalah teknis. Pastikan aplikasi Barterin Anda diperbarui ke versi terbaru di Play Store. Gunakan juga internet yang stabil saat memperbarui aplikasi Barterin.'),
(13, 'mengapa tidak bisa login', 'Upaya log in yang gagal ke akun Barterin Anda dapat terjadi karena alasan berikut:\r\n\r\n1. Password salah\r\nPeriksa kembali apakah Anda telah memasukkan password dengan benar. \r\n\r\n2. Kode OTP sudah kedaluwarsa\r\nPastikan Anda memasukkan Kode OTP yang sudah dikirimkan melalui WhatsApp atau SMS dalam batas waktu yang sudah ditentukan. Jika Anda memasukkan Kode OTP di luar waktu yang sudah ditentukan, silakan minta kembali Kode OTP baru.\r\n\r\n3. Akun dibatasi\r\nAkun Anda mungkin dibatasi karena:\r\n0) Alasan keamanan, seperti aktivitas log in yang mencurigakan atau dugaan upaya peretasan. Akun akan aman jika Anda tidak memberikan Kode OTP ataupun PIN kepada siapa pun.\r\n0) Alasan lain, seperti pelanggaran kebijakan Barterin.\r\n\r\n4. Masalah teknis\r\nKesalahan log in terkadang dapat terjadi karena masalah teknis. Pastikan aplikasi Barterin Anda diperbarui ke versi terbaru di Play Store. Gunakan juga internet yang stabil saat memperbarui aplikasi Barterin.'),
(14, 'logout', 'Berikut adalah cara untuk keluar dari akun barterin :\r\n1. Klik loguot yang berada di navigasi (pojok kiri/kanan)\r\n2. klik keluar/logout'),
(15, 'cara logout', 'Berikut adalah cara untuk keluar dari akun barterin :\r\n1. Klik loguot yang berada di navigasi (pojok kiri/kanan)\r\n2. klik keluar/logout'),
(16, 'tidak ada otp', 'Jika Anda tidak menerima Kode Verifikasi (OTP) melalui WhatsApp atau SMS, bisa jadi karena alasan berikut ini:\r\n \r\n1. Masalah jaringan atau konektivitas\r\nAnda dapat mencoba teknik pemecahan masalah dasar berikut:\r\n0) Periksa apakah sinyal Anda penuh.\r\n0) Kirim ulang Kode Verifikasi (OTP) hingga 4 (empat) kali percobaan.\r\n0) Matikan handphone Anda, keluarkan dan masukkan kembali kartu SIM Anda, dan hidupkan kembali handphone Anda. Kemudian, buat permintaan lain untuk Kode Verifikasi (OTP).\r\n \r\n2. Akun ditautkan ke nomor telepon lain\r\nPeriksa informasi profil Anda untuk memastikan bahwa no. handphone yang benar telah dihubungkan ke akun Barterin Anda.'),
(17, 'tidak terima otp', 'Jika Anda tidak menerima Kode Verifikasi (OTP) melalui WhatsApp atau SMS, bisa jadi karena alasan berikut ini:\r\n \r\n1. Masalah jaringan atau konektivitas\r\nAnda dapat mencoba teknik pemecahan masalah dasar berikut:\r\n0) Periksa apakah sinyal Anda penuh.\r\n0) Kirim ulang Kode Verifikasi (OTP) hingga 4 (empat) kali percobaan.\r\n0) Matikan handphone Anda, keluarkan dan masukkan kembali kartu SIM Anda, dan hidupkan kembali handphone Anda. Kemudian, buat permintaan lain untuk Kode Verifikasi (OTP).\r\n \r\n2. Akun ditautkan ke nomor telepon lain\r\nPeriksa informasi profil Anda untuk memastikan bahwa no. handphone yang benar telah dihubungkan ke akun Barterin Anda.'),
(18, 'otp tidak ada', 'Jika Anda tidak menerima Kode Verifikasi (OTP) melalui WhatsApp atau SMS, bisa jadi karena alasan berikut ini:\r\n \r\n1. Masalah jaringan atau konektivitas\r\nAnda dapat mencoba teknik pemecahan masalah dasar berikut:\r\n0) Periksa apakah sinyal Anda penuh.\r\n0) Kirim ulang Kode Verifikasi (OTP) hingga 4 (empat) kali percobaan.\r\n0) Matikan handphone Anda, keluarkan dan masukkan kembali kartu SIM Anda, dan hidupkan kembali handphone Anda. Kemudian, buat permintaan lain untuk Kode Verifikasi (OTP).\r\n \r\n2. Akun ditautkan ke nomor telepon lain\r\nPeriksa informasi profil Anda untuk memastikan bahwa no. handphone yang benar telah dihubungkan ke akun Barterin Anda.'),
(19, 'ubah data', 'Klik username Anda kemudian pilih Akun Saya dan pilih Ubah. Masukkan Password untuk verifikasi, lalu klik Konfirmasi. Masukkan data diri baru dan klik KONFIRMASI. Setelah ini, Anda harus verifikasi melalui link yang akan dikirim ke email Anda.'),
(20, 'mengubah data diri', 'Klik username Anda kemudian pilih Akun Saya dan pilih Ubah. Masukkan Password untuk verifikasi, lalu klik Konfirmasi. Masukkan data diri baru dan klik KONFIRMASI. Setelah ini, Anda harus verifikasi melalui link yang akan dikirim ke email Anda.'),
(21, 'cara mengubah data diri', 'Klik username Anda kemudian pilih Akun Saya dan pilih Ubah. Masukkan Password untuk verifikasi, lalu klik Konfirmasi. Masukkan data diri baru dan klik KONFIRMASI. Setelah ini, Anda harus verifikasi melalui link yang akan dikirim ke email Anda.'),
(22, 'menambah alamat rumah', 'Klik username Anda kemudian pilih Akun Saya dan pilih Ubah. Masukkan Password untuk verifikasi, lalu klik Konfirmasi. Klik tambah alamat baru lalu masukan alamat Baru yang sesuai dan klik KONFIRMASI. Setelah ini, Anda harus verifikasi melalui link yang akan dikirim ke email Anda'),
(23, 'bagaimana cara menambah alamat rumah', 'Klik username Anda kemudian pilih Akun Saya dan pilih Ubah. Masukkan Password untuk verifikasi, lalu klik Konfirmasi. Klik tambah alamat baru lalu masukan alamat Baru yang sesuai dan klik KONFIRMASI. Setelah ini, Anda harus verifikasi melalui link yang akan dikirim ke email Anda'),
(24, 'mengganti alamat', 'Klik username Anda kemudian pilih Akun Saya dan pilih Ubah. Masukkan Password untuk verifikasi, lalu klik Konfirmasi. Masukkan data diri baru dan klik KONFIRMASI. Setelah ini, Anda harus verifikasi melalui link yang akan dikirim ke email Anda.'),
(25, 'bagaimana cara mengganti alamat', 'Klik username Anda kemudian pilih Akun Saya dan pilih Ubah. Masukkan Password untuk verifikasi, lalu klik Konfirmasi. Masukkan data diri baru dan klik KONFIRMASI. Setelah ini, Anda harus verifikasi melalui link yang akan dikirim ke email Anda.'),
(26, 'lupa paswoard', 'Jika Anda belum login dan lupa password akun Barterin Anda, ikutilah langkah-langkah berikut:\r\n1. Pilih Lupa? di halaman Log In di situs Barterin\r\n2. Masukkan No. Handphone yang terdaftar di akun Anda*\r\n3. Masukkan Kode CAPTCHA\r\n4. Masukkan Kode OTP via email\r\n5. Atur Password baru\r\n6. OK'),
(27, 'ubah nomor hp', 'Berikut adalah cara mengubah nomor Handphone :\r\n1. Klik username Anda\r\n2. pilih Akun Saya dan pilih Ubah nomor Handphone\r\n3. Masukkan Password untuk verifikasi, lalu klik Konfirmasi. \r\n4. Setelah ini, Anda harus memasukan kode OTP yang telah dikirimkan ke email.'),
(28, 'ubah nomor handphone', 'Berikut adalah cara mengubah nomor Handphone :\r\n1. Klik username Anda\r\n2. pilih Akun Saya dan pilih Ubah nomor Handphone\r\n3. Masukkan Password untuk verifikasi, lalu klik Konfirmasi. \r\n4. Setelah ini, Anda harus memasukan kode OTP yang telah dikirimkan ke email.'),
(29, 'buat akun', 'Sebelum Anda membuat akun, baca Ketentuan Layanan dan Kebijakan Privasi Barterin terlebih dahulu.\r\nSetelah Anda menyetujui ketentuan dan kebijakan di atas, Anda dapat mendaftarkan akun Barterin melalui aplikasi Barterin resmi atau situs Barterin.\r\n\r\nBerikut adalah cara mendaftar (register) di aplikasi Barterin:\r\n1. Buka Website atau Aplikasi Barterin\r\n2. Klik SignUp di pojok kanan atas\r\n3. Masukan Nama, Username, Email dan Passwoard\r\n4. Centang \"Creating an account means your okay with our Terms of service, Pripacy policy, and our defaultNotification settings\"\r\n5. Klik \"Cretae Acount\"\r\n6. Cek email yang digunakan untuk mendaftar, lalu Masukan kode OTP\r\n7. Klik Verify Account\r\n8. Masukan Username/emal dan Passwoard untuk login\r\n*Pastikan tidak memberikan kode OTP pada siapapun '),
(30, 'register', 'Sebelum Anda membuat akun, baca Ketentuan Layanan dan Kebijakan Privasi Barterin terlebih dahulu.\r\nSetelah Anda menyetujui ketentuan dan kebijakan di atas, Anda dapat mendaftarkan akun Barterin melalui aplikasi Barterin resmi atau situs Barterin.\r\n\r\nBerikut adalah cara mendaftar (register) di aplikasi Barterin:\r\n1. Buka Website atau Aplikasi Barterin\r\n2. Klik SignUp di pojok kanan atas\r\n3. Masukan Nama, Username, Email dan Passwoard\r\n4. Centang \"Creating an account means your okay with our Terms of service, Pripacy policy, and our defaultNotification settings\"\r\n5. Klik \"Cretae Acount\"\r\n6. Cek email yang digunakan untuk mendaftar, lalu Masukan kode OTP\r\n7. Klik Verify Account\r\n8. Masukan Username/emal dan Passwoard untuk login\r\n*Pastikan tidak memberikan kode OTP pada siapapun '),
(31, 'cara membuat akun barterin', 'Sebelum Anda membuat akun, baca Ketentuan Layanan dan Kebijakan Privasi Barterin terlebih dahulu.\r\nSetelah Anda menyetujui ketentuan dan kebijakan di atas, Anda dapat mendaftarkan akun Barterin melalui aplikasi Barterin resmi atau situs Barterin.\r\n\r\nBerikut adalah cara mendaftar (register) di aplikasi Barterin:\r\n1. Buka Website atau Aplikasi Barterin\r\n2. Klik SignUp di pojok kanan atas\r\n3. Masukan Nama, Username, Email dan Passwoard\r\n4. Centang \"Creating an account means your okay with our Terms of service, Pripacy policy, and our defaultNotification settings\"\r\n5. Klik \"Cretae Acount\"\r\n6. Cek email yang digunakan untuk mendaftar, lalu Masukan kode OTP\r\n7. Klik Verify Account\r\n8. Masukan Username/emal dan Passwoard untuk login\r\n*Pastikan tidak memberikan kode OTP pada siapapun '),
(32, 'cara membuat akun baru', 'Sebelum Anda membuat akun, baca Ketentuan Layanan dan Kebijakan Privasi Barterin terlebih dahulu.\r\nSetelah Anda menyetujui ketentuan dan kebijakan di atas, Anda dapat mendaftarkan akun Barterin melalui aplikasi Barterin resmi atau situs Barterin.\r\n\r\nBerikut adalah cara mendaftar (register) di aplikasi Barterin:\r\n1. Buka Website atau Aplikasi Barterin\r\n2. Klik SignUp di pojok kanan atas\r\n3. Masukan Nama, Username, Email dan Passwoard\r\n4. Centang \"Creating an account means your okay with our Terms of service, Pripacy policy, and our defaultNotification settings\"\r\n5. Klik \"Cretae Acount\"\r\n6. Cek email yang digunakan untuk mendaftar, lalu Masukan kode OTP\r\n7. Klik Verify Account\r\n8. Masukan Username/emal dan Passwoard untuk login\r\n*Pastikan tidak memberikan kode OTP pada siapapun '),
(33, 'penipu', 'Jika Anda terkena penipuan yang mengaku sebagai pihak Barterin, segera hubungi Customer Service Barterin untuk pemeriksaan lebih lanjut dengan cara hubungi 1500702. Hindari penipuan yang meminta Anda untuk memberikan informasi pribadi dengan mengikuti panduan berikut:\r\nKenali ciri-ciri modus penipuan\r\nPenipu yang mengatasnamakan Barterin biasanya menelepon atau mengirim pesan melalui SMS atau WhatsApp berupa:\r\n1.        Pengumuman Pemenang undian beserta nominal hadiah.\r\n2.        Alamat situs mencurigakan yang bukan merupakan situs resmi Barterin.'),
(34, 'kena tipu', 'Jika Anda terkena penipuan yang mengaku sebagai pihak Barterin, segera hubungi Customer Service Barterin untuk pemeriksaan lebih lanjut dengan cara hubungi 1500702. Hindari penipuan yang meminta Anda untuk memberikan informasi pribadi dengan mengikuti panduan berikut:\r\nKenali ciri-ciri modus penipuan\r\nPenipu yang mengatasnamakan Barterin biasanya menelepon atau mengirim pesan melalui SMS atau WhatsApp berupa:\r\n1.        Pengumuman Pemenang undian beserta nominal hadiah.\r\n2.        Alamat situs mencurigakan yang bukan merupakan situs resmi Barterin.'),
(35, 'laporan penipuan', 'Jika Anda terkena penipuan yang mengaku sebagai pihak Barterin, segera hubungi Customer Service Barterin untuk pemeriksaan lebih lanjut dengan cara hubungi 1500702. Hindari penipuan yang meminta Anda untuk memberikan informasi pribadi dengan mengikuti panduan berikut:\r\nKenali ciri-ciri modus penipuan\r\nPenipu yang mengatasnamakan Barterin biasanya menelepon atau mengirim pesan melalui SMS atau WhatsApp berupa:\r\n1.        Pengumuman Pemenang undian beserta nominal hadiah.\r\n2.        Alamat situs mencurigakan yang bukan merupakan situs resmi Barterin.'),
(36, 'cara menghindari penipuan', 'Langkah pencegahan dan penanganan penipuan:\r\n \r\n1. Jangan klik link situs yang dikirimkan melalui SMS atau WhatsApp. Segera akhiri komunikasi dan konfirmasikan ke email Barterin \r\n2. Lakukan semua transaksi di platform Barterin \r\nSaat Anda melakukan transaksi di Barterin, pastikan selalu melakukan semua transaksi di situs Barterin.\r\nWaspadalah terhadap siapa pun yang meminta Anda untuk melakukan pembayaran tanpa melalui halaman checkout di situs Barterin.\r\n3. Hati-hati dengan pesan yang terlihat mencurigakan & mengatasnamakan Barterin dengan ciri-ciri:\r\nMenggunakan ejaan dan tata bahasa yang buruk atau meminta informasi sensitif, seperti password, Undian/hadiah dari Barterin, Permasalahan teknis terkait akun Barterin atau pesanan.\r\n4. Jangan pernah membagikan informasi akun sensitif. Pihak yang mengatasnamakan Barterin kemudian biasanya akan melakukan hal-hal berikut:\r\nMeminta informasi akun, seperti password,tautan pengaturan ulang password, atau bahkan foto diri Anda dapat membantu penipu mendapatkan akses ke akun Anda.\r\nBarterin tidak akan pernah menanyakan informasi sensitif kepada Anda di luar aplikasi Barterin.\r\nMeminta password atau data pribadi lainnya.\r\n5. Gunakan password yang baik\r\nSebaiknya ganti password akun Anda secara berkala.\r\nHindari menggunakan password di berbagai platform.'),
(37, 'Barterin adalah', 'Barterin adalah sebuah e-commerce yang saat ini memiliki 2 fitur utama, yaitu Barter dan Donasi.\r\nYuk Kepoin lebih lanjut di https:/barterin.tech atau Sosial media Barterin.\r\nInstagram : @barterin.tech\r\nLinkedin : Barterin'),
(40, 'apa itu barterin', 'barter merupakan sistem perdagangan yang di dalamnya terdapat kegiatan tukar-menukar barang tanpa melibatkan uang sebagai alat transaksi.'),
(41, 'cara upload', 'Berikut ini adalah langkah-langkah upload barang\r\n1. Pastikan anda telah terdaftar sebagai user Barterin. Jika belum silahkan Ketik 31 untuk bagi mana cara register akun Barterin\r\n2. Klik tambah barang pada navigasi\r\n3. Upload photo, pilih kamera atau ambil dari Galeri\r\n4. Masukan nama Produk, jenis produk, Lama pemakaian, kisaran harga beli dan deskripsi barang\r\n5. Pilih lokasi anda\r\n6. Pilih keterangan barang (barter/donasi)\r\n7. Jika data sudah terisi semua, klik tambah barang'),
(42, 'bagaimana cara upload', 'Berikut ini adalah langkah-langkah upload barang\r\n1. Pastikan anda telah terdaftar sebagai user Barterin. Jika belum silahkan Ketik 31 untuk bagi mana cara register akun Barterin\r\n2. Klik tambah barang pada navigasi\r\n3. Upload photo, pilih kamera atau ambil dari Galeri\r\n4. Masukan nama Produk, jenis produk, Lama pemakaian, kisaran harga beli dan deskripsi barang\r\n5. Pilih lokasi anda\r\n6. Pilih keterangan barang (barter/donasi)\r\n7. Jika data sudah terisi semua, klik tambah barang'),
(43, 'langkan upload', 'Berikut ini adalah langkah-langkah upload barang\r\n1. Pastikan anda telah terdaftar sebagai user Barterin. Jika belum silahkan Ketik 31 untuk bagi mana cara register akun Barterin\r\n2. Klik tambah barang pada navigasi\r\n3. Upload photo, pilih kamera atau ambil dari Galeri\r\n4. Masukan nama Produk, jenis produk, Lama pemakaian, kisaran harga beli dan deskripsi barang\r\n5. Pilih lokasi anda\r\n6. Pilih keterangan barang (barter/donasi)\r\n7. Jika data sudah terisi semua, klik tambah barang'),
(44, 'barter adalah', 'Barter merupakan sistem perdagangan yang di dalamnya terdapat kegiatan tukar-menukar barang tanpa melibatkan uang sebagai alat transaksi.'),
(45, 'apa itu benter', 'Barter merupakan sistem perdagangan yang di dalamnya terdapat kegiatan tukar-menukar barang tanpa melibatkan uang sebagai alat transaksi.'),
(47, 'donasi adalah', 'Donasi adalah sebuah pemberian dari perorangan atau badan hukum kepada pihak tertentu yang mempunyai sifat sukarela dengan tanpa adanya imbalan yang bersifat keuntungan. Pada platform Barterin ini, donasi yang dimaksud adalah donasi sebuah barang bekas yang masih layak pakai.\r\nJika anda ingin menjadi salah satu donatur, ketik 22 untuk informasi lebih lanjut.'),
(48, 'cara menjadi donatur', 'Berikut adalah cara salah satu donatur dalam Platform Barterin :\r\n1. \r\n \r\n'),
(49, 'donasi di batasi', 'Kenapa tidak bisa mengambil donasi ?\r\n\r\nSebelumnya Silahkan periksa terlebih dahulu riwayat transaksi sebelumnya dengan cara Masuk kedalam Riwayat transaksi.\r\nJika dalam 1 minggu ini sudah lebih dari 3 kali menerima donasi, maka akun anda akan di batasi hingga 1 minggu ke depan.\r\n\r\nSatu akun hanya bisa menerima maksimal 3 kali donasi selama 1 minggu.\r\n\r\nJika belum menerima donasi dan akun anda di batasi, silahkan muat ulang website atau keluar dari website dan tunggu beberapa menit untuk masuk kembali.\r\n'),
(50, 'kenapa dibatasi', 'Kenapa tidak bisa mengambil donasi ?\r\n\r\nSebelumnya Silahkan periksa terlebih dahulu riwayat transaksi sebelumnya dengan cara Masuk kedalam Riwayat transaksi.\r\nJika dalam 1 minggu ini sudah lebih dari 3 kali menerima donasi, maka akun anda akan di batasi hingga 1 minggu ke depan.\r\n\r\nSatu akun hanya bisa menerima maksimal 3 kali donasi selama 1 minggu.\r\n\r\nJika belum menerima donasi dan akun anda di batasi, silahkan muat ulang website atau keluar dari website dan tunggu beberapa menit untuk masuk kembali.'),
(51, 'cara donasi', 'Berikut ini adalah langkah-langkah upload barang donasi\r\n1. Pastikan anda telah terdaftar sebagai user Barterin. Jika belum silahkan Ketik 31 untuk bagi mana cara register akun Barterin\r\n2. Klik tambah barang pada navigasi\r\n3. Upload photo, pilih kamera atau ambil dari Galeri\r\n4. Masukan nama Produk, jenis produk, Lama pemakaian, kisaran harga beli dan deskripsi barang\r\n5. Pilih lokasi anda\r\n6. Pilih keterangan barang donasi\r\n7. Jika data sudah terisi semua, klik tambah barang'),
(52, 'langkah donasi barang', 'Berikut ini adalah langkah-langkah upload barang donasi\r\n1. Pastikan anda telah terdaftar sebagai user Barterin. Jika belum silahkan Ketik 31 untuk bagi mana cara register akun Barterin\r\n2. Klik tambah barang pada navigasi\r\n3. Upload photo, pilih kamera atau ambil dari Galeri\r\n4. Masukan nama Produk, jenis produk, Lama pemakaian, kisaran harga beli dan deskripsi barang\r\n5. Pilih lokasi anda\r\n6. Pilih keterangan barang donasi\r\n7. Jika data sudah terisi semua, klik tambah barang'),
(55, 'Cara mengambil donasi', 'Berikut ini adalah langkah-langkah mengambil donasi\r\n1. Pastikan anda telah terdaftar sebagai user Barterin. Jika belum silahkan Ketik 31 untuk bagi mana cara register akun Barterin\r\n2. Pilih barang yang ingin anda butuhkan\r\n3. Masukan alasan kenapa anda sangat membutuhkan barang tersebut di kolom alasan\r\n4. tunggu pihak donatur memutuskan\r\n5. jika anda terpilih sebagai penerima donasi, maka\r\n'),
(56, 'hghjgjh', 'cbvghvhj'),
(57, 'Barang yang di barter', 'Berikut adalah contoh barang yang dapat di barter :\r\n1. Pakaian Dewasa\r\n2. Pakaian Anak\r\n3. Peralatan Bayi\r\n4. Eleltronik'),
(58, 'cara barter', 'Berikut adalah langkah-langkah barter :\r\n1. Pilih barang yang masih tersedia untuk barter\r\n2. Klik tambah keranjang lalu klik oke\r\n3. klik chat pemilik untuk diskusi pribadi atau ikuti diskusi untuk diskusi umum\r\n4. Jika sudah sepakat dengan pemilik barang, klik tambah tawaran lalu pilih barang yang akan di barter\r\n5. Maka status barang akan muncul pada data transaksi\r\n6. kirimkan barang sesuai dengan kesepakatan (COD/Kirim ekspedisi) '),
(59, 'Kenapa tidak bisa mengambil donasi', 'Silahkan cek kembali riwayat transaksi. Jika sudah 3 kali mengambil donasi, maka akun anda akan dibatasi selama 7 hari dan bisa kembali mengambil donasi setelah masa bata selesai.\r\nSetiap user hanya bisa mengambl donasi sebanyak 3 kali selama 7 hari.'),
(60, 'Ongkos kirim dari donasi', 'Setiap transaksi donasi. Ongkos kirim di tangguhkan kepada penerima donasi.');

-- --------------------------------------------------------

--
-- Struktur dari tabel `list_request`
--

CREATE TABLE `list_request` (
  `id` int(11) NOT NULL,
  `queries` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `list_request`
--

INSERT INTO `list_request` (`id`, `queries`) VALUES
(64, 'berter');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `list_queries`
--
ALTER TABLE `list_queries`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `list_request`
--
ALTER TABLE `list_request`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `list_queries`
--
ALTER TABLE `list_queries`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=75;

--
-- AUTO_INCREMENT untuk tabel `list_request`
--
ALTER TABLE `list_request`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
