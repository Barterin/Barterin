<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class UploadImageBarang extends Model
{
    protected $table = 'upload_image';

    protected $fillable = [
        "items_id",
        "file_name",
        "file_path",
    ];
}