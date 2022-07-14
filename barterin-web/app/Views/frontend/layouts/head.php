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
    <title class="webTitle"><?= $title ?? "Barterin" ?></title>
</head>

<header>
    <nav class="navbar navbar-extend-lg bg-light sticky-top">
        <div class="container-fluid d-flex align-item-center">
            <div class="barterin-logo d-flex align-item-center">
                <svg id="a" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 937.53 204.77"><defs><style>.b{fill:#0034f0;}</style></defs><g><path class="b" d="M218.42,118.15v39.39c0,26.22-21.26,47.48-47.48,47.48H8.99c-4.64,0-8.4-3.76-8.4-8.4v-44.1c0-4.64,3.76-8.4,8.4-8.4H151.25c3.47,0,6.28-2.81,6.28-6.28h0c0-3.47-2.81-6.28-6.28-6.28H8.99c-4.64,0-8.4-3.76-8.4-8.4v-44.1c0-4.64,3.76-8.4,8.4-8.4H170.95c26.22,0,47.47,21.25,47.47,47.47Z"/><path class="b" d="M160.67,60.63H5.59c-2.76,0-5-2.24-5-5V4.74C.59,1.98,2.83-.26,5.59-.26H124.41c20.03,0,36.26,16.23,36.26,36.26v24.63Z"/></g><g><path d="M371,117.65c0,22.27-18.14,36.82-40.59,36.82h-44.41c-4.64,0-8.4-3.76-8.4-8.4V37.14c0-4.64,3.76-8.4,8.4-8.4h40.82c21.91,0,39.69,14.19,39.69,35.92,0,10.42-4.13,18.68-10.96,24.61,9.34,5.93,15.45,15.63,15.45,28.38Zm-64.66-61.97v22.27h20.48c6.47,0,10.96-4.67,10.96-11.14s-4.31-11.14-10.96-11.14h-20.48Zm35.92,59.81c0-7-4.67-12.03-11.85-12.03h-24.07v24.07h24.07c7.18,0,11.85-5.03,11.85-12.03Z"/><path d="M478.4,73.06v73.01c0,4.64-3.76,8.4-8.4,8.4h-10.15c-4.64,0-8.4-3.76-8.4-8.4v-.04c-5.93,6.82-14.73,10.96-26.76,10.96-23.53,0-42.93-20.65-42.93-47.42s19.4-47.42,42.93-47.42c12.03,0,20.84,4.13,26.76,10.96v-.04c0-4.64,3.76-8.4,8.4-8.4h10.15c4.64,0,8.4,3.76,8.4,8.4Zm-26.94,36.51c0-13.47-8.98-21.91-21.37-21.91s-21.37,8.44-21.37,21.91,8.98,21.91,21.37,21.91,21.37-8.44,21.37-21.91Z"/><path d="M552.04,72.72v12.11c0,4.25-3.17,7.94-7.4,8.34-9.65,.9-19.54,6.65-19.54,20.71v32.2c0,4.64-3.76,8.4-8.4,8.4h-10.15c-4.64,0-8.4-3.76-8.4-8.4V73.06c0-4.64,3.76-8.4,8.4-8.4h10.15c4.64,0,8.4,3.76,8.4,8.4v7.59c2.42-8.1,8.66-13.35,15.93-15.92,5.39-1.9,11.01,2.28,11.01,7.99Z"/><path d="M601.43,90.53v31.79c0,5.78,3.72,7.64,10.57,7.9,4.45,.17,7.93,3.93,7.93,8.38v8.06c0,4.47-3.51,8.2-7.98,8.39-28.8,1.21-37.46-9.61-37.46-32.74v-31.79h-5.97c-4.64,0-8.4-3.76-8.4-8.4v-9.07c0-4.64,3.76-8.4,8.4-8.4h5.97v-16.75c0-4.64,3.76-8.4,8.4-8.4h10.15c4.64,0,8.4,3.76,8.4,8.4v16.75h10.1c4.64,0,8.4,3.76,8.4,8.4v9.07c0,4.64-3.76,8.4-8.4,8.4h-10.1Z"/><path d="M679.02,132.92c5.26,0,9.97-1.47,13.54-3.74,2.59-1.65,5.83-1.85,8.49-.33l7.61,4.37c5.14,2.95,5.67,10.15,1.05,13.86-8.16,6.56-18.6,9.9-31.05,9.9-30.71,0-49.75-20.65-49.75-47.42s19.4-47.42,47.78-47.42c26.22,0,45.62,20.3,45.62,47.42,0,.97-.02,1.93-.07,2.88-.22,4.44-3.95,7.9-8.39,7.9h-56.38c3.41,9.34,11.68,12.57,21.55,12.57Zm16.53-32.69c-2.87-10.42-10.96-14.19-19.04-14.19-10.24,0-17.06,5.03-19.58,14.19h38.62Z"/><path d="M791.46,72.72v12.11c0,4.25-3.17,7.94-7.4,8.34-9.65,.9-19.54,6.65-19.54,20.71v32.2c0,4.64-3.76,8.4-8.4,8.4h-10.15c-4.64,0-8.4-3.76-8.4-8.4V73.06c0-4.64,3.76-8.4,8.4-8.4h10.15c4.64,0,8.4,3.76,8.4,8.4v7.59c2.42-8.1,8.66-13.35,15.93-15.92,5.39-1.9,11.01,2.28,11.01,7.99Z"/><path d="M801.34,40.6c0-8.8,7.36-16.16,16.16-16.16s16.16,7.36,16.16,16.16-7.36,16.17-16.16,16.17-16.16-7.37-16.16-16.17Zm11.09,24.07h10.15c4.64,0,8.4,3.76,8.4,8.4v73.01c0,4.64-3.76,8.4-8.4,8.4h-10.15c-4.64,0-8.4-3.76-8.4-8.4V73.06c0-4.64,3.76-8.4,8.4-8.4Z"/><path d="M936.94,99.33v46.74c0,4.64-3.76,8.4-8.4,8.4h-10.15c-4.64,0-8.4-3.76-8.4-8.4v-42.79c0-11.14-7.01-16.34-15.63-16.34-9.88,0-16.7,5.75-16.7,18.5v40.64c0,4.64-3.76,8.4-8.4,8.4h-10.15c-4.64,0-8.4-3.76-8.4-8.4V73.06c0-4.64,3.76-8.4,8.4-8.4h10.15c4.64,0,8.4,3.76,8.4,8.4v.04c4.85-6.65,13.83-10.96,25.68-10.96,18.14,0,33.59,12.93,33.59,37.18Z"/></g></svg>
            </div>
            <div class="search-box">
                <input type="text" class="search-field" name="search">
                <button class="head-button"><i class="bi bi-search"></i></button>
            </div>
            <div class="head-icon">
                <button class="head-button"><i class="bi bi-cart-dash-fill"></i></button>
                <button class="head-button"><i class="bi bi-chat-dots-fill"></i></button>
            </div>
            <div class="head-profile btn-group text-left">
            <div class="btn dropdown-toggle" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                <img src="../../assets/image/profile.png" class="rounded-circle profile-picture">
                <span class="fw-bold">User 1</span>
            </div>
                <ul class="dropdown-menu" aria-labelledby="dropdownMenuClickable">
                    <li><a class="dropdown-item" href="#">Profile</a></li>
                    <li><a class="dropdown-item" href="#">Setting</a></li>
                    <li><a class="dropdown-item" href="#">Transaksi</a></li>
                    <li><a class="dropdown-item" href="#">Logout</a></li>
                </ul>
            </div>   
        </div>
    </nav>
</header>

<loadjs-head></loadjs-head>
<loadcss-head></loadcss-head>