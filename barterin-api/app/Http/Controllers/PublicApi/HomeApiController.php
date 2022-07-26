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
use App\Models\CategoryItem as TableCatetgoryItems;
use App\Models\TypeItem as TableTypeItems;

class HomeApiController extends Controller
{
    public function __construct()
    {
    }

    private function getItemsData($itemsFor)
    {
        $data = ItemTable::select(DB::raw('
                barter_items.id items_id,
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
                add.latitude address_latitude,
                (SELECT count(*) FROM offer WHERE item_id = barter_items.id) bidder
            '))
            ->orderBy("id", "desc")
            ->join('type_item as type', 'type.id', '=', 'barter_items.type_id')
            ->join('category_item as cat', 'cat.id', '=', 'type.category_id')
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
                    "id" => Encrypt($rows->items_id),
                    "image" => $imageItems,
                    "name" => $rows->name,
                    "description" => $rows->description,
                    "used_time" => $rows->used_time . " " . $dateUnit[$rows->date_unit],
                    "purchase_price" => $rows->purchase_price,
                    "address_item" => $rows->address_full,
                    "address_region" => $rows->address_region,
                    "address_longitude" => $rows->address_longitude,
                    "address_latitude" => $rows->address_latitude,
                    "bidder" => $rows->bidder
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


    public function category(Request $request)
    {
        try {

            $data = TableCatetgoryItems::select(DB::raw('category_item.*, (
                    SELECT count(*) FROM barter_items as items 
                    JOIN type_item as type ON type.id = items.type_id
                    JOIN category_item as cat ON cat.id = type.category_id
                    WHERE cat.id = category_item.id
                ) as count'))
                ->orderBy('category_item.name', 'asc');

            if ($request->slug != null)
                $data->where('slug', $request->slug);

            if ($request->search != null)
                $data->where('name', 'like', '%' . $request->search . '%');

            $catData = [];
            foreach ($data->get() as $key) {
                $row = [];
                $row['id'] = Encrypt($key->id);
                $row['name'] = $key->name;
                $row['slug'] = $key->slug;
                $row['count'] = $key->count;
                $row['image'] = $key->image != null ? getenv('APP_URL') . "/uploads/images/category/" . $key->image : null;
                $catData[] = $row;
            }

            $response = [
                'statusCode' => 200,
                'data' => $catData
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

    public function type(Request $request)
    {
        try {

            $data = TableTypeItems::orderBy('name', 'asc');

            if ($request->slug != null)
                $data->where('slug', $request->slug);

            if ($request->search != null)
                $data->where('name', 'like', '%' . $request->search . '%');

            if ($request->categoryId != null)
                $data->where('category_id', Decrypt($request->categoryId));

            $catData = [];
            foreach ($data->get() as $key) {
                $row = [];
                $row['id'] = Encrypt($key->id);
                $row['categoryId'] = Encrypt($key->category_id);
                $row['name'] = $key->name;
                $row['slug'] = $key->slug;
                $catData[] = $row;
            }

            $response = [
                'statusCode' => 200,
                'data' => $catData
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