<?= $this->extend('frontend/layouts/main'); ?>
<?= $this->section('content'); ?>

<div class="container mt-5">
        <div class="row">
            <h1>Upload Barang</h1>
            <div class="col-3 text-center mt-4">
                <img src="../Asset/profile.png" class="mb-3" style="width: 100%; border-radius: 5px;" alt="">
                <div class="input-group mb-3">
                    <input type="file" class="form-control" id="inputGroupFile01">
                </div>
            </div>
            <div class="col-9">
                <label for="inputPassword5" class="form-label" style="margin-top: 30px; "
                        >Nama Produk </label
                    >
                    <input
                        type="password"
                        id="inputPassword5"
                        class="form-control"
                        aria-describedby="passwordHelpBlock"
                    />
                    <label for="inputPassword5" class="form-label" style="margin-top: 20px ;"
                        >Jenis Produk </label
                    >
                    <input
                        type="password"
                        id="inputPassword5"
                        class="form-control"
                        aria-describedby="passwordHelpBlock"
                    />
                    <label for="inputPassword5" class="form-label" style="margin-top: 20px ;"
                        >Lama Pemakaian</label
                    >
                    <input
                        type="password"
                        id="inputPassword5"
                        class="form-control"
                        aria-describedby="passwordHelpBlock"
                    />
                    <label for="inputPassword5" class="form-label" style="margin-top: 20px ;"
                    >Kisaran Harga Beli</label
                     >
                     <input
                    type="password"
                    id="inputPassword5"
                    class="form-control"
                    aria-describedby="passwordHelpBlock"
                    />
                    <label for="inputPassword5" class="form-label" style="margin-top: 20px ;"
                    >Deskripsi Barang</label
                     >
                     <input
                    type="password"
                    id="inputPassword5"
                    class="form-control"
                    aria-describedby="passwordHelpBlock"
                    />
                    <label for="inputPassword5" class="form-label" style="margin-top: 20px ;"
                    >Lokasi</label
                     >
                    <select class="form-select mt-1" aria-label="Default select example" style="margin-bottom: 40px ;">
                        <option selected> Pilih Lokasi</option>
                        <option value="1">Nanggroe Aceh Darussalam</option>
                        <option value="2">Sumatera Utara</option>
                        <option value="3">Sumatera Selatan</option>
                        <option value="4">Sumatera Barat</option>
                        <option value="5">Bengkulu</option>
                        <option value="6">Riau</option>
                        <option value="7">Kepulauan Riau</option>
                        <option value="8"> Jambi</option>
                        <option value="9">Lampung</option>
                        <option value="10">Bangka Belitung</option>
                        <option value="11">Banten</option>
                        <option value="12">DKI Jakarta</option>
                        <option value="13">Jawa Barat</option>
                        <option value="14"> Jawa Tengah</option>
                        <option value="15"> Yogyakarta</option>
                        <option value="16">Jawa Timur</option>
                        <option value="17">Kalimantan Timur</option>
                        <option value="18">Kalimantan Barat</option>
                        <option value="19">Kalimantan Tengah</option>
                        <option value="20">Kalimantan Selatan</option>
                        <option value="21">Kalimantan Utara</option>
                        <option value="22">Bali: Denpasar</option>
                        <option value="23"> Nusa Tenggara Timur</option>
                        <option value="24">Nusa Tenggara Barat</option>
                        <option value="25">Gorontalo</option>
                        <option value="26">Sulawesi Barat</option>
                        <option value="27">Sulawesi Tengah</option>
                        <option value="28">Sulawesi Utara</option>
                        <option value="29"> Sulawesi Tenggara</option>
                        <option value="30">Sulawesi Selatan</option>
                        <option value="31"> Maluku Utara</option>
                        <option value="32"> Maluku</option>
                        <option value="33">Papua Barat</option>
                        <option value="34"> Papua</option>
                        <option value="35">Papua Selatan</option>
                        <option value="36">Papua Tengah</option>
                        <option value="37">Papua Pegunungan</option>
                      </select>

                      <div class="d-flex justify-content-end">
                          <button type="button" class="btn btn-primary btn-sm" style="margin-bottom: 30px ;"> Tambah </button>
                      </div>
            </div>
        </div>
    </div>

<?= $this->endSection(); ?>