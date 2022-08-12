<?php

namespace Config;

// Create a new instance of our RouteCollection class.
$routes = Services::routes();

// Load the system's routing file first, so that the app and ENVIRONMENT
// can override as needed.
if (file_exists(SYSTEMPATH . 'Config/Routes.php')) {
    require SYSTEMPATH . 'Config/Routes.php';
}

/*
 * --------------------------------------------------------------------
 * Router Setup
 * --------------------------------------------------------------------
 */
$routes->setDefaultNamespace('App\Controllers\Frontend');
$routes->setDefaultController('HomeController');
$routes->setDefaultMethod('index');
$routes->setTranslateURIDashes(false);
$routes->set404Override();
$routes->setAutoRoute(true);

/*
 * --------------------------------------------------------------------
 * Route Definitions
 * --------------------------------------------------------------------
 */

// We get a performance increase by specifying the default
// route since we don't have to scan directories.

// $routes->get('/', 'Home::index');

$routes->group('/', ['namespace' => 'App\Controllers\Frontend'], function ($routes) {
    $routes->get('/', 'HomeController::index');
    $routes->get('home', 'HomeController::index');
});

$routes->group('/auth', ['namespace' => 'App\Controllers\Frontend'], function ($routes) {
    $routes->get('login', 'AuthController::login');
    $routes->get('logout', 'AuthController::logout');
    $routes->get('register', 'AuthController::register');
    $routes->get('forget-password', 'AuthController::forgetPassword');
    $routes->get('email-verification', 'AuthController::emailVerification');
    $routes->get('authenticating/(:any)/(:any)', 'AuthController::authenticating/$1/$2');
});

$routes->group('/profile', ['namespace' => 'App\Controllers\Frontend'], function ($routes) {
    $routes->get('biodata', 'ProfileController::biodata');
    $routes->get('alamat', 'ProfileController::alamat');
});

$routes->group('/barang', ['namespace' => 'App\Controllers\Frontend'], function ($routes) {
    $routes->get('detail-produk', 'BarangController::detailProduk');
    $routes->get('upload', 'BarangController::upload');
    $routes->get('tawaranku', 'BarangController::tawaranku');
    $routes->get('tawaran/(:any)', 'BarangController::tawaran/$1');
    $routes->get('barangku', 'BarangController::barangku');
    $routes->get('barter/(:any)', 'BarangController::detailBarangBarter/$1');
    $routes->get('donasi/(:any)', 'BarangController::detailBarangDonasi/$1');
});
$routes->group('/test', ['namespace' => 'App\Controllers\Frontend'], function ($routes) {
    $routes->get('page1', 'Page1::index');
    $routes->get('page2', 'Page2::index');
    $routes->get('page3', 'Page3::index');
});

/*
 * --------------------------------------------------------------------
 * Additional Routing
 * --------------------------------------------------------------------
 *
 * There will often be times that you need additional routing and you
 * need it to be able to override any defaults in this file. Environment
 * based routes is one such time. require() additional route files here
 * to make that happen.
 *
 * You will have access to the $routes object within that file without
 * needing to reload it.
 */
if (file_exists(APPPATH . 'Config/' . ENVIRONMENT . '/Routes.php')) {
    require APPPATH . 'Config/' . ENVIRONMENT . '/Routes.php';
}