<?= $this->extend('frontend/layouts/main'); ?>
<?= $this->section('content'); ?>
<div class="container-fluid mt-3" >
    <div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
                aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
                aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
                aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner rounded">
            <div class="carousel-item active" data-bs-interval="2000">
                <img src="../../assets/image/banner/banner.jpg" class="d-block w-100" alt="..." >
            </div>
            <div class="carousel-item" data-bs-interval="2000">
                <img src="../../assets/image/banner/banner1.jpg" class="d-block w-100" alt="...">
            </div>
            <div class="carousel-item" data-bs-interval="2000">
                <img src="../../assets/image/banner/banner2.jpg" class="d-block w-100" alt="...">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</div>
<div class="container-lg mt-3">
    <div class="row">
        <h1 class="col m-auto fw-bold">Category</h1>
        <a href="#" class="col text-decoration-none d-flex align-items-center m-auto justify-content-end">
            See More
            <i class="bi bi-arrow-right-short"></i>
        </a>
    </div>
    <div class="category" id="category">
        <div class="row mt-3 rounded">
            <img src="../../assets/image/categoryBanner/electronic.jpg" class="card-img-top img img-fluid rounded " id="electronic" alt="">
        </div>
        <div class="row mt-3 rounded">
            <img src="../../assets/image/categoryBanner/fashion.jpg" class="card-img-top img img-fluid rounded " id="fashion" alt="">
        </div>
        <div class="row mt-3 rounded">
            <img src="../../assets/image/categoryBanner/cooking.jpg" class="card-img-top img img-fluid rounded " id="cooking" alt="">
        </div>
    </div>
</div>
<!-- <div class="container-lg mt-3">
    <div class="row">
        <h1 class="col m-auto fw-bold">Barang Barter</h1>
        <a href="#" class="col text-decoration-none d-flex align-items-center m-auto justify-content-end">
            See More
            <i class="bi bi-arrow-right-short"></i>
        </a>
    </div>
    <div class="row mt-3 barter-slider" id="barterSliderItems">


    </div>
</div> -->
<div class="container-lg mt-3 mb-5">
    <div>
        <div class="row">
            <h1 class="col m-auto fw-bold">Barang Donasi</h1>
            <a href="#" class="col text-decoration-none d-flex align-items-center m-auto justify-content-end">
                See More
                <i class="bi bi-arrow-right-short"></i>
            </a>
        </div>
        <div class="row mt-3 donasi-slider" id="donasiSliderItems">


        </div>
    </div>
    <div class="mt-3">
        <div class="row">
            <h1 class="col m-auto fw-bold">Near You</h1>
            <a href="#" class="col text-decoration-none d-flex align-items-center m-auto justify-content-end">
                See More
                <i class="bi bi-arrow-right-short"></i>
            </a>
        </div>
    <div class="row mt-3 donasi-slider" id="nearYouSliderItems">


    </div>
    </div>
</div>
<div class="floating-button">
    <a href="https://bot.barterin.tech" class="float">
        <i class="bi bi-chat-right-fill my-float"></i>
    </a>
</div>
</body>

<loadjs-homepage></loadjs-homepage>
<loadcss-homepage></loadcss-homepage>

<?= $this->endSection(); ?>