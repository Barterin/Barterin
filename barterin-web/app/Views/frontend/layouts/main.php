<?= $this->include('frontend/layouts/head'); ?>

<main>
    <?= $this->include('frontend/layouts/navbar'); ?>
    <?= $this->renderSection('content'); ?>
</main>

<?= $this->include('frontend/layouts/foot'); ?>