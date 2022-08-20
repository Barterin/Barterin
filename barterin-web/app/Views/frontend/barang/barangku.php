<?= $this->extend('frontend/layouts/main'); ?>
<?= $this->section('content'); ?>

<div class="container mt-3 mb-3">
    <div>
        <div>
        <div class="fw-bold fs-2">
                <span>Barangku</span>
            </div>
            <div id="listBarang">

        </div>
    </div>
    <div class="mt-3">
        <div class="fw-bold fs-2">
            <span>Donated</span>
        </div>
        <div id="listBarangSold">

        </div>
    </div>
</div>

<loadjs-barangku></loadjs-barangku>
<loadcss-barangku></loadcss-barangku>

<?= $this->endSection(); ?>