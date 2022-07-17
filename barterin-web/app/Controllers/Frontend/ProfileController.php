<?php

namespace App\Controllers\Frontend;

use App\Controllers\BaseController;

class ProfileController extends BaseController
{
    public function biodata()
    {
        $data = [
            'title' => 'Barterin | Profile',
        ];

        return View('frontend/userProfile/biodata', $data);
    }
    
    public function alamat()
    {
        $data = [
            'title' => 'Barterin | Alamat',
        ];

        return View('frontend/userProfile/alamat', $data);
    }
}