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

$router->group(['middleware' => 'auth', 'prefix' => 'user'], function ($router) {
    $router->get('/test', 'UsersController@test');
});

$router->group(['prefix' => 'test'], function ($router) {
    $router->post('send-email', 'MailController@sendEmailVerification');
    // $router->post('send-email', 'MailController@mail');
});

// Route::group([
//     'middleware' => 'api',
//     'prefix' => 'auth'
// ], function ($router) {
//     Route::post('/login', [AuthController::class, 'login']);
//     Route::post('/register', [AuthController::class, 'register']);
//     Route::post('/logout', [AuthController::class, 'logout']);
//     Route::post('/refresh', [AuthController::class, 'refresh']);
//     Route::get('/user-profile', [AuthController::class, 'userProfile']);
// });