<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class SetAllowNullOnTableProfiles extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('profiles', function (Blueprint $table) {
            $table->string('phone')->nullable()->change();
            $table->string('born')->nullable()->change();
            $table->string('gender')->nullable()->change();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('profiles', function (Blueprint $table) {
            $table->string('phone')->nullable(false)->change();
            $table->string('born')->nullable(false)->change();
            $table->string('gender')->nullable(false)->change();
        });
    }
}