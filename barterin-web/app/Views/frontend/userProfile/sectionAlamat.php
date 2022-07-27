<div class="col">
    <div class="row d-flex justify-content-between">
        <div class="d-flex flex-column flex-lg-row justify-content-between">
            <div class="w-100 me-lg-4">
                <input type="Alamat" placeholder="Cari alamat   " class="form-control" id="exampleAlamat"
                    aria-describedby="AlamatHelp">
            </div>
            <!-- Button trigger modal for ALamat -->
            <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                data-bs-target="#tambahAlamatModal">Tambah</button>
        </div>
    </div>
    <div id="addressList">

    </div>
</div>

<!------------------- MODAL TAMBAH ALAMAT ----------------------->
<div class="modal fade" id="tambahAlamatModal" tabindex="-1" aria-labelledby="tambahAlamatModal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <form id="formAlamat">
                    <!-- Label Alamat -->
                    <label for="LabelALamat" class="form-label fw-bold mt-3"> Label Alamat </label>
                    <input type="text" placeholder="Alamat Rumah" class="form-control" id="Alamat"
                        aria-describedby="Alamat" name="label">
                    <!-- Nama Penerima -->
                    <div class="col-12">
                        <div class="row d-flex justify-content-between">
                            <div class="col-7">
                                <label for="Nama Penerima" class="form-label mt-3 fw-bold"> Nama Penerima</label>
                                <input type="text" placeholder="Nama Penerima" class="form-control" id="Nama Penerima"
                                    aria-describedby="Nama Penerima" name="penerima">
                            </div>
                            <!-- No Ponsel -->
                            <div class="col-5">
                                <label for="Number" class="form-label fw-bold mt-3"> No. Ponsel </label>
                                <input type="number" placeholder="+628 - 0000 - 0000 - 00" class="form-control"
                                    id="Number" aria-describedby="Number" name="nohp">
                            </div>
                        </div>
                    </div>
                    <!-- Kota / Kecamatan -->
                    <div class="col-12">
                        <div class="row d-flex justify-content-between">
                            <div class="col-9">
                                <label for="Kota atau Kecamatan" class="form-label fw-bold mt-3"> Kota atau
                                    Kecamatan</label>
                                <input type="text" placeholder="Jawa Barat, Kota Bandung, Cibeunying Kidul"
                                    class="form-control" id="Kota atau Kecamatan" aria-describedby="Kota atau Kecamatan"
                                    name="kota_kecamatan">
                            </div>
                            <!-- Kode Pos -->
                            <div class="col-3">
                                <label for="Number" class="form-label fw-bold mt-3"> Kode Pos </label>
                                <input type="number" placeholder="40124" class="form-control" id="Number"
                                    aria-describedby="Number" name="kode_pos">
                            </div>
                        </div>
                    </div>
                    <!-- Alamat  -->
                    <label for="Alamat" class="form-label fw-bold mt-3">Alamat</label>
                    <input type="text" placeholder="Gg. Sukapada, RT 3 rw 15 193 - 139a" class="form-control"
                        id="Alamat" aria-describedby="Alamat" name="alamat_lengkap">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                    Keluar </button>
                <button type="submit" class="btn btn-primary"> Simpan Alamat</button>
            </div>
            </form>
        </div>
    </div>
</div>
<!------------------- END OF MODAL TAMBAH ALAMAT ----------------------->


<!------------------- MODAL EDIT ALAMAT ----------------------->
<div class="modal fade" id="editAlamatModal" tabindex="-1" aria-labelledby="editAlamatModal" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
        <div class="modal-content">
            <div class="modal-body">
                <!-- Label Alamat -->
                <label for="LabelALamat" class="form-label fw-bold mt-3"> Label Alamat </label>
                <input type="text" placeholder="Alamat Rumah" class="form-control" id="Alamat"
                    aria-describedby="Alamat">
                <!-- Nama Penerima -->
                <div class="col-12">
                    <div class="row d-flex justify-content-between">
                        <div class="col-7">
                            <label for="Nama Penerima" class="form-label mt-3 fw-bold"> Nama Penerima</label>
                            <input type="text" placeholder="Nama Penerima" class="form-control" id="Nama Penerima"
                                aria-describedby="Nama Penerima">
                        </div>
                        <!-- No Ponsel -->
                        <div class="col-5">
                            <label for="Number" class="form-label fw-bold mt-3"> No. Ponsel </label>
                            <input type="number" placeholder="+628 - 0000 - 0000 - 00" class="form-control" id="Number"
                                aria-describedby="Number">
                        </div>
                    </div>
                </div>
                <!-- Kota / Kecamatan -->
                <div class="col-12">
                    <div class="row d-flex justify-content-between">
                        <div class="col-9">
                            <label for="Kota atau Kecamatan" class="form-label fw-bold mt-3"> Kota atau
                                Kecamatan</label>
                            <input type="text" placeholder="Jawa Barat, Kota Bandung, Cibeunying Kidul"
                                class="form-control" id="Kota atau Kecamatan" aria-describedby="Kota atau Kecamatan">
                        </div>
                        <!-- Kode Pos -->
                        <div class="col-3">
                            <label for="Number" class="form-label fw-bold mt-3"> Kode Pos </label>
                            <input type="number" placeholder="40124" class="form-control" id="Number"
                                aria-describedby="Number">
                        </div>
                    </div>
                </div>
                <!-- Alamat  -->
                <label for="Alamat" class="form-label fw-bold mt-3">Alamat</label>
                <input type="text" placeholder="Gg. Sukapada, RT 3 rw 15 193 - 139a" class="form-control" id="Alamat"
                    aria-describedby="Alamat">
                <!-- Alamat Map -->
                <label for="Alamat Map" class="form-label fw-bold mt-3"> Alamat Map</label>
                <input type="text" class="form-control" id="Alamat Map" aria-describedby="Alamat Map">
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

<loadjs-profile_alamat></loadjs-profile_alamat>