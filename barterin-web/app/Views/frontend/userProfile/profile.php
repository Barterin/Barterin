<?= $this->extend('frontend/layouts/main'); ?>
<?= $this->section('content'); ?>

<div class="container mb-5">
    <div class="container px-4">
        <div class="row gx-5 mt-3">
            <div class="d-flex flex-row align-items-center" id="profile">
                <div id="profile-picture">

                </div>
                <div class="row user-name">
                    <p class="fs-3 fw-bold" id="name-menu"></p>
                    <!-- <p class="fs-6">Set up your account and start barter</p> -->
                    <p class="fs-6"><?= $subTitle ?></p>
                </div>
            </div>
        </div>
        <div class="row d-flex flex-lg-row flex-column mt-4">
            <div class="col-lg-3 menu">
                <div class="row">
                    <a href="<?php $base_url ?>/profile/biodata"
                        class="bio-menu <?= $section == "sectionBiodata" ? 'active' : '' ?> internal">
                        Biodata Diri
                    </a>
                    <a href="<?php $base_url ?>/profile/alamat"
                        class="bio-menu <?= $section == "sectionAlamat" ? 'active' : '' ?> internal">
                        Daftar Alamat
                    </a>
                    <a href="#" class="bio-menu internal">
                        Pembayaran
                    </a>
                    <a href="#" class="bio-menu internal">
                        Rekening Bank</a>
                    <a href="#" class="bio-menu internal">
                        Notifikasi</a>
                    <a href="#" class="bio-menu internal">
                        Keamanan
                    </a>
                    <!-- Button trigger modal -->
                    <a href="#" class="text-decoration-none fw-bold text-danger" data-bs-toggle="modal"
                        data-bs-target="#ModalHapusAkun">
                        Delete Account
                    </a>
                </div>
            </div>

            <!-- Main Biodata Section  -->

            <?= $this->include('frontend/userProfile/' . $section) ?>

            <!-- End Main Biodata Section  -->

        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="ModalHapusAkun" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
<loadcss-profile_biodata></loadcss-profile_biodata>
<loadjs-profile_biodata></loadjs-profile_biodata>
<?= $this->endSection(); ?>