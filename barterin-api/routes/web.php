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

// Auth router
$router->group(['prefix' => 'auth'], function ($router) {
    $router->post('/login', 'Auth\AuthController@login');
    $router->post('/register', 'Auth\AuthController@register');
    $router->post('/refresh', 'Auth\AuthController@refresh');
    $router->post('/logout', 'Auth\AuthController@logout');
    $router->post('/user-profile', 'Auth\AuthController@userProfile');
    $router->post('/send-email', 'Auth\MailController@sendEmailVerification');
    $router->post('/verify-email', 'Auth\MailController@verifyEmail');
});

// Admin router
$router->group(['prefix' => 'admin', 'middleware' => ['auth', 'isAdmin']], function ($router) {
    $router->group(['prefix' => 'category'], function ($router) {
        $router->get('list', 'Admin\CategoryItemController@list');
        $router->get('id/{categoryId}', 'Admin\CategoryItemController@list');
        $router->post('store', 'Admin\CategoryItemController@store');
        $router->delete('delete/{categoryId}', 'Admin\CategoryItemController@delete');
        $router->post('update/{categoryId}', 'Admin\CategoryItemController@update');
    });
    $router->group(['prefix' => 'type'], function ($router) {
        $router->get('list/{categoryId}', 'Admin\TypeItemController@list');
        $router->get('id/{typeId}', 'Admin\TypeItemController@list');
        $router->post('store/{categoryId}', 'Admin\TypeItemController@store');
        $router->delete('delete/{typeId}', 'Admin\TypeItemController@delete');
        $router->post('update/{typeId}', 'Admin\TypeItemController@update');
    });
});

// Member router
$router->group(['prefix' => 'member', 'middleware' => 'auth'], function ($router) {
    $router->group(['prefix' => 'address'], function ($router) {
        $router->get('list', 'Member\AddressController@list');
        $router->get('id/{addressId}', 'Member\AddressController@list');
        $router->post('store', 'Member\AddressController@store');
        $router->post('update/{addressId}', 'Member\AddressController@update');
        $router->delete('delete/{addressId}', 'Member\AddressController@delete');
    });
    $router->group(['prefix' => 'user'], function ($router) {
        $router->post('update', 'Member\UserProfileController@update');
        $router->post('upload-photo', 'Member\UserProfileController@uploadPhotoProfile');
    });
    $router->group(['prefix' => 'items'], function ($router) {
        $router->get('list', 'Member\BarterItemsController@list');
        $router->get('id/{itemsId}', 'Member\BarterItemsController@list');
        $router->post('store', 'Member\BarterItemsController@store');
        $router->post('update/{itemsId}', 'Member\BarterItemsController@update');
        $router->delete('delete/{itemsId}', 'Member\BarterItemsController@delete');
    });
    $router->group(['prefix' => 'cart'], function ($router) {
        $router->post('store', 'Member\CartController@store');
        $router->get('list', 'Member\CartController@list');
        $router->delete('delete/{cartId}', 'Member\CartController@delete');
    });
});

// Public router

$router->group(['prefix' => 'public'], function ($router) {
    $router->get('items/barter', 'PublicApi\HomeApiController@itemListBarter');
    $router->get('items/donate', 'PublicApi\HomeApiController@itemListDonate');
});

$router->group(['prefix' => 'files'], function ($router) {
    $router->get('profile/{imageFile}', 'FileRequest@profile');
});