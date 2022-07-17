<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class DropCategoryIdOnBarterItemsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table("barter_items", function (Blueprint $table) {
            $table->dropForeign(['category_id']);
            $table->dropColumn('category_id');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table("barter_items", function (Blueprint $table) {
            $table->unsignedBigInteger('category_id')->after('user_id');
        });
        Schema::table('barter_items', function (Blueprint $table) {
            $table->foreign('category_id')
                ->references('id')
                ->on('category_item')
                ->onUpdate('cascade')
                ->onDelete('cascade');
        });
    }
}