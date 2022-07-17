<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateOfferDonateTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('offer_donate', function (Blueprint $table) {
            $table->id();
            $table->unsignedBigInteger('item_id');
            $table->unsignedBigInteger('with_user_id');
            $table->longText('reason');
            $table->enum("status", ['0', '1', '2'])->default('0');
            $table->timestamps();
        });
        Schema::table('offer_donate', function (Blueprint $table) {
            $table->foreign('item_id')
                ->references('id')
                ->on('barter_items')
                ->onUpdate('cascade')
                ->onDelete('cascade');
            $table->foreign('with_user_id')
                ->references('id')
                ->on('users')
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
        Schema::dropIfExists('offer_donate');
    }
}