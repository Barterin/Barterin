<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Validator;
use Illuminate\Http\Request;
use Illuminate\Support\Str;
use App\Models\TypeItem as Table;
use App\Models\CategoryItem as TableCategory;

class TypeItemController extends Controller
{

    function __construct()
    {
        $this->middleware('auth', ['except' => ['list']]);
        $this->validateRules = [
            "name" => "required|string|min:3|max:255|unique:type_item|regex:/^[a-zA-Z0-9 ]+$/u",
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
                $data->where('category_id', Decrypt($request->categoryId));

            if ($request->typeId != null)
                $data->where('id', Decrypt($request->typeId));

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

            $categoryId = TableCategory::find(decrypt($request->categoryId));

            if (!$categoryId)
                throw new \Exception('Category ID not exists', 404);

            if ($validator->fails())
                throw new \Exception('Kolom inputan tidak sesuai, periksa kembali kolom inputan anda', 400);

            if ($userData->verified_email == 'false')
                throw new \Exception('Email belum terverifikasi', 400);

            $categoryData = [
                'category_id' => decrypt($request->categoryId),
                'name' => $request->input('name'),
                'slug' => Str::of($request->input('name'))->slug('-')
            ];

            $insertId = Table::create($categoryData)->id;

            $response = [
                'statusCode' => 200,
                'message' => "Tipe berhasil dimasukan",
                'id' => Encrypt($insertId)
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

            if (!$request->typeId) throw new \Exception('Bad request', 400);

            $data = Table::where(['id' => decrypt($request->typeId)]);

            if (!$data->get()->first()) throw new \Exception('Data not found', 404);

            $data->delete();

            $response = [
                'statusCode' => 200,
                'message' => "Tipe berhasil dihapus",
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

            $data = Table::where(['id' => decrypt($request->typeId)]);

            if (!$data->get()->first()) throw new \Exception('Data not found', 404);

            $data->update([
                'name' => $request->input('name'),
                'slug' => Str::of($request->input('name'))->slug('-')
            ]);

            $response = [
                'statusCode' => 200,
                'message' => "Tipe berhasil diupdate",
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