<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\Validator;
use Illuminate\Http\Request;
use App\Models\Address;

class AddressController extends Controller
{

    public function store(Request $request)
    {
        try {
            $userData = json_decode(strval(auth()->user()));
            if ($userData->verified_email == 'false')
                throw new \Exception('Email belum terverifikasi', 400);

            $validator = Validator::make(
                $request->all(),
                [
                    "penerima" => "required|string|min:3",
                    "nohp" => "required|min:10|max:13",
                    "label" => "required|string|min:3",
                    "kota_kecamatan" => "required|string|min:3",
                    "alamat_lengkap" => "required|min:10",
                    "kode_pos" => "required|min:3"
                ],
                [
                    'required' => 'kolom ini harus diisi',
                    'nohp.min' => 'nomor hp setidaknya harus memiliki :min angka',
                    'nohp.max' => 'nomor hp tidak boleh lebih dari :max angka',
                    'kode_pos.min' => 'kode pos setidaknya memiliki :min angka',
                    'label.min' => 'label setidaknya harus memiliki :min huruf',
                    'label.unique' => 'label sudah digunakan',
                    'penerima.min' => 'penerima setidaknya harus memiliki :min huruf',
                    'kota_kecamatan.min' => 'kota atau kecamatan setidaknya harus memiliki :min huruf',
                    'alamat_lengkap.min' => 'alamat setidaknya harus memiliki :min huruf',
                    'kode_pos.min' => 'kode pos setidaknya harus memiliki :min huruf',
                ]
            );

            if ($validator->fails())
                throw new \Exception('Kolom inputan tidak sesuai, periksa kembali kolom inputan anda', 400);

            $addressData = array_merge($request->all(), ['user_id' => $userData->id]);

            $insertId = Address::create($addressData)->id;

            $response = [
                'statusCode' => 200,
                'message' => "Alamat berhasil dimasukan",
                // 'id' => $insertId . " , " . Encrypt($insertId)
            ];
        } catch (\Throwable | \Exception $error) {
            $response = [
                'statusCode' => GetStatusCode($error),
                'message' => $error->getMessage()
            ];
        } finally {
            return response()->json(
                array_merge($response, ['input' => $validator->errors()]),
                $response['statusCode']
            );
        }
    }

    public function delete(Request $request)
    {
        try {
            $userData = json_decode(strval(auth()->user()));

            if (!$request->has('id')) throw new \Exception('Bad request', 400);

            $data = Address::where(['id' => Decrypt($request->input('id'))]);

            if (!$data->get()->first()) throw new \Exception('Data not found', 404);

            $data->delete();

            $response = [
                'statusCode' => 200,
                'message' => "Alamat berhasil dihapus",
            ];
        } catch (\Throwable | \Exception $error) {
            $response = [
                'statusCode' => GetStatusCode($error),
                'message' => $error->getMessage()
            ];
        } finally {
            return response()->json(
                $response,
                $response['statusCode']
            );
        }
    }

    public function update(Request $request)
    {
        try {
            $validator = Validator::make(
                $request->all(),
                [
                    "penerima" => "required|string|min:3",
                    "nohp" => "required|min:10|max:13",
                    "label" => "required|string|min:3",
                    "kota_kecamatan" => "required|string|min:3",
                    "alamat_lengkap" => "required|min:10",
                    "kode_pos" => "required|min:3"
                ],
                [
                    'required' => 'kolom ini harus diisi',
                    'nohp.min' => 'nomor hp setidaknya harus memiliki :min angka',
                    'nohp.max' => 'nomor hp tidak boleh lebih dari :max angka',
                    'kode_pos.min' => 'kode pos setidaknya memiliki :min angka',
                    'label.min' => 'label setidaknya harus memiliki :min huruf',
                    'label.unique' => 'label sudah digunakan',
                    'penerima.min' => 'penerima setidaknya harus memiliki :min huruf',
                    'kota_kecamatan.min' => 'kota atau kecamatan setidaknya harus memiliki :min huruf',
                    'alamat_lengkap.min' => 'alamat setidaknya harus meminanti haliki :min huruf',
                    'kode_pos.min' => 'kode pos setidaknya harus memiliki :min huruf',
                ]
            );

            if ($validator->fails())
                throw new \Exception('Kolom inputan tidak sesuai, periksa kembali kolom inputan anda', 400);

            $data = Address::where(['id' => Decrypt($request->addressId)]);

            if (!$data->get()->first()) throw new \Exception('Data not found', 404);

            $data->update($request->all());

            $response = [
                'statusCode' => 200,
                'message' => "Alamat berhasil diupdate",
            ];
        } catch (\Throwable | \Exception $error) {
            $response = [
                'statusCode' => GetStatusCode($error),
                'message' => $error->getMessage(),
                'input' => $validator->errors()
            ];
        } finally {
            return response()->json(
                array_merge($response),
                $response['statusCode']
            );
        }
    }
}
