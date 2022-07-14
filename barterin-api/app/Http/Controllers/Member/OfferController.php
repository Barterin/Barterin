<?php

namespace App\Http\Controllers\Member;

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Validator;
use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;
use Illuminate\Support\Str;
use App\Models\Offer as Table;
use App\Models\BarterItems as TableItems;

class OfferController extends Controller
{

    function __construct()
    {
        $this->middleware('auth', ['except' => ['list']]);
        $this->validateRules = [
            "item_id" => "required",
            "with_item_id" => "required",
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
                    offer.id id,
                    bi.id items_id,
                    bi.name items_name,
                    bi.status items_status,
                    add.kota_kecamatan items_region,
                    u.fullname items_user,
                    (SELECT file_path FROM upload_image WHERE items_id = bi.id ORDER BY id ASC LIMIT 1) items_image
                '))
                ->orderBy("offer.id", "desc")
                ->join('barter_items as bi', 'bi.id', '=', 'item_id')
                ->join('users as u', 'u.id', '=', 'bi.user_id')
                ->join('address as add', 'add.id', '=', 'bi.address_id')
                ->groupBy('offer.item_id');

            $data->where('u.id', $this->userData->id);

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

    public function listBidder(Request $request)
    {
        try {

            $data = Table::select(DB::raw(' 
                    offer.id id,
                    bi.id items_id,
                    bi.name items_name,
                    bi.status items_status,
                    add.kota_kecamatan items_region,
                    u.fullname items_user,
                    (SELECT file_path FROM upload_image WHERE items_id = bi.id ORDER BY id ASC LIMIT 1) items_image
                '))
                ->orderBy("offer.id", "desc")
                ->join('barter_items as bi', 'bi.id', '=', 'with_item_id')
                ->join('users as u', 'u.id', '=', 'bi.user_id')
                ->join('address as add', 'add.id', '=', 'bi.address_id');

            $data->where('offer.item_id', Decrypt($request->itemId));

            $barang = TableItems::where(["id" => Decrypt($request->itemId)])->get()->first();

            if ($barang->user_id != $this->userData->id) throw new \Exception('Bukan Barang Anda', 401);

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

            $barang = TableItems::where(["id" => Decrypt($request->input('item_id')), "status" => '0'])->get()->first();
            $barangPenawar = TableItems::where(["id" => Decrypt($request->input('with_item_id'))])->get()->first();

            if (!$barang) throw new \Exception('Barang tidak tersedia', 404);
            if (!$barangPenawar) throw new \Exception('Barang Anda sudah tidak tersedia', 404);
            if ($barangPenawar->user_id != $userData->id) throw new \Exception('Bukan barang Anda', 401);
            if ($barangPenawar->item_for != "0") throw new \Exception('Barang yang Anda masukan bukan barang untuk barter', 400);

            if (Table::where([
                'item_id' => Decrypt($request->input('item_id')),
                'with_item_id' => Decrypt($request->input('with_item_id'))
            ])->get()->first()) {
                throw new \Exception('Barang sudah ada di penawaran', 400);
            }

            $offerData = [
                'item_id' => Decrypt($request->input('item_id')),
                'with_item_id' => Decrypt($request->input('with_item_id'))
            ];

            $insertId = Table::create($offerData)->id;

            $response = [
                'statusCode' => 200,
                'message' => "Barang berhasil dimasukan ke penawaran",
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

            if (!$request->offerId) throw new \Exception('Bad request', 400);

            $data = Table::where(['id' => Decrypt($request->offerId)]);

            if (!$data->get()->first()) throw new \Exception('Data not found', 404);

            $data->delete();

            $response = [
                'statusCode' => 200,
                'message' => "Barang berhasil dihapus dari penawaran",
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

    public function acceptOffer(Request $request)
    {
        try {

            $response = ["statusCode" => 500];

            if (!$request->has('offerId')) throw new \Exception('no offerId', 400);

            $offer = Table::where(["id" => Decrypt($request->offerId)])->get()->first();

            if (!$offer) throw new \Exception('Penawaran tidak ditemukan', 404);

            $barang1 = TableItems::where(['id' => $offer->item_id])->get()->first();

            if ($barang1->user_id != $this->userData->id) throw new \Exception('Bukan barang Anda', 401);

            $offer->update([
                'status' => '1'
            ]);

            TableItems::where(['id' => $offer->item_id])->update(['status' => '1']);
            TableItems::where(['id' => $offer->with_item_id])->update(['status' => '1']);

            $response = [
                'statusCode' => 200,
                'message' => "Tawaran berhasil diterima !",
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