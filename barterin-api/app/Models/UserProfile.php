<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class UserProfile extends Model
{
    protected $table = 'profiles';

    protected $fillable = [
        "user_id",
        "phone",
        "born",
        "profile_picture",
        "gender",
    ];
}
