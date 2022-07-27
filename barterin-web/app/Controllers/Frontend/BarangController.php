<?php

namespace App\Controllers\Frontend;

use App\Controllers\BaseController;

class BarangController extends BaseController
{
    public function detailBarangBarter($id = "")
    {
        $data = [
            'title' => 'Barterin | Detail Barang',
            'idBarang' => $id
        ];

        return View('frontend/barang/detail_barang', $data);
    }

    public function detailBarangDonasi($id = "")
    {
        $data = [
            'title' => 'Barterin | Detail Barang',
            'idBarang' => $id
        ];

        return View('frontend/barang/detail_barang_donasi', $data);
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

    public function barangku()
    {
        $data = [
            'title' => 'Barterin | Barangku',
        ];

        return View('frontend/barang/barangku', $data);
    }
}