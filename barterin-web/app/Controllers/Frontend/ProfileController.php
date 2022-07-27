<?php

namespace App\Controllers\Frontend;

use App\Controllers\BaseController;

class ProfileController extends BaseController
{
    public function biodata()
    {
        $data = [
            'title' => 'Barterin | Profile',
            'section' => 'sectionBiodata',
            'subTitle' => 'Atur Biodata diri'
        ];

        return View('frontend/userProfile/profile', $data);
    }

    public function alamat()
    {
        $data = [
            'title' => 'Barterin | Alamat',
            'section' => 'sectionAlamat',
            'subTitle' => 'Atur Alamat'
        ];

        return View('frontend/userProfile/profile', $data);
    }
}