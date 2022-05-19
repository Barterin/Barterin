<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Facades\File;
use Illuminate\Http\Request;
use App\Models\UserProfile;

class UserProfileController extends Controller
{

    function __construct()
    {
        $this->validateRules = [
            "phone" => "required|min:10|max:15|regex:/^[0-9]+$/u",
            "born" => "required|date_format:Y-m-d",
            "gender" => "required|in:male,female,other",
        ];
        $this->validateMessage = [
            'required' => 'kolom ini harus diisi',
            'phone.min' => 'Nomor telpon setidaknya harus memiliki :min angka',
            'phone.regex' => 'Nomor telpon hanya bisa dimasukan oleh angka',
            'born.date_format' => "Format tanggal tidak valid",
            'gender.in' => "Pilihan gender tidak tersedia"
        ];
        $this->userData = json_decode(strval(auth()->user()));
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

            UserProfile::updateOrInsert(["user_id" => $this->userData->id], $request->all());

            $response = [
                "statusCode" => 200,
                "message" => "Profile berhasil diupdate"
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

    public function uploadPhotoProfile(Request $request)
    {
        try {

            $validator = Validator::make(
                $request->all(),
                [
                    "photo" => "required|image"
                ],
                [
                    "required" => "Tidak ada file yang dipilih",
                    "image" => "Format gambar tidak valid"
                ]
            );

            if ($validator->fails())
                throw new \Exception('Kolom inputan tidak sesuai, periksa kembali kolom inputan anda', 400);

            // get latest profile picture
            $user = UserProfile::where(["user_id" => $this->userData->id])->first();
            // if exists then delete
            if ($user && $user->profile_picture != null)
                File::delete("uploads/images/profiles/" . $user->profile_picture);

            $file = $request->photo;
            $fileName = md5(time());
            $extension = $file->extension();

            $file->move("uploads/images/profiles", $fileName . "." . $extension);

            UserProfile::updateOrInsert(["user_id" => $this->userData->id], ["profile_picture" => $fileName . "." . $extension]);

            $response = [
                "statusCode" => 200,
                "message" => "Photo profile berhasil diubah",
                // "path" => env("APP_URL") . "/uploads/images/profiles/$fileName.$extension",
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
}