<?php

/** @var \Laravel\Lumen\Routing\Router $router */

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

$router->get('/', function () use ($router) {
    return $router->app->version();
});

$router->group(['prefix' => 'auth'], function ($router) {
    $router->post('/login', 'AuthController@login');
    $router->post('/register', 'AuthController@register');
    $router->post('/refresh', 'AuthController@refresh');
    $router->post('/logout', 'AuthController@logout');
    $router->post('/user-profile', 'AuthController@userProfile');
    $router->post('/send-email', 'MailController@sendEmailVerification');
    $router->post('/verify-email', 'MailController@verifyEmail');
});

$router->group(['middleware' => 'auth', 'prefix' => 'address'], function ($router) {
    $router->get('list', 'AddressController@list');
    $router->get('id/{addressId}', 'AddressController@list');
    $router->post('store', 'AddressController@store');
    $router->post('delete', 'AddressController@delete');
    $router->post('update/{addressId}', 'AddressController@update');
});

$router->group(['prefix' => 'category'], function ($router) {
    $router->get('list', 'CategoryItemController@list');
    $router->get('id/{categoryId}', 'CategoryItemController@list');
    $router->post('store', 'CategoryItemController@store');
    $router->post('delete', 'CategoryItemController@delete');
    $router->post('update/{categoryId}', 'CategoryItemController@update');
});

$router->group(['middleware' => 'auth', 'prefix' => 'user'], function ($router) {
    $router->post('update', 'UserProfileController@update');
    $router->post('upload-photo', 'UserProfileController@uploadPhotoProfile');
});