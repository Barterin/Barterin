<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class OfferDonate extends Model
{
    protected $table = 'offer_donate';

    protected $fillable = [
        "item_id",
        "with_user_id",
        "reason",
        "status"
    ];
}