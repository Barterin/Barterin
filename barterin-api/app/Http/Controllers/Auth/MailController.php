<?php

namespace App\Http\Controllers\Auth;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Mail;
use App\Models\User;

class MailController extends Controller
{

    public function __construct()
    {
        $this->middleware('auth');
        $this->userData = [];
    }

    public function sendEmailVerification(Request $request)
    {
        try {
            $userData = json_decode(strval(auth()->user()));

            $getData = User::select('email', 'fullname', 'verify_code', 'verified_email')->where(['email' => trim($userData->email)])->get()->first();

            if (empty($getData)) throw new \Exception('Email tidak ditemukan', 400);

            $expiresIn = 2;

            $verify = [
                'verify_code' => GenerateRandomNumber(),
                'verify_code_expired' => date("Y-m-d H:i:s", strtotime("+$expiresIn minutes")),
                'expiresIn' => $expiresIn
            ];

            $this->userData = array_merge($getData->toarray(), $verify);

            User::where('email', $this->userData['email'])
                ->update([
                    'verify_code' => $verify['verify_code'],
                    'verify_code_expired' => $verify['verify_code_expired'],
                ]);


            Mail::send("mail", $this->userData, function ($message) {
                $message->to($this->userData['email'], $this->userData['fullname']);
                $message->subject("Kode verifikasi akun Barterin");
                $message->from("barterintech@gmail.com", "Barterin Support");
            });

            $response = [
                'statusCode' => 200,
                'message' => "Kode verifikasi telah dikirim ke email Anda " . $userData->email,
            ];
        } catch (\Exception $error) {
            $response = [
                'statusCode' => GetStatusCode($error),
                'message' => $error->getMessage()
            ];
        } finally {
            return response()->json(
                [
                    "statusCode" => $response['statusCode'],
                    "message" => $response['message'],
                ],
                $response['statusCode']
            );
        }
    }

    public function verifyEmail(Request $request)
    {
        try {

            if (!$request->has(['verify_code'])) throw new \Exception("Bad request", 400);

            $userData = json_decode(strval(auth()->user()));

            $where = [
                "email" => $userData->email,
                "verify_code" => $request->input('verify_code')
            ];

            $getData = User::select('email', 'verify_code', 'verified_email')->where($where)->get()->first();

            if (empty($getData)) throw new \Exception('Kode verifikasi salah !', 400);

            if ($getData->verified_email == 'true') throw new \Exception('Email sudah diverifikasi', 400);

            User::where($request->all())->update(['verified_email' => 'true']);

            $response = [
                'statusCode' => '200',
                'message' => 'Email berhasil diverifikasi'
                // 'message' => $getData
            ];
        } catch (\Exception $error) {
            $response = [
                'statusCode' => GetStatusCode($error),
                'message' => $error->getMessage()
            ];
        } finally {
            return response()->json(
                [
                    "statusCode" => $response['statusCode'],
                    "message" => $response['message'],
                ],
                $response['statusCode']
            );
        }
    }
}