<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="base-url" content="<?= getenv('app.baseURL') ?>">
    <meta name="api-url" content="<?= getenv('app.apiUrl') ?>">
    <meta name="socket-url" content="<?= getenv('app.socketUrl') ?>">
    <script defer src="<?= getenv('app.baseURL') ?>/assets/js/app.js"></script>
    <title class="webTitle"><?= $title ?? "Barterin" ?></title>
</head>