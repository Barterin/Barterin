<?php

namespace App\Http\Controllers;

use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;
use Illuminate\Http\Request;
use App\Models\User;

class AuthController extends Controller
{

    public function __construct()
    {
        $this->middleware('auth:api', ['except' => ['login', 'register']]);
    }

    public function register(Request $request)
    {
        try {

            $validator = Validator::make($request->all(), [
                "username" => "required|string|unique:users",
                "fullname" => "required|string",
                "email" => "required|email:rfc,dns|unique:users",
                "password" => "required",
            ], [
                'required' => 'kolom ini harus diisi',
                'username.unique' => 'nama pengguna sudah digunakan',
                'email.unique' => 'email sudah digunakan',
                'email' => 'format email harus benar'
            ]);

            if ($validator->fails())
                throw new \Exception('Kolom inputan tidak sesuai, periksa kembali kolom inputan anda');

            User::create([
                'username' => $request->input('username'),
                'fullname' => $request->input('fullname'),
                'email' => $request->input('email'),
                'password' => Hash::make($request->input('password')),
            ]);

            $response = [
                'status_code' => 200,
                'message' => 'Create data success'
            ];
        } catch (\Exception $Error) {
            $response = [
                'status_code' => 400,
                'message' => $Error->getMessage()
            ];
        } catch (\Throwable $Error) {
            $response = [
                'status_code' => 400,
                'message' => $Error->getMessage()
            ];
        } finally {
            return response()->json(
                [
                    "message" => $response['message'],
                    "input" => $validator->errors()
                ],
                $response['status_code']
            );
        }
    }
}
