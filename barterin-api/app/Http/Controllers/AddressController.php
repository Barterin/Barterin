<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\Validator;
use Illuminate\Http\Request;
use App\Models\Address;

class AddressController extends Controller
{

    function __construct()
    {
        $this->validateRules = [
            "penerima" => "required|string|min:3",
            "nohp" => "required|min:10|max:13",
            "label" => "required|string|min:3",
            "kota_kecamatan" => "required|string|min:3",
            "alamat_lengkap" => "required|min:10",
            "kode_pos" => "required|min:3"
        ];
        $this->validateMessage = [
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
        ];
        $this->userData = json_decode(strval(auth()->user()));
    }

    public function list(Request $request)
    {
        try {

            $data = Address::where('user_id', $this->userData->id)->orderBy("id", "desc");

            if ($request->addressId != null)
                $data->where('id', Decrypt($request->addressId));

            $addressData = [];

            foreach ($data->get() as $rows) {
                $row = [];
                $row["id"] = Encrypt($rows->id);
                $row["penerima"] = $rows->penerima;
                $row["nohp"] = $rows->nohp;
                $row["label"] = $rows->label;
                $row["kota_kecamatan"] = $rows->kota_kecamatan;
                $row["alamat_lengkap"] = $rows->alamat_lengkap;
                $row["kode_pos"] = $rows->kode_pos;
                $addressData[] = $row;
            }

            $response = [
                "statusCode" => 200,
                "data" => $addressData
            ];
        } catch (\Throwable | \Exception $error) {
            $response = [
                'statusCode' => GetStatusCode($error),
                'message' => $error->getMessage(),
            ];
        } finally {
            return response()->json(
                $response,
                $response['statusCode']
            );
        }
    }

    public function store(Request $request)
    {
        try {
            $userData = $this->userData;

            $validator = Validator::make(
                $request->all(),
                $this->validateRules,
                $this->validateMessage
            );

            if ($validator->fails())
                throw new \Exception('Kolom inputan tidak sesuai, periksa kembali kolom inputan anda', 400);

            if ($userData->verified_email == 'false')
                throw new \Exception('Email belum terverifikasi', 400);

            $addressData = array_merge(['user_id' => $userData->id], $request->all());

            $insertId = Address::create($addressData)->id;

            $response = [
                'statusCode' => 200,
                'message' => "Alamat berhasil dimasukan",
                // 'id' => $insertId . " , " . Encrypt($insertId)
            ];
        } catch (\Throwable | \Exception $error) {
            $response = [
                'statusCode' => GetStatusCode($error),
                'message' => $error->getMessage(),
                'input' => $validator->errors()
            ];
        } finally {
            return response()->json(
                $response,
                $response['statusCode']
            );
        }
    }

    public function delete(Request $request)
    {
        try {

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
                $this->validateRules,
                $this->validateMessage
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