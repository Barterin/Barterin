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

    public function tawaranku(){
        $data = [
            'title' => 'Barterin | Daftar Tawaran',
        ];

        return View('frontend/barang/tawaranku', $data);
    }

    public function tawaran($id = "")
    {
        $data = [
            'title' => 'Barterin | Daftar Tawaran',
            'idBarang' => $id
        ];

        return View('frontend/barang/detail_tawaran', $data);
    }

    public function barangku()
    {
        $data = [
            'title' => 'Barterin | Barangku',
        ];

        return View('frontend/barang/barangku', $data);
    }

    public function search($search = "")
    {
        $data = [
            'title' => 'Barterin | Search Result',
            'search' => $search
        ];

        return View('frontend/barang/searchResult', $data);
    }
}