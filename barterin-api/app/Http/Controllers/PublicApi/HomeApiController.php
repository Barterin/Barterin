<?php

namespace App\Http\Controllers\PublicApi;

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Validator;
use Illuminate\Http\Request;
use Symfony\Component\HttpFoundation\BinaryFileResponse;
use Illuminate\Support\Facades\DB;

use App\Models\BarterItems as ItemTable;
use App\Models\UploadImageBarang as ImageUpload;
use App\Models\Address as TableAddress;
use App\Models\User as TableUsers;

class HomeApiController extends Controller
{
    public function __construct()
    {
    }

    private function getItemsData($itemsFor)
    {
        $data = ItemTable::select(DB::raw('
                barter_items.*, 
                cat.id cat_id, 
                cat.name cat_name, 
                type.id type_id, 
                type.name type_name,
                u.id user_id,
                u.fullname user_fullname,
                prof.phone user_phone,
                add.alamat_lengkap address_full,
                add.kota_kecamatan address_region,
                add.longitude address_longitude,
                add.latitude address_latitude
            '))
            ->orderBy("id", "desc")
            ->join('category_item as cat', 'cat.id', '=', 'barter_items.category_id')
            ->join('type_item as type', 'type.id', '=', 'barter_items.type_id')
            ->join('users as u', 'u.id', '=', 'barter_items.user_id')
            ->join('profiles as prof', 'prof.user_id', '=', 'barter_items.user_id')
            ->join('address as add', 'add.id', '=', 'barter_items.address_id');
        $data->where('item_for', $itemsFor);
        // dd($data->get());
        return $data;
    }

    public function itemListBarter(Request $request)
    {
        try {

            $skip = $request->skip ?? 0;
            $take = $request->take ?? 10;

            $data = $this->getItemsData('0');

            $data->where('status', "0");

            if ($request->itemsId != null)
                $data->where('barter_items.id', Decrypt($request->itemsId));

            if ($request->userId != null)
                $data->where('barter_items.user_id', Decrypt($request->userId));

            if ($request->categoryId != null)
                $data->where('barter_items.category_id', Decrypt($request->categoryId));

            if ($request->typeId != null)
                $data->where('barter_items.type_id', Decrypt($request->typeId));

            if ($request->search != null) {
                $data->where('barter_items.name', 'like', '%' . $request->search . '%');
                $data->orWhere('barter_items.description', 'like', '%' . $request->search . '%');
                $data->orWhere('cat.name', 'like', '%' . $request->search . '%');
                $data->orWhere('type.name', 'like', '%' . $request->search . '%');
            }

            $data->skip($skip)->take($take);

            $itemsData = [];

            $dateUnit = ["Hari", "Minggu", "Bulan", "Tahun"];

            foreach ($data->get() as $rows) {
                $imageItems = [];
                foreach (ImageUpload::select('file_path')->where(['items_id' => $rows->id])->get() as $key) {
                    $imageItems[] = getenv('APP_URL') . "/" . $key->file_path;
                }

                $row = [];
                $row['user'] = [
                    "id" => Encrypt($rows->user_id),
                    "name" => $rows->user_fullname,
                    "phone" => $rows->user_phone
                ];
                $row['category'] = [
                    "id" => Encrypt($rows->cat_id),
                    "name" => $rows->cat_name,
                ];
                $row['type'] = [
                    "id" => Encrypt($rows->type_id),
                    "name" => $rows->type_name,
                ];
                // $row['image'] = $imageItems;
                $row['item'] = [
                    "id" => Encrypt($rows->id),
                    "image" => $imageItems,
                    "name" => $rows->name,
                    "description" => $rows->description,
                    "used_time" => $rows->used_time . " " . $dateUnit[$rows->date_unit],
                    "purchase_price" => $rows->purchase_price,
                    "address_item" => $rows->address_full,
                    "address_region" => $rows->address_region,
                    "address_longitude" => $rows->address_longitude,
                    "address_latitude" => $rows->address_latitude,
                ];
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

    public function itemListDonate(Request $request)
    {
        try {

            $skip = $request->skip ?? 0;
            $take = $request->take ?? 10;

            $data = $this->getItemsData('1');

            $data->where('status', "0");

            if ($request->itemsId != null)
                $data->where('id', Decrypt($request->itemsId));

            if ($request->userId != null)
                $data->where('user_id', Decrypt($request->userId));

            $data->skip($skip)->take($take);

            $itemsData = [];

            $dateUnit = ["Hari", "Minggu", "Bulan", "Tahun"];

            foreach ($data->get() as $rows) {
                $imageItems = [];
                foreach (ImageUpload::select('file_path')->where(['items_id' => $rows->id])->get() as $key) {
                    $imageItems[] = getenv('APP_URL') . "/" . $key->file_path;
                }
                $address = TableAddress::select('alamat_lengkap', 'kota_kecamatan')->where(['id' => $rows->address_id])->get()->first();
                $user = TableUsers::select('users.id', 'fullname', 'phone')->where(['users.id' => $rows->user_id])
                    ->join('profiles', 'users.id', '=', 'profiles.user_id')
                    ->get()->first();
                $row = [];
                $row["id"] = Encrypt($rows->id);
                $row['user'] = [
                    "id" => Encrypt($user->id),
                    "name" => $user->fullname,
                    "phone" => $user->phone
                ];
                $row['image'] = $imageItems;
                $row["name"] = $rows->name;
                $row["description"] = $rows->description;
                $row['used_time'] = $rows->used_time . " " . $dateUnit[$rows->date_unit];
                // $row['purchase_price'] = $rows->purchase_price;
                // $row['item_for'] = $rows->item_for;
                $row['address_item'] = $address->alamat_lengkap;
                $row['address_region'] = $address->kota_kecamatan;
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
}