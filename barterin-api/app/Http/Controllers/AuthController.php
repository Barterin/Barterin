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
        $this->middleware('auth', ['except' => ['login', 'register']]);
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
                throw new \Exception('Kolom inputan tidak sesuai, periksa kembali kolom inputan anda', 400);

            User::create([
                'username' => $request->input('username'),
                'fullname' => $request->input('fullname'),
                'email' => $request->input('email'),
                'password' => Hash::make($request->input('password')),
            ]);

            $response = [
                'statusCode' => 200,
                'message' => 'Create data success'
            ];
        } catch (\Exception $error) {
            $response = [
                'statusCode' => GetStatusCode($error),
                'message' => $error->getMessage()
            ];
        } finally {
            return response()->json(
                [
                    "message" => $response['message'],
                    "input" => $validator->errors()
                ],
                $response['statusCode']
            );
        }
    }

    public function Login(Request $request)
    {
        try {

            $validator = Validator::make($request->all(), [
                "username" => "required|string",
                "password" => "required|string|min:6",
            ], [
                'required' => 'kolom ini harus diisi',
            ]);

            $useEmail = [
                'email' => $request->input('username'),
                'password' => $request->input('password'),
            ];


            if ($validator->fails())
                throw new \Exception('Kolom inputan tidak sesuai, periksa kembali kolom inputan anda', 400);

            $tokenUsername = auth()->attempt($validator->validated());
            $tokenEmail = auth()->attempt($useEmail);

            if (empty($tokenEmail) && empty($tokenUsername))
                throw new \Exception('Username atau Password salah', 401);

            $token = $this->createNewToken($tokenEmail != true ? $tokenUsername : $tokenEmail);

            $response = [
                'statusCode' => 200,
                'message' => 'Berhasil masuk',
                'access' => $token
            ];
        } catch (\Exception $error) {
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

    public function refresh()
    {
        try {
            $response = [
                'statusCode' => 200,
                'access' => $this->createNewToken(auth()->refresh())
            ];
        } catch (\Exception $error) {
            $response = [
                'statusCode' => GetStatusCode($error),
                'message' => "fail : $error->getMessage()"
            ];
        } finally {
            return response()->json(
                $response,
                $response['statusCode']
            );
        }
    }

    public function userProfile()
    {
        $userData = json_decode(auth()->user());
        // print_r($userData);
        $userData->id = md5(sha1($userData->id));
        return response()->json($userData);
    }

    public function logout()
    {
        try {
            auth()->logout();
            $response = [
                'statusCode' => 200,
                'message' => "Berhasil logout"
            ];
        } catch (\Exception $error) {
            $response = [
                'statusCode' => GetStatusCode($error),
                'message' => "fail : $error->getMessage()"
            ];
        } finally {
            return response()->json(
                $response,
                $response['statusCode']
            );
        }
    }

    protected function createNewToken($token)
    {
        return [
            'access_token' => $token,
            'token_type' => 'bearer',
            'expires_in' => auth()->factory()->getTTL() * 60 * 24 * 7,
            // 'user' => auth()->user()
        ];
    }
}
