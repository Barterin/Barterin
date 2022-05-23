<?= $this->include('frontend/layouts/head'); ?>
<!-- <?= $this->include('frontend/layouts/nav'); ?> -->

<body id="contentId">
    <?= $this->renderSection('content'); ?>
</body>

<?= $this->include('frontend/layouts/foot'); ?>