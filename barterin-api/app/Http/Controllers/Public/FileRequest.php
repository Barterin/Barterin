<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\Validator;
use Illuminate\Http\Request;
use Symfony\Component\HttpFoundation\BinaryFileResponse;

class FileRequest extends Controller
{
    public function __construct()
    {
    }

    public function profile(Request $request)
    {
        // $type = 'image/png';
        // $headers = ['Content-Type' => $type];
        // $path = '/path/to/you/your/file.png';

        // $response = new BinaryFileResponse($path, 200, $headers);

        // return $response;
        echo __DIR__;
    }
}