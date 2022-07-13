<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class BarterItems extends Model
{
    protected $table = 'barter_items';

    protected $fillable = [
        "user_id",
        "category_id",
        "type_id",
        "address_id",
        "name",
        "description",
        "used_time",
        "date_unit",
        "purchase_price",
        "item_for",
        "created_at",
        "updated_at",
    ];

    public function category()
    {
        return $this->hasOne(CategoryItem::class);
    }
}