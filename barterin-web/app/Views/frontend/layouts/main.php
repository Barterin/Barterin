<?= $this->include('frontend/layouts/head'); ?>
<?= $this->include('frontend/layouts/nav'); ?>

<main>
    <?= $this->renderSection('content'); ?>
</main>

<?= $this->include('frontend/layouts/foot'); ?>