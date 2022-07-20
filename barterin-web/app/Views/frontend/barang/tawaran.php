<?= $this->extend('frontend/layouts/main'); ?>
<?= $this->section('content'); ?>

<div class="container mt-4">
            <div class="card">
                <div class="card-body">
                    <div class="row">
                        <div class="col-6">
                            <h1>Barang yang Ditawar</h1>
                            <div class="row">
                                <div class="col-4">
                                    <img
                                        src="../../assets/image/ps-joystick.jpg"
                                        alt=""
                                        style="width: 100%; margin-top: 10px"
                                    />
                                </div>
                                <div class="col-8">
                                    <label
                                        for="inputPassword5"
                                        class="form-label"
                                        style="font-weight: bold"
                                        >Nama Produk
                                    </label>
                                    <input
                                        type="password"
                                        id="inputPassword5"
                                        class="form-control"
                                        aria-describedby="passwordHelpBlock"
                                    />
                                    <label
                                        for="inputPassword5"
                                        class="form-label"
                                        style="
                                            font-weight: bold;
                                            margin-top: 10px;
                                        "
                                    >
                                        Lokasi
                                    </label>
                                    <input
                                        type="password"
                                        id="inputPassword5"
                                        class="form-control"
                                        aria-describedby="passwordHelpBlock"
                                    />
                                    <div class="d-flex align-items-center">
                                        <img
                                            src="../../assets/image/profile.png"
                                            alt=""
                                            style="width: 30px"
                                            class="mt-2"
                                        />
                                        <h2
                                            style="font-size: 16px"
                                            class="m-0 p-0 ms-2"
                                        >
                                            Nama Pemilik
                                        </h2>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-6">
                            <h1>Tawaran</h1>
                            <div class="row ">
                                <div class="col-4 text-center">
                                    <img
                                        src="../../assets/image/ps-joystick.jpg"
                                        alt=""
                                        style="width: 100%; margin-top: 10px"
                                    />
                                    <!-- Button trigger modal -->
                                    <button
                                        type="button"
                                        class="btn btn-primary mt-2"
                                        data-bs-toggle="modal"
                                        data-bs-target="#exampleModal"
                                    >
                                        Launch demo modal
                                    </button>

                                    <!-- Modal -->
                                    <div
                                        class="modal fade "
                                        id="exampleModal"
                                        tabindex="-1"
                                        aria-labelledby="exampleModalLabel"
                                        aria-hidden="true"
                                    >
                                        <div class="modal-dialog modal-lg modal-dialog-centered ">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5
                                                        class="modal-title"
                                                        id="exampleModalLabel"
                                                    >
                                                        Daftar Barang
                                                    </h5>
                                                    <button
                                                        type="button"
                                                        class="btn-close"
                                                        data-bs-dismiss="modal"
                                                        aria-label="Close"
                                                    ></button>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="option1" checked>
                                                        <label class="form-check-label" for="exampleRadios1">
                                                          Default radio
                                                        </label>
                                                      </div>
                                                      <div class="form-check">
                                                        <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios2" value="option2">
                                                        <label class="form-check-label" for="exampleRadios2">
                                                          Second default radio
                                                        </label>
                                                      </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button
                                                        type="button"
                                                        class="btn btn-secondary"
                                                        data-bs-dismiss="modal"
                                                    >
                                                        Close
                                                    </button>
                                                    <button
                                                        type="button"
                                                        class="btn btn-primary"
                                                    >
                                                        Save changes
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-8">
                                    <label
                                        for="inputPassword5"
                                        class="form-label"
                                        style="font-weight: bold"
                                        >Nama Produk
                                    </label>
                                    <input
                                        type="password"
                                        id="inputPassword5"
                                        class="form-control"
                                        aria-describedby="passwordHelpBlock"
                                    />
                                    <label
                                        for="inputPassword5"
                                        class="form-label"
                                        style="
                                            font-weight: bold;
                                            margin-top: 10px;
                                        "
                                    >
                                        Lokasi
                                    </label>
                                    <input
                                        type="password"
                                        id="inputPassword5"
                                        class="form-control"
                                        aria-describedby="passwordHelpBlock"
                                    />
                                    <div class="d-flex align-items-center">
                                        <img
                                            src="../../assets/image/profile.png"
                                            alt=""
                                            style="width: 30px"
                                            class="mt-2"
                                        />
                                        <h2
                                            style="font-size: 16px"
                                            class="m-0 p-0 ms-2"
                                        >
                                            Nama Pemilik
                                        </h2>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

<?= $this->endSection(); ?>