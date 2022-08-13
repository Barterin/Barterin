<?php

namespace App\Http\Controllers\Member;

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;
use Illuminate\Support\Str;
use App\Models\Message as Table;
use App\Models\User as TableUser;

class MessageController extends Controller
{

    function __construct()
    {
        $this->middleware('auth');
        $this->validateRules = [
            "target_id" => "required",
            "message" => "required",
        ];
        $this->validateMessage = [
            'required' => 'isi kolom ini',
        ];
        $this->userData = json_decode(strval(auth()->user()));
    }

    public function list(Request $request)
    {
        try {

            $data = Table::select(DB::raw('
                    user.fullname as name, 
                    message.target_id as target,
                    (SELECT content FROM message WHERE target_id = target ORDER BY id DESC LIMIT 1) as last_message,
                    prop.profile_picture,
                    message.created_at as time
                '))
                ->join('users as user', 'user.id', '=', 'message.target_id')
                ->leftJoin('profiles as prop', 'prop.user_id', '=', 'message.target_id')
                ->where(['sender_id' => $this->userData->id])
                ->orderBy('message.id', 'desc')
                ->groupBy('message.target_id');

            $chatList = [];

            foreach ($data->get() as $rows) {
                $chatList[] =
                    [
                        "user_id" => Encrypt($rows->target),
                        "name" => $rows->name,
                        "profile_picture" => $rows->profile_picture != null ? getenv('APP_URL') . "/uploads/images/profiles/" . $rows->profile_picture : getenv('APP_URL') . "/uploads/images/profiles/default.png",
                        "last_message" => $rows->last_message,
                        "time" => $rows->time
                    ];
            }

            $response = [
                "statusCode" => 200,
                "user" => [
                    "id" => md5(md5(md5($this->userData->id))),
                    "name" => $this->userData->fullname
                ],
                "data" => $chatList
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

    public function getFrom(Request $request)
    {
        try {

            $dataSender = Table::where(['sender_id' => $this->userData->id, 'target_id' => Decrypt($request->id)])
                ->orderBy('message.id', 'desc');
            $dataTarget = Table::where(['sender_id' => Decrypt($request->id), 'target_id' => $this->userData->id])
                ->orderBy('message.id', 'desc');

            $data = $dataSender->union($dataTarget)->orderBy('id', 'asc');
            $chat = [];

            $user = TableUser::select(DB::raw('users.*, prop.profile_picture'))
                ->leftJoin('profiles as prop', 'prop.user_id', '=', 'users.id')
                ->where(['users.id' => Decrypt($request->id)])->get()->first();

            foreach ($data->get() as $rows) {
                $chat[] = [
                    'direction' => $rows->sender_id == $this->userData->id ? 'out' : 'in',
                    'message' => $rows->content,
                    "time" => $rows->created_at
                ];
            }

            $response = [
                "statusCode" => 200,
                "user" => [
                    "id" => md5(md5(md5($user->id))),
                    "name" => $user->fullname,
                    "profile_picture" => $user->profile_picture != null ? getenv('APP_URL') . "/uploads/images/profiles/" . $user->profile_picture : getenv('APP_URL') . "/uploads/images/profiles/default.png",
                ],
                "data" => $chat
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

            $chatData = [
                'sender_id' => $userData->id,
                'target_id' => Decrypt($request->input('target_id')),
                'content' => $request->message
            ];

            $insertId = Table::create($chatData)->id;

            $response = [
                'statusCode' => 200,
                'message' => "Pesan terkirim",
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
}