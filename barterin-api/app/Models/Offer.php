<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Offer extends Model
{
    protected $table = 'offer';

    protected $fillable = [
        "item_id",
        "with_item_id",
        "status"
    ];
}