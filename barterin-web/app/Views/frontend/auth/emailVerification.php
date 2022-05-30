<?= $this->extend('frontend/layouts/main'); ?>

<?= $this->section('content'); ?>

<div class="main-container">
    <div class="containerLeft">
        <h1 class="tagline">Let's Exchange Your Stuff</h1>
    </div>
    <div class="containerRight">
        <div class="font signup">
            Not a member? <a href="<?php $base_url ?>/auth/register" class="internal">Sign Up</a>
        </div>
        <div class="form">
            <form id="formVerification">
                <div class="font title">
                    Email Verification
                </div>
                <label><?= session()->getFlashdata("message") ?></label>
                <label class="mt-3" for="email"></label>
                <div class="form-body">
                    <div class="group-name">
                        <div class="form-group">
                            <input class="form-control mt-1 field-name shadow-none" maxlength="1" type="number"
                                id="code1" autofocus>
                            <input class="form-control mt-1 field-name shadow-none" maxlength="1" type="number"
                                id="code2">
                            <input class="form-control mt-1 field-name shadow-none" maxlength="1" type="number"
                                id="code3">
                            <input class="form-control mt-1 field-name shadow-none" maxlength="1" type="number"
                                id="code4">
                            <input class="form-control mt-1 field-name shadow-none" maxlength="1" type="number"
                                id="code5">
                            <input class="form-control mt-1 field-name shadow-none" maxlength="1" type="number"
                                id="code6">
                        </div>
                    </div>
                    <input class="btn btn-submit" type="submit" value="Submit">
                </div>
            </form>
        </div>
    </div>
</div>

<loadjs-emailVerification></loadjs-emailVerification>
<loadcss-emailVerification></loadcss-emailVerification>
<?= $this->endSection(); ?>