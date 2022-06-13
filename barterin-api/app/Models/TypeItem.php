<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class TypeItem extends Model
{
    protected $table = 'type_item';

    protected $fillable = [
        "category_id",
        "name",
        "slug",
    ];
}