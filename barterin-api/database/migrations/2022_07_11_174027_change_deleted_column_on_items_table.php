<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class ChangeDeletedColumnOnItemsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('barter_items', function (Blueprint $table) {
            $table->dropColumn('deleted');
            $table->enum('status', ['0', '1', '2'])->default('0')->after('item_for');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('barter_items', function (Blueprint $table) {
            $table->dropColumn('status');
            $table->enum('deleted', ['0', '1'])->default('0')->after('item_for');
        });
    }
}