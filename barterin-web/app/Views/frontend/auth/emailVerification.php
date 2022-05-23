<?= $this->extend('frontend/layouts/main'); ?>

<?= $this->section('content'); ?>

<div class="main-container">
    <div class="containerLeft">
        <h1 class="tagline">Let's Exchange Your Stuff</h1>
    </div>
    <div class="containerRight">
        <div class="font signup">
            Not a member? <a href="http://localhost:8001/auth/register">Sign Up</a>
        </div>
        <div class="form">
            <form id="formLogin">
                <div class="font title">
                    Email Verification
                </div>
                <label class="mt-3" for="email"></label>
                <div class="form-body">
                    <div class="group-name">
                        <div class="form-group">
                            <input class="form-control mt-1 field-name shadow-none" maxlength="1" type="number" autofocus>
                            <input class="form-control mt-1 field-name shadow-none" maxlength="1" type="number" >
                            <input class="form-control mt-1 field-name shadow-none" maxlength="1" type="number" >
                            <input class="form-control mt-1 field-name shadow-none" maxlength="1" type="number" >
                            <input class="form-control mt-1 field-name shadow-none" maxlength="1" type="number" >
                            <input class="form-control mt-1 field-name shadow-none" maxlength="1" type="number" >
                        </div>
                    </div>
                    <input class="btn btn-submit" type="submit" value="Signin">
                </div>
            </form>
        </div>
    </div>
</div>

<loadjs-emailVerification></loadjs-emailVerification>
<loadcss-emailVerification></loadcss-emailVerification>
<?= $this->endSection(); ?>