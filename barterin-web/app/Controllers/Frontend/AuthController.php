<?php

namespace App\Controllers\Frontend;

use App\Controllers\BaseController;

class AuthController extends BaseController
{
    public function login()
    {
        $data = [
            'title' => 'Barterin | Login',
        ];

        return View('frontend/auth/login', $data);
    }

    public function register()
    {
        return "hello";
    }
}
