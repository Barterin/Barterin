<?= $this->extend('frontend/layouts/main'); ?>
<?= $this->section('content'); ?>

<div class="container mt-3 mb-3">
    <div>
        <div class="fw-bold fs-2">
            <span>Tawaranku</span>
        </div>
        <div id="listTawaran">

        </div>
    </div>
    <div>
        <div class="fw-bold fs-2">
            <span>Barang tawaran yang telah diterima</span>
        </div>
        <div id="listTawaranAcc">

        </div>
    </div>
</div>

<loadjs-tawaranku></loadjs-tawaranku>
<loadcss-tawaranku></loadcss-tawaranku>

<?= $this->endSection(); ?>