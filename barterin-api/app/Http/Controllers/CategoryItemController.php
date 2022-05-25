<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\Validator;
use Illuminate\Http\Request;
use Illuminate\Support\Str;
use App\Models\Categoryitem as Table;

class CategoryItemController extends Controller
{

    function __construct()
    {
        $this->middleware('auth', ['except' => ['list']]);
        $this->validateRules = [
            "name" => "required|string|min:3|max:255|unique:category_item|regex:/^[a-zA-Z0-9 ]+$/u",
        ];
        $this->validateMessage = [
            'required' => 'kolom ini harus diisi',
            'min' => 'kategori setidaknya harus memiliki :min karakter',
            'max' => 'kategori tidak boleh lebih dari :max karakter',
            'regex' => 'kategori hanya boleh diisi oleh alfabet dan angka',
            'unique' => 'Kategori sudah ada'
        ];
        $this->userData = json_decode(strval(auth()->user()));
    }

    public function list(Request $request)
    {
        try {

            $data = Table::orderBy("id", "desc");

            if ($request->categoryId != null)
                $data->where('id', Decrypt($request->categoryId));

            $addressData = [];

            foreach ($data->get() as $rows) {
                $row = [];
                $row["id"] = Encrypt($rows->id);
                $row["name"] = $rows->name;
                $row["slug"] = $rows->slug;
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

            $categoryData = [
                'name' => $request->input('name'),
                'slug' => Str::of($request->input('name'))->slug('-')
            ];

            $insertId = Table::create($categoryData)->id;

            $response = [
                'statusCode' => 200,
                'message' => "Kategori berhasil dimasukan",
                'id' => $insertId . " , " . Encrypt($insertId)
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

            $data = Table::where(['id' => Decrypt($request->input('id'))]);

            if (!$data->get()->first()) throw new \Exception('Data not found', 404);

            $data->delete();

            $response = [
                'statusCode' => 200,
                'message' => "Kategori berhasil dihapus",
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

            $data = Table::where(['id' => Decrypt($request->categoryId)]);

            if (!$data->get()->first()) throw new \Exception('Data not found', 404);

            $data->update([
                'name' => $request->input('name'),
                'slug' => Str::of($request->input('name'))->slug('-')
            ]);

            $response = [
                'statusCode' => 200,
                'message' => "Kategori berhasil diupdate",
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
