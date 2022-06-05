package com.barterin.barterinapps.data.remote.retrofit

import com.barterin.barterinapps.data.remote.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun userLogin(
        @Field("username") username: String,
        @Field("password") password: String,
    ): LoginResult

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("fullname") fullname: String,
        @Field("password") password: String,
    ): RegisterResult

    @POST("auth/user-profile")
    suspend fun getDataUser(
        @Header("Authorization") token: String,
    ): GetDataUser

    @POST("auth/send-email")
    suspend fun emailVerification(
        @Header("Authorization") token: String
    ): EmailVerificationResponse

    @FormUrlEncoded
    @POST("auth/verify-email")
    suspend fun sendVerification(
        @Header("Authorization") token: String,
        @Field("verify_code") verify_code: String
    ): SendVerificationResponse

    @FormUrlEncoded
    @POST("address/store")
    suspend fun addAddress(
        @Header("Authorization") token: String,
        @Field("label") label: String,
        @Field("penerima") penerima: String,
        @Field("nohp") nohp: String,
        @Field("kota_kecamatan") kota_kecamatan: String,
        @Field("alamat_lengkap") alamat_lengkap: String,
        @Field("kode_pos") kode_pos: String,
    ): AddAddressResponse

    @GET("address/list")
    suspend fun getAddressList(
        @Header("Authorization") token: String,
    ): AddressResponse

    @FormUrlEncoded
    @POST("address/delete")
    suspend fun deleteAddress(
        @Header("Authorization") token: String,
        @Field("id") id: String
    ): DeleteAddressResponse


    @FormUrlEncoded
    @POST("address/update/{id}")
    suspend fun updateAddress(
        @Path("id") id: String,
        @Header("Authorization") token: String,
        @Field("label") label: String,
        @Field("penerima") penerima: String,
        @Field("nohp") nohp: String,
        @Field("kota_kecamatan") kota_kecamatan: String,
        @Field("alamat_lengkap") alamat_lengkap: String,
        @Field("kode_pos") kode_pos: String,
    ): AddAddressResponse

    @Multipart
    @POST("user/upload-photo")
    suspend fun uploadProfilePhoto(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): UploadPhotoResponse

    @FormUrlEncoded
    @POST("user/update")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Field("fullname") fullname: String,
        @Field("phone") phone: String,
        @Field("born") born: String,
        @Field("gender") gender: String
    ): EditProfileResponse

}