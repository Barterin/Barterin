<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class DivideKotaKecamatanColumn extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::table('address', function (Blueprint $table) {
            $table->renameColumn('kota_kecamatan', 'kota');
            $table->string('kecamatan')->nullable()->after('kota_kecamatan');
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
            $table->renameColumn('kota', 'kota_kecamatan');
            $table->dropColumn('kecamatan');
        });
    }
}
