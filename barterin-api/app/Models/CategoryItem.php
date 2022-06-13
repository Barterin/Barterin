<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class CategoryItem extends Model
{
    protected $table = 'category_item';

    protected $fillable = [
        "name",
        "slug",
    ];
}