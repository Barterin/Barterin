<?= $this->extend('frontend/layouts/main'); ?>
<?= $this->section('content'); ?>

<div class="body mt-5 mb-5">
    <input type="hidden" value="<?= $idBarang ?>" id="idBarang">
    <div class="container">
        <div class="row">
            <div class="foto">
                <img src="../../assets/image/ps-joystick.jpg" alt="" width="300px" height="300px" class="border rounded"
                    id="productImage">
            </div>
            <div class="col">
                <div class="header">
                    <h4 id="productName"></h4>
                    <div class="container">
                        <div class="row">
                            <div class="col-4">
                                <p>4.9(50 ulasan)</p>
                            </div>
                            <div class="col">
                                <p>Diskusi (28)</p>
                            </div>
                        </div>
                    </div>
                    <b class="text-secondary">Estimasi Harga</b>
                    <h3>Rp299.00</h3>
                    <hr style="border: 1px solid black">
                    <div class="row">
                        <div class="col text-primary"><b>Detail</b></div>
                    </div>
                    <hr style="border: 1px solid black">
                    <p>Kondisi: Lorem Ipsum</p>
                    <p>Berat: Lorem Ipsum</p>
                    <p>Kategori: Lorem Ipsum</p>
                    <p>Etalase: Lorem Ipsum</p>
                    <p>Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat
                        duis enim velit mollitAmet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint.
                        Velit officia consequat duis enim velit mollit</p>
                    <p>Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint.</p>
                    <b class="text-primary">Lihat Selengkapnya</b>
                    <hr style="border: 1px solid black">
                    <hr style="border: 1px solid black">
                    <div>
                        <h5>Pengiriman</h5>
                        <div class="container">
                            Dilayani oleh <b>Barterin</b>
                            <p>Barang dikirim dari gudang Barterin</p>
                            <p>Ongkir Reguler 9rb - 10rb</p>
                            <p>Estimasi tiba besok - 5 hari kedepan</p>
                            Kurir lainnya : <span class="text-primary" style="float:right;"><b>Lihat pilihan
                                    kurir</b></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="container mb-4"
                    style="outline-style:solid; outline-color: #CDCDCD; border-radius:4px; height:10%; padding: 20px;">
                    <div class="row-10">
                        <div class="d-flex align-item-center">
                            <div class="justify-content-start col-8">
                                <img src="../../assets/image/profile.png" class="mb-3"
                                    style="width: 40px; border-radius: 5px;" alt="">
                                <b id="user-name"></b>
                            </div>
                            <div class="justify-content-center col-2">
                                <button class="btn btn-outline-primary">Chat</button><br><br>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="container mb-2 pb-2"
                    style="outline-style:solid; outline-color: #CDCDCD; border-radius:4px;">
                    <div class="">
                        <button class="btn btn-primary form-control mt-3   mb-2" style="font-weight:bold;"> +
                            Keranjang</button>
                        <button class="btn btn-outline-primary form-control mb-2" style="font-weight:bold;"> Ikuti
                            Diskusi</button>
                    </div>
                    <div class="container">
                        <div class="d-flex justify-content-center fw-bold">
                            <a href="" class="text-secondary text-decoration-none"> Share </a>
                        </div>
                    </div>
                </div>
                <div class="container mt-4 bg-secondary"
                    style="outline-style:solid; outline-color: #CDCDCD; border-radius:4px; height:20%;">
                </div>
            </div>
        </div>
    </div>
    <div class="container ulasan-container">
        <h5>ULASAN(28)</h5>
        <p>Amet minim mollit non deserunt ullamco est sit allqua</p>
        <div class="row">
            <div class="col">
                <div class="row">
                    <div class="col-5"><span style="font-size: 90px; font-weight:700;">5.0</span>/5</div>
                </div>
                <span class="text-center">(28) Ulasan</span>
            </div>
            <div class="col">
                <p>5</p>
                <p>4</p>
                <p>3</p>
                <p>2</p>
                <p>1</p>
            </div>
            <div class="col">
                <p>20</p>
                <p>0</p>
                <p>0</p>
                <p>0</p>
                <p>0</p>
            </div>
        </div>
    </div>
</div>

<loadjs-detailBarangDonasi></loadjs-detailBarangDonasi>

<?= $this->endSection(); ?>