<?= $this->extend('frontend/layouts/main'); ?>

<?= $this->section('content'); ?>
<div class="container">
    <button id="btn">asd</button>
    <form id="formLogin">
        <div class="card">
            <div class="card-header">
                Login
            </div>
            <div class="card-body">
                <div class="form-group">
                    <label>Username / Email</label>
                    <input class="form-control" type="text" name="username" autofocus>
                    <div id="validate_username"></div>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input class="form-control" type="password" name="password">
                    <div id="validate_password"></div>
                </div>
            </div>
            <div class="card-footer d-flex justify-content-center">
                <input class="btn btn-primary" type="submit" value="Masuk">
            </div>
        </div>
    </form>
</div>

<loadjs-login></loadjs-login>
<?= $this->endSection(); ?>