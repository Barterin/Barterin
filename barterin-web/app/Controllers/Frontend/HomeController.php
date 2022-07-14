<?php

namespace App\Controllers\Frontend;

use App\Controllers\BaseController;

class HomeController extends BaseController
{
    public function index()
    {
        $data = [
            'title' => 'Barterin | Homepage',
        ];

        return View('frontend/home/homepage', $data);
    }


}
