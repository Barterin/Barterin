<?php

namespace App\Http\Controllers\Member;

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;
use Illuminate\Support\Str;
use App\Models\Cart as Table;

class CartController extends Controller
{

    function __construct()
    {
        $this->middleware('auth', ['except' => ['list']]);
        $this->validateRules = [
            "items_id" => "required",
        ];
        $this->validateMessage = [
            'required' => 'kolom ini harus diisi',
        ];
        $this->userData = json_decode(strval(auth()->user()));
    }

    public function list(Request $request)
    {
        try {

            $data = Table::select(DB::raw('
                    cart.id id,
                    bi.id items_id,
                    bi.name items_name,
                    bi.status items_status,
                    add.kota_kecamatan items_region,
                    u.fullname items_user,
                    (SELECT file_path FROM upload_image WHERE items_id = bi.id ORDER BY id ASC LIMIT 1) items_image
                '))
                ->orderBy("cart.id", "desc")
                ->join('barter_items as bi', 'bi.id', '=', 'items_id')
                ->join('users as u', 'u.id', '=', 'bi.user_id')
                ->join('address as add', 'add.id', '=', 'bi.address_id');

            if ($request->cartId != null)
                $data->where('cart.id', Decrypt($request->cartId));

            $addressData = [];

            foreach ($data->get() as $rows) {
                $row = [];
                $row["id"] = Encrypt($rows->id);
                $row["barang"] = [
                    "id" => Encrypt($rows->items_id),
                    "name" => $rows->items_name,
                    "image" => env('APP_URL') . "/" . $rows->items_image,
                    "user" => $rows->items_user,
                    "region" => $rows->items_region,
                    "status" => $rows->items_status,
                ];
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

            if ($validator->fails())
                throw new \Exception('Kolom inputan tidak sesuai, periksa kembali kolom inputan anda', 400);

            if ($userData->verified_email == 'false')
                throw new \Exception('Email belum terverifikasi', 400);

            if (Table::where([
                'user_id' => $this->userData->id,
                'items_id' => Decrypt($request->input('items_id'))
            ])->get()->first()) {
                throw new \Exception('Barang sudah ada di keranjang', 400);
            }

            $cartData = [
                'user_id' => $this->userData->id,
                'items_id' => Decrypt($request->input('items_id'))
            ];

            $insertId = Table::create($cartData)->id;

            $response = [
                'statusCode' => 200,
                'message' => "Barang berhasil dimasukan ke keranjang",
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

            if (!$request->cartId) throw new \Exception('Bad request', 400);

            $data = Table::where(['id' => Decrypt($request->cartId)]);

            if (!$data->get()->first()) throw new \Exception('Data not found', 404);

            $data->delete();

            $response = [
                'statusCode' => 200,
                'message' => "Barang berhasil dihapus dari keranjang",
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
}