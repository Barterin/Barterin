<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Validator;
use Illuminate\Http\Request;
use Illuminate\Support\Str;
use Illuminate\Support\Facades\File;
use App\Models\CategoryItem as Table;

class CategoryItemController extends Controller
{

    function __construct()
    {
        $this->middleware("auth", ["except" => ["list"]]);
        $this->validateRules = [
            "name" => "required|string|min:3|max:255|unique:category_item|regex:/^[a-zA-Z0-9& ]+$/u",
            "image" => "required|image|mimes:jpeg,png,jpg,gif,svg|max:2048"
        ];
        $this->validateMessage = [
            "required" => "kolom ini harus diisi",
            "min" => "kategori setidaknya harus memiliki :min karakter",
            "max" => "kategori tidak boleh lebih dari :max karakter",
            "regex" => "kategori hanya boleh diisi oleh alfabet, angka dan simbol &",
            "unique" => "Kategori sudah ada",
            "image.required" => "Tidak ada file yang dipilih",
            "image" => "Format gambar tidak valid",
            "max" => "Ukuran gambar tidak boleh lebih dari 2 MB"
        ];
        $this->userData = json_decode(strval(auth()->user()));
    }

    public function list(Request $request)
    {
        try {

            $data = Table::orderBy("id", "desc");

            if ($request->categoryId != null)
                $data->where("id", Decrypt($request->categoryId));

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
                "statusCode" => GetStatusCode($error),
                "message" => $error->getMessage(),
            ];
        } finally {
            return response()->json(
                $response,
                $response["statusCode"]
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
                throw new \Exception("Kolom inputan tidak sesuai, periksa kembali kolom inputan anda", 400);

            if ($userData->verified_email == "false")
                throw new \Exception("Email belum terverifikasi", 400);

            $file = $request->image;
            $fileName = md5(time());
            $extension = $file->extension();

            $file->move("uploads/images/category", $fileName . "." . $extension);

            $categoryData = [
                "name" => $request->input("name"),
                "slug" => Str::of($request->input("name"))->slug("-"),
                "image" => $fileName . "." . $extension
            ];

            $insertId = Table::create($categoryData)->id;

            $response = [
                "statusCode" => 200,
                "message" => "Kategori berhasil dimasukan",
                "id" => Encrypt($insertId)
            ];
        } catch (\Throwable | \Exception $error) {
            $response = [
                "statusCode" => GetStatusCode($error),
                "message" => $error->getMessage(),
                "input" => $validator->errors()
            ];
        } finally {
            return response()->json(
                $response,
                $response["statusCode"]
            );
        }
    }

    public function delete(Request $request)
    {
        try {

            if (!$request->categoryId) throw new \Exception("Bad request", 400);

            $data = Table::where(["id" => Decrypt($request->categoryId)]);

            if (!$data->get()->first()) throw new \Exception("Data not found", 404);

            $data->delete();

            $response = [
                "statusCode" => 200,
                "message" => "Kategori berhasil dihapus",
            ];
        } catch (\Throwable | \Exception $error) {
            $response = [
                "statusCode" => GetStatusCode($error),
                "message" => $error->getMessage()
            ];
        } finally {
            return response()->json(
                $response,
                $response["statusCode"]
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
                throw new \Exception("Kolom inputan tidak sesuai, periksa kembali kolom inputan anda", 400);

            $data = Table::where(["id" => Decrypt($request->categoryId)]);

            if (!$data->get()->first()) throw new \Exception("Data not found", 404);

            $imageData = $data->get()->first();

            if ($imageData && $imageData->image != null)
                File::delete("uploads/images/category/" . $imageData->image);

            $file = $request->image;
            $fileName = md5(time());
            $extension = $file->extension();

            $file->move("uploads/images/category", $fileName . "." . $extension);

            $data->update([
                "name" => $request->input("name"),
                "slug" => Str::of($request->input("name"))->slug("-"),
                "image" => $fileName . "." . $extension
            ]);

            $response = [
                "statusCode" => 200,
                "message" => "Kategori berhasil diupdate",
            ];
        } catch (\Throwable | \Exception $error) {
            $response = [
                "statusCode" => GetStatusCode($error),
                "message" => $error->getMessage() . ", On Line : " . $error->getLine(),
                "input" => $validator->errors()
            ];
        } finally {
            return response()->json(
                array_merge($response),
                $response["statusCode"]
            );
        }
    }
}