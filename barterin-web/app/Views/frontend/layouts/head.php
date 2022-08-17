<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="base-url" content="<?= base_url() ?>">
    <meta name="api-url" content="<?= getenv('app.apiUrl') ?>">
    <meta name="socket-url" content="<?= getenv('app.socketUrl') ?>">
    <meta name="environment" content="<?= getenv('CI_ENVIRONMENT') ?>">
    <meta name="access-token" content="<?= get_cookie("accessToken") ?>">
    <script defer
        src="<?= base_url() ?>/assets/js/app<?= getenv('CI_ENVIRONMENT') == "development" ? '' : '.min' ?>.js?<?= time() ?>">
    </script>
    <link rel="icon" type="image/x-icon" href="../../assets/image/favicon/barterin_2.ico">
    <title class="webTitle"><?= $title ?? "Barterin" ?></title>
</head>