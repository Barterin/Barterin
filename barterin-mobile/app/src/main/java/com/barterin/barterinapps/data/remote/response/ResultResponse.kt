package com.barterin.barterinapps.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class GetDataUser(

    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("data")
    val data: UserResponse,

)

data class RegisterResult(
    @field:SerializedName("message")
    val message: String,
)

data class LoginResult(

    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("access")
    val access: AccessToken

)

data class AccessToken(

    @field:SerializedName("access_token")
    val access_token: String,

    @field:SerializedName("token_type")
    val token_type: String,

    @field:SerializedName("expires_in")
    val expires_in: Int

)

data class EmailVerificationResponse(
    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("message")
    val message: String,
)

data class SendVerificationResponse(
    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("message")
    val message: String,
)

data class AddAddressResponse(
    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("data")
    val message: String,
)

data class AddressResponse(
    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("data")
    val data: List<AddressResult>,
)

@Parcelize
data class AddressResult(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("penerima")
    val penerima: String,

    @field:SerializedName("nohp")
    val nohp: String,

    @field:SerializedName("label")
    val label: String,

    @field:SerializedName("kota_kecamatan")
    val kota_kecamatan: String,

    @field:SerializedName("alamat_lengkap")
    val alamat_lengkap: String,

    @field:SerializedName("kode_pos")
    val kode_pos: String,
): Parcelable

data class DeleteAddressResponse(
    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("message")
    val message: String,
)

data class UploadPhotoResponse(
    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("message")
    val message: String,
)

data class EditProfileResponse(
    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("message")
    val message: String,
)

data class Categories(
    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("data")
    val data: List<CategoriesResult>,
)


data class CategoriesResult(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("slug")
    val slug: String,
)

data class Type(
    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("data")
    val data: List<TypeResult>,
)

data class TypeResult(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("categoryId")
    val categoryId: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("slug")
    val slug: String,
)

data class UploadImageResult(
    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("id")
    val id: String,
)


