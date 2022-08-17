<?= $this->extend('frontend/layouts/main'); ?>
<?= $this->section('content'); ?>

<div class="container mt-3 mb-3">
    <div class="fw-bold fs-2">
        <span>Search Result</span>
    </div>
    <div id="searchResult" class="row">
        <input type="hidden" value="<?= $search ?>" id="searchRes">

    </div>
</div>

<loadjs-search></loadjs-search>
<!-- <loadcss-barangku></loadcss-barangku> -->

<?= $this->endSection(); ?>