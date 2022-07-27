<?php

namespace App\Controllers\Frontend;

use App\Controllers\BaseController;

class BarangController extends BaseController
{
    public function detailProduk($id = "")
    {
        $data = [
            'title' => 'Barterin | Detail Produk',
            'idBarang' => $id
        ];

        return View('frontend/barang/detail_produk', $data);
    }

    public function upload()
    {
        $data = [
            'title' => 'Barterin | Upload Produk',
        ];

        return View('frontend/barang/upload', $data);
    }

    public function tawaran()
    {
        $data = [
            'title' => 'Barterin | Daftar Tawaran',
        ];

        return View('frontend/barang/tawaran', $data);
    }
}