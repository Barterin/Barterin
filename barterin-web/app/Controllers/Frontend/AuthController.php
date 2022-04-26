<?php

namespace App\Controllers\Frontend;

use App\Controllers\BaseController;

class AuthController extends BaseController
{
    public function login()
    {
        $data = [
            'title' => 'Barterin | Sign in',
        ];

        return View('frontend/auth/login', $data);
    }

    public function register()
    {
        $data = [
            'title' => 'Barterin | Sign up',
        ];
        return View('frontend/auth/register', $data);
    }

    public function forgetPassword()
    {
        $data = [
            'title' => 'Barterin | Forgot Password',
        ];

        return View('frontend/auth/forgetPassword', $data);
    }
}
