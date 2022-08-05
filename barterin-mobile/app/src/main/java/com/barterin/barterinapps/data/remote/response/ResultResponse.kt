package com.barterin.barterinapps.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import retrofit2.http.Field
import java.io.Serializable


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
) : Parcelable

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

@Parcelize
data class CategoriesResult(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("slug")
    val slug: String,
) : Parcelable

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

data class BarterDataResponse(

    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("data")
    val data: List<DataItem>,
    )


data class DataItem(

    @field:SerializedName("user")
    val user: UserItem,

    @field:SerializedName("category")
    val category: CategoryItem,

    @field:SerializedName("type")
    val type: TypeItem,

    @field:SerializedName("item")
    val item: ItemBarter,

    )

data class ItemBarter(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("image")
    val image: List<String>,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("used_time")
    val used_time: String,

    @field:SerializedName("purchase_price")
    val purchase_price: String,

    @field:SerializedName("address_item")
    val address_item: String,

    @field:SerializedName("address_region")
    val address_region: String,

    @field:SerializedName("address_longitude")
    val address_longitude: String,

    @field:SerializedName("address_latitude")
    val address_latitude: String,

    @field:SerializedName("bidder")
    val bidder: String,
)

data class UserItem(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("phone")
    val phone: String
)

data class CategoryItem(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,
)

data class TypeItem(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,
)

data class LogoutResponse(
    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("message")
    val message: String,
)

data class AddToCartResponse(
    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("id")
    val id: String,
)


data class GetCartResponse(
    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("data")
    val data: List<DataCartResult>,
)

data class DataCartResult(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("barang")
    val barang: BarangDataResult,
)

data class BarangDataResult(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("user")
    val user: String,

    @field:SerializedName("region")
    val region: String,

    @field:SerializedName("status")
    val status: String,
)

data class GetMyItemsResponse(
    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("data")
    val data: List<GetMyItems>
)

data class GetMyItems(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("image")
    val image: List<String>,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("used_time")
    val used_time: String,

    @field:SerializedName("purchase_price")
    val purchase_price: String,

    @field:SerializedName("item_for")
    val item_for: String,

    @field:SerializedName("address_item")
    val address_item: String,
)

data class GetOfferResponse(
    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("data")
    val data: List<OfferData>
)

data class OfferData(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("barang")
    val barang: DataBarangOffer
)

data class DataBarangOffer (
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("user")
    val user: String,

    @field:SerializedName("region")
    val region: String,

    @field:SerializedName("status")
    val status: String,
)

data class AcceptOfferResponse (
    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("message")
    val message: String,
)

data class TypeResponse (
    @field:SerializedName("statusCode")
    val statusCode: Int,

    @field:SerializedName("message")
    val data: List<DataTypes>,
)

data class DataTypes (
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("categoryId")
    val categoryId: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("slug")
    val slug: String,
)





