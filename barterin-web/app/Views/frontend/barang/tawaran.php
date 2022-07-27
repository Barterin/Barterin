<?= $this->extend('frontend/layouts/main'); ?>
<?= $this->section('content'); ?>

<div class="container mt-4">
    <h3 class="fw-bold">Tawaran Rekomendasi</h3>
    <div class="card mt-3 item-card" >
        <div class="row g-0">
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title fw-bold" name="name">Gita Purnamasari</h5>
                    <p class="card-text" name="purchase_price">Jl. Kebon Kopi, Cimahi</p>
                    <p class="card-text" name="description">Saya membutuhkan barang ini untuk didaur ulang dan diperbaiki, untuk kebutuhan rumah tangga</p>
                    <p class="card-text" name="used_time"><small class="text-muted">+628559238972</small></p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card-body">
                    <button class="btn btn-primary" type="button">Chat Penawar</button>
                    <button class="btn btn-primary mt-1" type="button">Buat Kesepakatan</button>
                </div>
            </div>
        </div>
    </div>
    
    <h3 class="fw-bold mt-5">Semua Tawaran</h3>
    <div class="card mt-3 item-card" >
        <div class="row g-0">
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title fw-bold" name="name">Ibnu Rizqia Ramadhan</h5>
                    <p class="card-text" name="purchase_price">Jl. Rawalele, Subang</p>
                    <p class="card-text" name="description">Saya membutuhkan barang ini karena saya baru saja pindah kos dan barang yang tersedia masihlah sedkit, saya membutuhkan nya untuk melengkapi kebutuhan dalam menyimpan barang saya</p>
                    <p class="card-text" name="used_time"><small class="text-muted">+628559086722</small></p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card-body">
                    <button class="btn btn-primary" type="button">Chat Penawar</button>
                    <button class="btn btn-primary mt-1" type="button">Buat Kesepakatan</button>
                </div>
            </div>
        </div>
    </div>
    <div class="card mt-3 item-card" >
        <div class="row g-0">
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title fw-bold" name="name">Gita Purnamasari</h5>
                    <p class="card-text" name="purchase_price">Jl. Kebon Kopi, Cimahi</p>
                    <p class="card-text" name="description">Saya membutuhkan barang ini untuk didaur ulang dan diperbaiki, untuk kebutuhan rumah tangga</p>
                    <p class="card-text" name="used_time"><small class="text-muted">+628559238972</small></p>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card-body">
                    <button class="btn btn-primary" type="button">Chat Penawar</button>
                    <button class="btn btn-primary mt-1" type="button">Buat Kesepakatan</button>
                </div>
            </div>
        </div>
    </div>
</div>

<?= $this->endSection(); ?>