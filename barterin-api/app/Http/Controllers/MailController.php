<?php

namespace App\Http\Controllers;

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

            if (!$request->has('email') || trim($request->get('email')) == '') throw new \Exception("Bad request", 400);

            $getData = User::select('email', 'fullname', 'verify_code', 'verified_email')->where(['email' => trim($request->get('email'))])->get()->first();

            if (empty($getData)) throw new \Exception('Email tidak ditemukan', 400);

            $expiresIn = 5;

            $verify = [
                'verify_code' => GenerateRandomNumber(),
                'verify_code_expired' => date("Y-m-d H:i:s", strtotime("+$expiresIn minutes")),
                'expiresIn' => $expiresIn
            ];

            $this->userData = array_merge($getData->toarray(), $verify);

            // print_r($this->userData);

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
                'message' => "Kode verifikasi telah dikirim ke email Anda",
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

            if (!$request->has(['verify_code', 'email'])) throw new \Exception("Bad request", 400);

            $getData = User::select('email', 'verify_code', 'verified_email')->where($request->all())->get()->first();

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