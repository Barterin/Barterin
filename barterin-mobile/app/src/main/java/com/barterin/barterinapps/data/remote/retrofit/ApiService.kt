package com.barterin.barterinapps.data.remote.retrofit

import com.barterin.barterinapps.data.remote.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
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
    @POST("member/address/store")
    suspend fun addAddress(
        @Header("Authorization") token: String,
        @Field("label") label: String,
        @Field("penerima") penerima: String,
        @Field("nohp") nohp: String,
        @Field("kota_kecamatan") kota_kecamatan: String,
        @Field("alamat_lengkap") alamat_lengkap: String,
        @Field("kode_pos") kode_pos: String,
    ): AddAddressResponse

    @GET("member/address/list")
    suspend fun getAddressList(
        @Header("Authorization") token: String,
    ): AddressResponse

    @DELETE("member/address/delete/{id}")
    suspend fun deleteAddress(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): DeleteAddressResponse


    @FormUrlEncoded
    @POST("member/address/update/{id}")
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
    @POST("member/user/upload-photo")
    suspend fun uploadProfilePhoto(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part
    ): UploadPhotoResponse

    @FormUrlEncoded
    @POST("member/user/update")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Field("fullname") fullname: String,
        @Field("phone") phone: String,
        @Field("born") born: String,
        @Field("gender") gender: String
    ): EditProfileResponse

    @GET("public/category")
    suspend fun getCategoryList(
        @Header("Authorization") token: String,
    ): Categories

    @GET("public/type")
    suspend fun getTypeList(
        @Query("category") category: String,
        @Header("Authorization") token: String,
    ): TypeResponse

    @Multipart
    @POST("member/items/store")
    suspend fun uploadItem(
        @Header("Authorization") token: String,
        @Part("type_id") type_id: RequestBody,
        @Part("address_id") address_id: RequestBody,
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
        @Part("used_time") used_time: RequestBody,
        @Part("date_unit") date_unit: RequestBody,
        @Part("purchase_price") purchase_price: RequestBody,
        @Part("item_for") item_for: RequestBody,
        @Part file: Array<MultipartBody.Part>
    ) : UploadImageResult

    @GET("public/items/barter")
    suspend fun getAllBarterItems(): BarterDataResponse

    @GET("public/items/barter")
    suspend fun searchBarterItem(
        @Query("search") search: String,
    ): BarterDataResponse

    @POST("auth/logout")
    suspend fun logoutUser(
        @Header("Authorization") token: String,
    ): LogoutResponse

    @FormUrlEncoded
    @POST("member/cart/store")
    suspend fun addToCart(
        @Header("Authorization") token: String,
        @Field("items_id") items_id: String,
    ): AddToCartResponse

    @GET("member/cart/list")
    suspend fun getAllChart(
        @Header("Authorization") token: String
    ): GetCartResponse

    @DELETE("member/cart/delete/{id}")
    suspend fun deleteChart(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): DeleteAddressResponse

    @FormUrlEncoded
    @POST("member/offer/store")
    suspend fun barterOffer(
        @Header("Authorization") token: String,
        @Field("item_id") item_id: String,
        @Field("with_item_id") with_item_id: String,
    ): DeleteAddressResponse

    @GET("member/items/list")
    suspend fun getMyItems(
        @Header("Authorization") token: String
    ) : GetMyItemsResponse

    @GET("member/offer/list")
    suspend fun getOfferBarter(
        @Header("Authorization") token: String
    ) : GetOfferResponse

    @FormUrlEncoded
    @POST("member/offer/accept")
    suspend fun acceptOffer(
        @Header("Authorization") token: String,
        @Field("offerId") offerId: String,
    ) : AcceptOfferResponse

    @GET("member/offer/list/bidder")
    suspend fun getBidderList(
        @Query("itemId") itemId: String,
        @Header("Authorization") token: String,
    ) : GetOfferResponse

}