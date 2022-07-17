<?= $this->extend('frontend/layouts/main'); ?>
<?= $this->section('content'); ?>

<div>
    <div class="container">
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
                        <a href="#" class="text-decoration-none fw-bold text-danger" data-bs-toggle="modal" data-bs-target="#ModalHapusAkun">
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
                                    Harap diperhatikan, penghapusan akun bersifat final. Tidak akan ada cara untuk memulihkan akun Anda.
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

                <div class="col">
                    <div class="row d-flex justify-content-between">
                        <div class="d-flex flex-column flex-lg-row justify-content-between">
                            <div class="w-100 me-lg-4">
                                <input type="Alamat" placeholder="Cari alamat   " class="form-control"
                                    id="exampleAlamat" aria-describedby="AlamatHelp">
                            </div>                            
                            <!-- Button trigger modal for ALamat -->
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#tambahAlamatModal">Tambah</button>
                        </div>
                    </div>
                    <div class="row d-flex justify-content-between" style="margin-top: 34px;">
                        <div class="col-10">
                            <p style="margin-bottom: 10px; font-size: 14px; font-weight: bold;">Alamat Kos <span
                                    class="badge text-bg-primary"
                                    style="background-color: rgba(0, 52, 240, 0.2) !important; color: #0034F0 !important;">
                                    Utama </span> </p>
                            <p style="margin-bottom: 10px; font-size: 18px;">Mutiara Azzahra</p>
                            <p style="margin-bottom: 10px; font-size: 16px;">6289542259001</p>
                            <p style="margin-bottom: 10px; font-size: 16px;">Rancamanyar , RT 2 RW 26 </p>
                            <div class="row d-flex align-items-center" style="margin-bottom: 20px;">
                                <div class="col-1 d-flex justify-content-center">
                                    <img src="../Asset/yang ini.JPG" alt="" style="border-radius: 50%; width: 20px;">
                                </div>
                                <div class="col-11" style="padding-left: 0;">
                                    <p class="m-0" style="font-size: 16px;">Sudah Pinpoint</p>
                                </div>
                            </div>
                            <!-- Button trigger modal for ALamat -->
                            <a href="#" class="text-decoration-none"
                                data-bs-toggle="modal" data-bs-target="#editAlamatModal">
                                Ubah Alamat
                            </a>
                        </div>
                        <div class="col-2 m-auto d-flex justify-content-end">
                            <div class="btn btn-primary" style="background: #0034F0; ">Pilih</div>
                        </div>
                        <hr style="margin: 5px 0 ;">
                    </div>
                </div>
            </div>
</div>

                            
<!------------------- MODAL TAMBAH ALAMAT ----------------------->
<div class="modal fade" id="tambahAlamatModal" tabindex="-1" aria-labelledby="tambahAlamatModal"
    aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <!-- Label Alamat -->
                <label for="LabelALamat" class="form-label fw-bold mt-3"> Label Alamat </label>
                <input type="text" placeholder="Alamat Rumah" class="form-control" id="Alamat" aria-describedby="Alamat">
                <!-- Nama Penerima -->
                <div class="col-12">
                    <div class="row d-flex justify-content-between">
                        <div class="col-7">
                                <label for="Nama Penerima" class="form-label mt-3 fw-bold"> Nama Penerima</label>
                                <input type="text" placeholder="Nama Penerima" class="form-control" id="Nama Penerima" aria-describedby="Nama Penerima">
                        </div>
                        <!-- No Ponsel -->
                        <div class="col-5">
                            <label for="Number" class="form-label fw-bold mt-3"> No. Ponsel </label>
                            <input type="number" placeholder="+628 - 0000 - 0000 - 00"
                                class="form-control" id="Number" aria-describedby="Number">
                        </div>
                    </div>
                </div>
                <!-- Kota / Kecamatan -->
                <div class="col-12">
                    <div class="row d-flex justify-content-between">
                        <div class="col-9">
                                <label for="Kota atau Kecamatan" class="form-label fw-bold mt-3"> Kota atau Kecamatan</label>
                                <input type="text" placeholder="Jawa Barat, Kota Bandung, Cibeunying Kidul" class="form-control" id="Kota atau Kecamatan" aria-describedby="Kota atau Kecamatan">
                        </div>
                        <!-- Kode Pos -->
                        <div class="col-3">
                            <label for="Number" class="form-label fw-bold mt-3"> Kode Pos </label>
                            <input type="number" placeholder="40124" class="form-control"
                                id="Number" aria-describedby="Number">
                        </div>
                    </div>
                </div>
                <!-- Alamat  -->
                <label for="Alamat" class="form-label fw-bold mt-3">Alamat</label>
                <input type="text" placeholder="Gg. Sukapada, RT 3 rw 15 193 - 139a"
                    class="form-control" id="Alamat" aria-describedby="Alamat">
                <!-- Alamat Map -->
                <label for="Alamat Map" class="form-label fw-bold mt-3"> Alamat Map</label>
                <input type="text" class="form-control" id="Alamat Map"
                    aria-describedby="Alamat Map">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                    Keluar </button>
                <button type="button" class="btn btn-primary"> Simpan Alamat</button>
            </div>
        </div>
    </div>
</div>
<!------------------- END OF MODAL TAMBAH ALAMAT ----------------------->

                            
<!------------------- MODAL EDIT ALAMAT ----------------------->
<div class="modal fade" id="editAlamatModal" tabindex="-1" aria-labelledby="editAlamatModal"
    aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <!-- Label Alamat -->
                <label for="LabelALamat" class="form-label fw-bold mt-3"> Label Alamat </label>
                <input type="text" placeholder="Alamat Rumah" class="form-control" id="Alamat" aria-describedby="Alamat">
                <!-- Nama Penerima -->
                <div class="col-12">
                    <div class="row d-flex justify-content-between">
                        <div class="col-7">
                                <label for="Nama Penerima" class="form-label mt-3 fw-bold"> Nama Penerima</label>
                                <input type="text" placeholder="Nama Penerima" class="form-control" id="Nama Penerima" aria-describedby="Nama Penerima">
                        </div>
                        <!-- No Ponsel -->
                        <div class="col-5">
                            <label for="Number" class="form-label fw-bold mt-3"> No. Ponsel </label>
                            <input type="number" placeholder="+628 - 0000 - 0000 - 00"
                                class="form-control" id="Number" aria-describedby="Number">
                        </div>
                    </div>
                </div>
                <!-- Kota / Kecamatan -->
                <div class="col-12">
                    <div class="row d-flex justify-content-between">
                        <div class="col-9">
                                <label for="Kota atau Kecamatan" class="form-label fw-bold mt-3"> Kota atau Kecamatan</label>
                                <input type="text" placeholder="Jawa Barat, Kota Bandung, Cibeunying Kidul" class="form-control" id="Kota atau Kecamatan" aria-describedby="Kota atau Kecamatan">
                        </div>
                        <!-- Kode Pos -->
                        <div class="col-3">
                            <label for="Number" class="form-label fw-bold mt-3"> Kode Pos </label>
                            <input type="number" placeholder="40124" class="form-control"
                                id="Number" aria-describedby="Number">
                        </div>
                    </div>
                </div>
                <!-- Alamat  -->
                <label for="Alamat" class="form-label fw-bold mt-3">Alamat</label>
                <input type="text" placeholder="Gg. Sukapada, RT 3 rw 15 193 - 139a"
                    class="form-control" id="Alamat" aria-describedby="Alamat">
                <!-- Alamat Map -->
                <label for="Alamat Map" class="form-label fw-bold mt-3"> Alamat Map</label>
                <input type="text" class="form-control" id="Alamat Map"
                    aria-describedby="Alamat Map">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                    Keluar </button>
                <button type="button" class="btn btn-primary"> Simpan Alamat</button>
            </div>
        </div>
    </div>
</div>
<!------------------- END OF MODAL TAMBAH ALAMAT ----------------------->

<loadcss-profile_alamat></loadcss-profile_alamat>
<loadcss-main></loadcss-main>
<loadjs-profile_alamat></loadjs-profile_alamat>
<?= $this->endSection(); ?>