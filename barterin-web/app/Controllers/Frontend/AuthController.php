<?php

namespace App\Controllers\Frontend;

use App\Controllers\BaseController;

class AuthController extends BaseController
{

    function __construct()
    {
        helper(['cookie', 'request_api', 'url']);
        $accessToken = get_cookie("accessToken");
        if (!empty($accessToken)) {
            $uri = service('uri');
            $response = json_decode(ApiPost("/auth/user-profile"));
            if ($response->statusCode == 200 && $uri->getSegment("2") != 'logout') {
                header("refresh:0,url=" . base_url());
            }
        }
    }

    public function login()
    {
        $data = [
            'title' => 'Barterin | Sign in',
        ];

        return View('frontend/auth/login', $data);
    }

    public function register()
    {
        $data = [
            'title' => 'Barterin | Sign up',
        ];
        return View('frontend/auth/register', $data);
    }

    public function forgetPassword()
    {
        $data = [
            'title' => 'Barterin | Forgot Password',
        ];

        return View('frontend/auth/forgetPassword', $data);
    }

    public function emailVerification()
    {
        $data = [
            'title' => 'Barterin | Email Verification',
        ];

        return View('frontend/auth/emailVerification', $data);
    }

    public function authenticating($accessToken, $verifiedEmail)
    {
        echo "redirecting . . .";
        $token = base64_decode($accessToken);
        setcookie("accessToken", $token, time() + (86400 * 30), "/");
        if ($verifiedEmail == "false") {
            $curl = curl_init();
            curl_setopt_array($curl, array(
                CURLOPT_URL => getenv("app.apiUrl") . "/auth/send-email",
                CURLOPT_RETURNTRANSFER => true,
                CURLOPT_ENCODING => '',
                CURLOPT_MAXREDIRS => 10,
                CURLOPT_TIMEOUT => 0,
                CURLOPT_FOLLOWLOCATION => true,
                CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
                CURLOPT_CUSTOMREQUEST => 'POST',
                CURLOPT_HTTPHEADER => array(
                    "Authorization: Bearer $token"
                ),
            ));

            $response = curl_exec($curl);

            curl_close($curl);
            // echo $response;
            $responseData = json_decode($response);
            session()->setFlashdata("message", $responseData->message);
            header("refresh:1;url=" . base_url("auth/email-verification"));
        } else {
            header("refresh:1;url=" . base_url());
        }
    }
}