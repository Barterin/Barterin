<?= $this->extend('frontend/layouts/main'); ?>
<?= $this->section('content'); ?>

<div class="container mt-3 mb-3">
    <div class="fw-bold fs-2">
        <span>Barangku</span>
    </div>
    <div id="listBarang">

    </div>
</div>

<loadjs-barangku></loadjs-barangku>
<loadcss-barangku></loadcss-barangku>

<?= $this->endSection(); ?>