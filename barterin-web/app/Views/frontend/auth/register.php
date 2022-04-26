<?= $this->extend('frontend/layouts/main'); ?>

<?= $this->section('content'); ?>

<div class="main-container">
    <div class="containerLeft">
        <h1 class="tagline">Let's Exchange Your Stuff</h1>
    </div>
    <div class="containerRight">
        <div class="font signup">
            Member already? <a href="#">Sign in</a>
        </div>
        <div class="form">
            <form id="formLogin">
                <div class="font title">
                    Sign Up to Barterin
                </div>
                <div class="form-body">
                    <div class="form-group">
                        <label class="mt-3 fw-bold">Name</label>
                        <input class="form-control mt-1" type="text" name="name" autofocus>
                        <div id="validate_name"></div>
                    </div>
                    <div class="form-group">
                        <label class="mt-3 fw-bold">Username</label>
                        <input class="form-control mt-1" type="text" name="username" autofocus>
                        <div id="validate_username"></div>
                    </div>
                    <div class="form-group">
                        <label class="mt-3 fw-bold">Email</label>
                        <input class="form-control mt-1" type="text" name="email" autofocus>
                        <div id="validate_email"></div>
                    </div>
                    <div class="form-group">
                        <label class="mt-3 fw-bold">Password</label>
                        <input class="form-control mt-1" type="password" name="password">
                        <div id="validate_password"></div>
                    </div>
                    <input class="btn btn-submit" type="submit" value="Create Account">
                </div>
            </form>
        </div>
    </div>
</div>

<loadjs-register></loadjs-register>
<loadcss-register></loadcss-register>
<?= $this->endSection(); ?>