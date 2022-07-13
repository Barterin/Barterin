<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateBarterItemsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('barter_items', function (Blueprint $table) {
            $table->id();
            $table->unsignedBigInteger('user_id');
            $table->unsignedBigInteger('category_id');
            $table->unsignedBigInteger('type_id');
            $table->unsignedBigInteger('address_id');
            $table->string('name', 255);
            $table->text('description');
            $table->integer('used_time');
            // 0 = tahun; 1 = bulan; 2 = minggu; 3 = hari
            $table->enum('date_unit', ['0', '1', '2', '3'])->default('0');
            $table->double('purchase_price');
            $table->enum('item_for', ['0', '1'])->default('0');
            $table->enum('deleted', ['0', '1'])->default('0');
            $table->timestamps();
        });

        Schema::table('barter_items', function (Blueprint $table) {
            $table->foreign('category_id')
                ->references('id')
                ->on('category_item')
                ->onUpdate('cascade')
                ->onDelete('cascade');
            $table->foreign('type_id')
                ->references('id')
                ->on('type_item')
                ->onUpdate('cascade')
                ->onDelete('cascade');
            $table->foreign('address_id')
                ->references('id')
                ->on('address')
                ->onUpdate('cascade')
                ->onDelete('cascade');
            $table->foreign('user_id')
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
        Schema::dropIfExists('barter_items');
    }
}