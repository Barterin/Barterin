<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateOfferTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('offer', function (Blueprint $table) {
            $table->id();
            $table->unsignedBigInteger('item_id');
            $table->unsignedBigInteger('with_item_id');
            $table->enum("status", ['0', '1', '2'])->default('0');
            $table->timestamps();
        });

        Schema::table('offer', function (Blueprint $table) {
            $table->foreign('item_id')
                ->references('id')
                ->on('barter_items')
                ->onUpdate('cascade')
                ->onDelete('cascade');
            $table->foreign('with_item_id')
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
        Schema::dropIfExists('offer');
    }
}