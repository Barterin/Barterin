<?= $this->extend('frontend/layouts/main'); ?>
<?= $this->section('content'); ?>

<div class="container mt-5">
    <div class="row">
    <form id="formUploadBarang">
        <h1>Upload Barang</h1>
        <div class="col-3 text-center mt-4">
            <img src="../Asset/profile.png" class="mb-3" style="width: 100%; border-radius: 5px;" alt="">
            <div class="input-group mb-3">
                <input type="file" class="form-control" id="inputGroupFile01" name="photo[]" multiple>
            </div>
        </div>
        <div class="col-9">
            <div class="form-group">
                <label for="">Nama Barang</label>
                <input class="form-control mt-1 shadow-none" type="text" name="name">
                <div id="validate_name"></div>
            </div>
            <div class="form-group">
                <label class="mt-2">Kategori Barang</label>
                <select class="form-select mt-1" id="itemsCategory">
                    <option selected disabled> Pilih Kategori Barang</option>
                </select>
            </div>
            <div class="form-group">
                <label class="mt-2">Tipe Barang</label>
                <select class="form-select mt-1" id="itemsType" name="type_id">
                    <option selected disabled> Pilih Tipe Barang</option>
                </select>
                <div id="validate_type_id"></div>
            </div>
            <div class="row">
                <div class="col-6">
                    <div class="form-group">
                        <label class="mt-2" for="">Lama Pemakaian</label>
                        <input class="form-control mt-1 shadow-none" type="number" name="used_time">
                        <div id="validate_used_time"></div>
                    </div>
                </div>
                <div class="col-6">
                    <div class="form-group">
                        <label class="mt-2" for="">Satuan Lama</label>
                        <select class="form-control mt-1 shadow-none" name="date_unit">
                            <option value="0" selected>Hari</option>
                            <option value="1">Minggu</option>
                            <option value="2">Bulan</option>
                            <option value="3">Tahun</option>
                        </select>
                        <div id="validate_date_unit"></div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="mt-2" for="">Kisaran Harga Beli</label>
                <input class="form-control mt-1 shadow-none" type="number" name="purchase_price">
                <div id="validate_purchase_price"></div>
            </div>
            <div class="form-group">
                <label class="mt-2" for="">Deskripsi Barang</label>
                <textarea class="form-control mt-1 shadow-none" type="text" name="description"></textarea>
                <div id="validate_description"></div>
            </div>

            <div class="form-group">
                <label class="mt-2" for="">Barang Untuk</label>
                <select name="item_for" class="form-control">
                    <option value="0" selected>Barter</option>
                    <option value="1">Donasi</option>
                </select>
            </div>

            <div class="form-group">
                <label class="mt-2">Alamat Barang</label>
                <select class="form-select mt-1" style="margin-bottom: 40px ;" id="userAddress" name="address_id">
                    <option selected disabled> Pilih Dari Alamat Kamu</option>
                </select>
            </div>

            <div class="form-group">
                <label class="mt-2">Keyword</label>
                <select class="form-select mt-1" style="margin-bottom: 40px ;" id="keyword" name="keyword">
                    <option selected disabled> Pilih Dari Alamat Kamu</option>
                </select>
            </div>

            <div class="d-flex justify-content-end">
                <button type="submit" class="btn btn-primary btn-sm" style="margin-bottom: 30px ;"> Tambah </button>
            </div>
        </div>
    </div>
    </form>
</div>

<loadjs-uploadBarang></loadjs-uploadBarang>

<?= $this->endSection(); ?>