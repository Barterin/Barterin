<?php

namespace App\Http\Controllers\Auth;

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Validator;
use Illuminate\Http\Request;
use App\Models\User;
use App\Models\UserProfile;

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
                "username" => "required|string|unique:users|regex:/^[a-zA-Z0-9]+$/u",
                "fullname" => "required|string|regex:/^[a-zA-Z' ]+$/u",
                "email" => "required|email:rfc,dns|unique:users",
                "password" => "required",
            ], [
                'required' => 'kolom ini harus diisi',
                'username.unique' => 'nama pengguna sudah digunakan',
                'email.unique' => 'email sudah digunakan',
                'email' => 'format email harus benar',
                'regex' => "format inputan tidak benar"
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

            $userData = json_decode(strval(auth()->user()));

            $response = [
                'statusCode' => 200,
                'message' => 'Berhasil masuk',
                'verified_email' => $userData->verified_email,
                'access' => $token
            ];
        } catch (\Exception $error) {
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
        try {
            $userData = json_decode(strval(auth()->user()));
            $userProfile = UserProfile::where(["user_id" => $userData->id])->first();
            $data = [
                "id" => Encrypt($userData->id),
                "username" => $userData->username,
                "email" => $userData->email,
                "fullname" => $userData->fullname,
                "verified_email" => $userData->verified_email,
                "verified_account" => $userData->verified_account,
                "phone" => $userProfile->phone ?? "-",
                "born" => $userProfile->born ?? "-",
                "profile_picture" => $userProfile->profile_picture ?? "-",
                "gender" => $userProfile->gender ?? "-",
            ];
            $response = [
                "statusCode" => "200",
                "data" => $data
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
            'expires_in' => auth()->factory()->getTTL(),
            // 'user' => auth()->user()
        ];
    }

    public function setStatus(Request $request)
    {
        try {
            $userData = json_decode(strval(auth()->user()));

            if (!$request->has('status')) throw new \Exception('no status', 400);

            User::where(['id' => $userData->id])->update(['online' => $request->input('status') == '1' ? 'online' : 'offline']);

            $response = [
                "statusCode" => 200,
                "message" => "status updated"
            ];
        } catch (\Throwable | \Exception $error) {
            $response = [
                "statusCode" => GetStatusCode($error),
                "message" => $error->getMessage(),
            ];
        } finally {
            return response()->json(
                $response,
                $response['statusCode']
            );
        }
    }
}