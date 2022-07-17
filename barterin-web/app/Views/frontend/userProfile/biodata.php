<?= $this->extend('frontend/layouts/main'); ?>
<?= $this->section('content'); ?>

<div class="container mb-5">
    <div class="container px-4">
        <div class="row gx-5 mt-3">
            <div class="d-flex flex-row align-items-center" id="profile">
                <img src="../../assets/image/profile.png" alt="" class="profile-picture rounded-circle me-3" />
                <div class="row user-name">
                    <p class="fs-3 fw-bold">Name / Biodata Diri</p>
                    <p class="fs-6">Set up your account and start barter</p>
                </div>
            </div>
        </div>
        <div class="row d-flex flex-lg-row flex-column mt-4">
            <div class="col-lg-3 menu">
                <div class="row">
                    <a href="Biodata_Diri.html" class="fw-bold text-decoration-none text-body">
                        Biodata Diri
                    </a>
                    <a href="Daftar_Alamat.html" class="text-decoration-none text-body">
                        Daftar Alamat
                    </a>
                    <a href="Pembayaran.html" class="text-decoration-none text-body">
                        Pembayaran
                    </a>
                    <a href="Rekening_bank.html" class="text-decoration-none text-body">
                        Rekening Bank</a>
                    <a href="Notifikasi.html" class="text-decoration-none text-body">
                        Notifikasi</a>
                    <a href="Keamanan.html" class="text-decoration-none text-body">
                        Keamanan
                    </a>
                    <!-- Button trigger modal -->
                    <a href="#" class="text-decoration-none fw-bold text-danger" data-bs-toggle="modal"
                        data-bs-target="#ModalHapusAkun">
                        Delete Account
                    </a>
                </div>

                <!-- Modal -->
                <div class="modal fade" id="ModalHapusAkun" tabindex="-1" aria-labelledby="exampleModalLabel"
                    aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-body m-auto">
                                <p class="fs-3 fw-bold mt-3 text-center">
                                    Kami sedih melihat Anda pergi
                                </p>
                                <p class="text-center">
                                    Jika Anda ingin mengurangi notifikasi email, Anda dapat
                                    <a href="#" class="text-decoration-none">menonaktifkannya disini</a>
                                    atau jika Anda hanya ingin mengubah nama pengguna, Anda
                                    dapat <a href="#" class="text-decoration-none"> melakukannya di sini.</a>
                                </p>
                                <p class="text-center">
                                    Harap diperhatikan, penghapusan akun bersifat final. Tidak akan ada cara untuk
                                    memulihkan akun Anda.
                                </p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" data-bs-dismiss="modal">
                                    Batal
                                </button>
                                <button type="button" class="btn btn-danger">
                                    Hapus Akun
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- MODAL END -->
            </div>

            <div class="col" enctype="multipart/form-data">
                <div class="row">
                    <div class="col-1">
                        <img src="../../assets/image/profile.png" alt="" class="rounded circle profile-picture" />
                    </div>
                    <div class="mt-3">
                        <label for="formFile" class="form-label fw-bold">Upload Foto Profile</label>
                        <input type="file" class="form-control" id="formFile">
                    </div>
                </div>
                <!-- Nama -->
                <label for="Nama" class="form-label fw-bold mt-3">
                    Nama
                </label>
                <input type="text" placeholder="Nama Lengkap" class="form-control" id="nama" name="nama"
                    aria-describedby="nama" autofocus />

                <!-- Tanggal Lahir -->
                <label for="Tanggal Lahir" class="form-label fw-bold mt-3">
                    Tanggal Lahir</label>
                <input type="date" placeholder="Tanggal Lahir" class="form-control" id="tanggalLahir"
                    aria-describedby="tanggalLahir" />
                <!-- Jenis Kelamin -->
                <label for="Jenis Kelamin" class="form-label fw-bold mt-3">
                    Jenis Kelamin</label>
                <div class="dropdown d-grid gap-2">
                    <button class="text-start btn btn-light dropdown-toggle" type="button" id="jkDropdown"
                        data-bs-toggle="dropdown" aria-expanded="false">
                        Pilih Jenis Kelamin
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="jkDropdown">
                        <li class="dropdown-item" value="male">Laki-Laki</li>
                        <li class="dropdown-item" value="female">Perempuan</li>
                    </ul>
                </div>
                <label for="Number" class="form-label fw-bold mt-3">
                    Nomor HP
                </label>
                <input type="number" class="form-control" id="Number" aria-describedby="Number" />
                <div class="d-flex justify-content-end">
                    <button type="submit" class="btn btn-primary mt-3 align-items-end">
                        Simpan
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

<loadcss-profile_biodata></loadcss-profile_biodata>
<loadjs-biodata></loadjs-biodata>

<?= $this->endSection(); ?>