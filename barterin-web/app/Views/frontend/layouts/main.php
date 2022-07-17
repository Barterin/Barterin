<?= $this->include('frontend/layouts/head'); ?>
<?= $this->include('frontend/layouts/navbar'); ?>
<main>
    <?= $this->renderSection('content'); ?>
</main>

<?= $this->include('frontend/layouts/foot'); ?>