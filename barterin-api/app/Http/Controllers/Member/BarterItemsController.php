<?php

namespace App\Http\Controllers\Member;

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Validator;
use Illuminate\Http\Request;
use Illuminate\Support\Str;
use App\Models\BarterItems as Table;
use App\Models\UploadImageBarang as TableUpload;
use App\Models\Address as TableAddress;

class BarterItemsController extends Controller
{

    function __construct()
    {
        $this->middleware('auth', ['except' => ['list']]);
        $this->validateRules = [
            "photo" => "array",
            "photo.*" => "required|image|mimes:jpeg,png,jpg,gif,svg|max:3072",
            // "category_id" => "required",
            "type_id" => "required",
            "address_id" => "required",
            "name" => "required|string|min:3|max:255",
            "description" => "required|min:20",
            "used_time" => "required|integer",
            // date unit 0: hari, 1: minggu, 2: bulan, 3: tahun
            "date_unit" => "required|in:0,1,2,3",
            "purchase_price" => "required|integer",
            // item_for 0: barter, 1: donasi
            "item_for" => "required|in:0,1",
        ];
        $this->validateMessage = [
            'required' => 'kolom ini harus diisi',
            'min' => 'kolom ini setidaknya harus memiliki :min karakter',
            'max' => 'kolom ini tidak boleh lebih dari :max karakter',
            'integer' => 'kolom ini harus diisi oleh nomor',
            'in' => 'kolom ini tidak diisi dengan pilihan yang benar',
            // 'photo[].required' => "Anda harus memasukan Foto barang"
            // 'regex' => 'kategori hanya boleh diisi oleh alfabet dan angka',
        ];
        $this->userData = json_decode(strval(auth()->user()));
    }

    public function list(Request $request)
    {
        try {

            $data = Table::orderBy("id", "desc");

            if ($request->itemsId != null)
                $data->where('id', Decrypt($request->itemsId));

            $data->where('user_id', $this->userData->id);
            $data->where('status', "!=", "2");
            $itemsData = [];

            $dateUnit = ["Hari", "Minggu", "Bulan", "Tahun"];

            foreach ($data->get() as $rows) {
                $imageItems = [];
                foreach (TableUpload::select('file_path')->where(['items_id' => $rows->id])->get() as $key) {
                    $imageItems[] = getenv('APP_URL') . "/" . $key->file_path;
                }
                $address = TableAddress::select('alamat_lengkap')->where(['id' => $rows->address_id])->get()->first()->alamat_lengkap;
                $row = [];
                $row["id"] = Encrypt($rows->id);
                $row['image'] = $imageItems;
                $row["name"] = $rows->name;
                $row["description"] = $rows->description;
                $row['used_time'] = $rows->used_time . " " . $dateUnit[$rows->date_unit];
                $row['purchase_price'] = $rows->purchase_price;
                $row['item_for'] = $rows->item_for;
                $row['address_item'] = $address;
                $itemsData[] = $row;
            }

            $response = [
                "statusCode" => 200,
                "data" => $itemsData
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

            $barterItems = array_merge(
                $request->all(),
                [
                    'user_id' => $userData->id,
                    // 'category_id' => Decrypt($request->input('category_id')),
                    'type_id' => Decrypt($request->input('type_id')),
                    'address_id' => Decrypt($request->input('address_id')),
                ]
            );

            $insertId = Table::create($barterItems)->id;

            if (!empty($request->photo)) {
                $fileCount  = count($request->file('photo'));
                $a = ($request->file('photo'));
                $fileCount;
                for ($i = 0; $i < $fileCount; $i++) {
                    $extension = $a[$i]->extension();
                    $fileName = md5(time() . $i) . "." . $extension;
                    $destinationPath = "uploads/images/barang/";
                    $finalArray = [
                        'items_id' => $insertId,
                        'file_path' => $destinationPath . $fileName,
                        'file_name' => $fileName
                    ];
                    TableUpload::create($finalArray);
                    $a[$i]->move($destinationPath, $fileName);
                }
            }

            $response = [
                'statusCode' => 200,
                'message' => "Barang berhasil dimasukan",
                'id' => Encrypt($insertId),
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

            if (!$request->itemsId) throw new \Exception('Bad request', 400);

            $data = Table::where(['id' => Decrypt($request->itemsId)]);

            if (!$data->get()->first()) throw new \Exception('Data not found', 404);

            if ($data->get()->first()->status != '0') throw new \Exception('Not available to delete', 404);

            $data->update([
                'status' => '2'
            ]);

            $response = [
                'statusCode' => 200,
                'message' => "Barang berhasil dihapus",
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

            $data = Table::where(['id' => Decrypt($request->itemsId)]);

            if (!$data->get()->first()) throw new \Exception('Data not found', 404);

            if ($data->get()->first()->status != '0') throw new \Exception('Not available to edit', 404);

            $data->update(array_merge(
                $request->all(),
                [
                    'category_id' => Decrypt($request->input('category_id')),
                    'type_id' => Decrypt($request->input('type_id')),
                    'address_id' => Decrypt($request->input('address_id')),
                ]
            ));

            $response = [
                'statusCode' => 200,
                'message' => "Barang berhasil diupdate",
            ];
        } catch (\Throwable | \Exception $error) {
            $response = [
                'statusCode' => GetStatusCode($error),
                'message' => $error->getMessage(),
                'input' => $validator->errors()
            ];
        } finally {
            return response()->json(
                array_merge($response),
                $response['statusCode']
            );
        }
    }
}