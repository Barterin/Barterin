<?= $this->extend('frontend/layouts/main'); ?>

<?= $this->section('content'); ?>

<div class="main-container">
    <div class="containerLeft">
        <h1 class="tagline">Let's Exchange Your Stuff</h1>
    </div>
    <div class="containerRight">
        <div class="font signup">
            Not a member? <a href="<?= base_url() ?>/auth/register">Sign Up</a>
        </div>
        <div class="form">
            <form id="formLogin">
                <div class="font title">
                    Sign in to Barterin
                </div>
                <div class="form-body">
                    <div class="form-group">
                        <label class="mt-3 fw-bold">Username or Email Address</label>
                        <input class="form-control mt-1 shadow-none" type="text" name="username" autofocus>
                        <div id="validate_username"></div>
                    </div>
                    <div class="form-group">
                        <label class="mt-3 fw-bold">Password</label>
                        <a class="forgetPassword mt-3" href="<?= base_url() ?>/auth/forget-password">Forget
                            Password?</a>
                        <input class="form-control mt-1 shadow-none" type="password" name="password">
                        <div id="validate_password"></div>
                    </div>
                    <input class="btn btn-submit" type="submit" value="Signin">
                </div>
            </form>
        </div>
    </div>
</div>

<loadcss-login></loadcss-login>
<loadjs-login></loadjs-login>
<?= $this->endSection(); ?>