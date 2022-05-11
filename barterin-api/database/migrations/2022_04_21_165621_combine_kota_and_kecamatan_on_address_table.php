<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CombineKotaAndKecamatanOnAddressTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('address', function (Blueprint $table) {
            $table->dropColumn('kecamatan');
            $table->renameColumn('kota', 'kota_kecamatan');
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::table('address', function (Blueprint $table) {
            $table->renameColumn('kota_kecamatan', 'kota');
            $table->string('kecamatan')->after('kota_kecamatan');
        });
    }
}