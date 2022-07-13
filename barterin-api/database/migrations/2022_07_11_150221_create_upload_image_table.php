<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateUploadImageTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('upload_image', function (Blueprint $table) {
            $table->id();
            $table->unsignedBigInteger('items_id');
            $table->string('file_name');
            $table->string('file_path');
            $table->timestamps();
        });

        Schema::table('upload_image', function (Blueprint $table) {
            $table->foreign('items_id')
                ->references('id')
                ->on('barter_items')
                ->onUpdate('cascade')
                ->onDelete('cascade');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('upload_image');
    }
}