package com.barterin.barterinapps.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.barterin.barterinapps.data.remote.response.*
import com.barterin.barterinapps.data.remote.retrofit.ApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody


class BarterinRepository private constructor(
    private val apiService: ApiService,
) {

    fun login(
        username: String,
        password: String
    ): LiveData<Result<LoginResult>> = liveData {
        emit(Result.Loading)
        try {

            val response = apiService.userLogin(
                username,
                password
            )

            if (response.statusCode == 200) {
                Log.d("error response", "true: ${response.message} ")
                emit(Result.Success(response))
            } else {
                Log.d("error response", "false: ${response.message} ")
                emit(Result.Error(response.message))
            }
        } catch (e: Exception) {
            Log.d("BarterinRepository", "error: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }

    }

    fun register(
        username: String,
        email: String,
        fullName: String,
        password: String
    ): LiveData<Result<RegisterResult>> = liveData {
        emit(Result.Loading)
        try {

            val response = apiService.register(
                username,
                email,
                fullName,
                password
            )

            if (response.message == "Create data success") {
                Log.d("error response", "true: ${response.message} ")
                emit(Result.Success(response))
            } else {
                Log.d("error response", "false: ${response.message} ")
                emit(Result.Error(response.message))
            }
        } catch (e: Exception) {
            Log.d("BarterinRepository", "error: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }

    }

    fun emailVerification(
        token: String
    ): LiveData<Result<EmailVerificationResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.emailVerification(
                "Bearer $token"
            )
            if (response.statusCode == 200) {
                Log.d("error response", "true: ${response.message} ")
                emit(Result.Success(response))
            } else {
                Log.d("error response", "false: ${response.message} ")
                emit(Result.Error(response.message))
            }
        } catch (e: Exception) {
            Log.d("BarterinRepository", "error: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }

    }

    fun sendVerification(
        token: String,
        verifyCode: String
    ): LiveData<Result<SendVerificationResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.sendVerification(
                "Bearer $token",
                verifyCode
            )
            if (response.statusCode == 200) {
                Log.d("error response", "true: ${response.message} ")
                emit(Result.Success(response))
            } else {
                Log.d("error response", "false: ${response.message} ")
                emit(Result.Error(response.message))
            }
        } catch (e: Exception) {
            Log.d("BarterinRepository", "error: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }

    }


    fun addAddress(
        token: String,
        label: String,
        receiver: String,
        phoneNumber: String,
        kotaKecamatan: String,
        fullAddress: String,
        postCode: String
    ): LiveData<Result<AddAddressResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.addAddress(
                "Bearer $token",
                label,
                receiver,
                phoneNumber,
                kotaKecamatan,
                fullAddress,
                postCode
            )
            if (response.statusCode == 200) {
                Log.d("error response", "true: ${response.message} ")
                emit(Result.Success(response))
            } else {
                Log.d("error response", "false: ${response.message} ")
                emit(Result.Error(response.message))
            }
        } catch (e: Exception) {
            Log.d("BarterinRepository", "error: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }

    }

    fun updateAddress(
        id: String,
        token: String,
        label: String,
        receiver: String,
        phoneNumber: String,
        kotaKecamatan: String,
        fullAddress: String,
        postCode: String
    ): LiveData<Result<AddAddressResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.updateAddress(
                id,
                "Bearer $token",
                label,
                receiver,
                phoneNumber,
                kotaKecamatan,
                fullAddress,
                postCode
            )
            if (response.statusCode == 200) {
                Log.d("error response", "true: ${response.message} ")
                emit(Result.Success(response))
            } else {
                Log.d("error response", "false: ${response.message} ")
                emit(Result.Error(response.message))
            }
        } catch (e: Exception) {
            Log.d("BarterinRepository", "error: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }

    }

    fun uploadImage(
        token: String,
        file: MultipartBody.Part
    ): LiveData<Result<UploadPhotoResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.uploadProfilePhoto(
                "Bearer $token",
                file
            )
            if (response.statusCode == 200) {
                Log.d("error response", "false: ${response.message} ")
                emit(Result.Success(response))
            } else {
                Log.d("error response", "true: ${response.message} ")
                emit(Result.Error(response.message))
            }
        } catch (e: Exception) {
            Log.d("StoryRepository", "error: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }

    }


    fun getDataUser(token: String): LiveData<Result<UserResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getDataUser(
                "Bearer $token"
            )

            if (response.statusCode == 200) {
                Log.d("error response", "true: Berhasil ")
                emit(Result.Success(response.data))
            } else {
                Log.d("error response", "false: Gagal")
                emit(Result.Error(response.data.fullname))
            }

        } catch (e: Exception) {
            Log.d("BarterinRepository", "error: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getAddressList(token: String): LiveData<Result<List<AddressResult>>> = liveData {
        emit(Result.Loading)

        try {

            val response = apiService.getAddressList("Bearer $token")


            if (response.statusCode == 200) {
                Log.d("error response", "true: Berhasil ")
                emit(Result.Success(response.data))
            } else {
                Log.d("error response", "false: Gagal")
            }
        } catch (e: Exception) {
            Log.d("BarterinRepository", "getAddressList: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getCategory(token: String): LiveData<Result<List<CategoriesResult>>> = liveData {
        emit(Result.Loading)

        try {
            val response = apiService.getCategoryList("Bearer $token")

            if (response.statusCode == 200) {
                Log.d("error response", "true: Berhasil ")
                emit(Result.Success(response.data))
            } else {
                Log.d("error response", "false: Gagal")
            }
        } catch (e: Exception) {
            Log.d("BarterinRepository", "getcategory: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }


    fun getType(id: String): LiveData<Result<List<DataTypes>>> = liveData {
        emit(Result.Loading)

        try {
            val response = apiService.getTypeList(id)

            if (response.statusCode == 200) {
                Log.d("error response", "true: Berhasil ")
                emit(Result.Success(response.data))
            } else {
                Log.d("error response", "false: Gagal")
            }
        } catch (e: Exception) {
            Log.d("BarterinRepository", "getcategory: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }


    fun deleteAddress(token: String, id: String): LiveData<Result<DeleteAddressResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.deleteAddress(
                    "Bearer $token",
                    id
                )

                if (response.statusCode == 200) {
                    Log.d("error response", "true: Berhasil ")
                    emit(Result.Success(response))
                } else {
                    Log.d("error response", "false: Gagal")
                    emit(Result.Error(response.message))
                }

            } catch (e: Exception) {
                Log.d("BarterinRepository", "error: ${e.message.toString()} ")
                emit(Result.Error(e.message.toString()))
            }
        }

    fun editProfile(
        token: String,
        fullname: String,
        phone: String,
        born: String,
        gender: String
    ): LiveData<Result<EditProfileResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.updateProfile(
                "Bearer $token",
                fullname,
                phone,
                born,
                gender
            )
            if (response.statusCode == 200) {
                Log.d("error response", "true: ${response.message} ")
                emit(Result.Success(response))
            } else {
                Log.d("error response", "false: ${response.message} ")
                emit(Result.Error(response.message))
            }
        } catch (e: Exception) {
            Log.d("BarterinRepository", "error: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }

    }

    fun uploadItem(
        token: String,
        type_id: RequestBody,
        address_id: RequestBody,
        name: RequestBody,
        description: RequestBody,
        used_time: RequestBody,
        date_unit: RequestBody,
        purchase_price: RequestBody,
        item_for: RequestBody,
        file: Array<MultipartBody.Part>
    ): LiveData<Result<UploadImageResult>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.uploadItem(
                "Bearer $token",
                type_id,
                address_id,
                name,
                description,
                used_time,
                date_unit,
                purchase_price,
                item_for,
                file
            )
            if (response.statusCode == 200) {
                Log.d("error response", "true: ${response.message} ")
                emit(Result.Success(response))
            } else {
                Log.d("error response", "false: ${response.message} ")
                emit(Result.Error(response.message))
            }
        } catch (e: Exception) {
            Log.d("StoryRepository", "error: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }


    fun getBarterItem(): LiveData<Result<List<DataItem>>> = liveData {
        emit(Result.Loading)

        try {

            val response = apiService.getAllBarterItems()


            if (response.statusCode == 200) {
                Log.d("error response", "true: Berhasil ")
                emit(Result.Success(response.data))
            } else {
                Log.d("error response", "false: Gagal")
            }
        } catch (e: Exception) {
            Log.d("BarterinRepository", "getAddressList: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun searchBarterItem(query: String): LiveData<Result<List<DataItem>>> = liveData {
        emit(Result.Loading)

        try {

            val response = apiService.searchBarterItem(query)


            if (response.statusCode == 200) {
                Log.d("error response", "true: Berhasil ")
                emit(Result.Success(response.data))
            } else {
                Log.d("error response", "false: Gagal")
            }
        } catch (e: Exception) {
            Log.d("BarterinRepository", "getAddressList: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }


    fun logout(token: String): LiveData<Result<LogoutResponse>> = liveData {
        emit(Result.Loading)
        try {

            val response = apiService.logoutUser("Bearer $token")

            if (response.statusCode == 200) {
                Log.d("error response", "true: ${response.message} ")
                emit(Result.Success(response))
            } else {
                Log.d("error response", "false: ${response.message} ")
                emit(Result.Error(response.message))
            }
        } catch (e: Exception) {
            Log.d("BarterinRepository", "error: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }

    }


    fun addToCart(
        token: String,
        itemsId: String,
    ): LiveData<Result<AddToCartResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.addToCart("Bearer $token", itemsId)

            if (response.statusCode == 200) {
                Log.d("error response", "true: ${response.message} ")
                emit(Result.Success(response))
            } else {
                Log.d("error response", "false: ${response.message} ")
                emit(Result.Error(response.message))
            }
        } catch (e: Exception) {
            Log.d("BarterinRepository", "error: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }

    }

    fun getChartItem(token: String): LiveData<Result<List<DataCartResult>>> = liveData {
        emit(Result.Loading)

        try {

            val response = apiService.getAllChart("Bearer $token")


            if (response.statusCode == 200) {
                Log.d("error response", "true: Berhasil ")
                emit(Result.Success(response.data))
            } else {
                Log.d("error response", "false: Gagal")
            }
        } catch (e: Exception) {
            Log.d("BarterinRepository", "getAddressList: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun deleteChart(token: String, id: String): LiveData<Result<DeleteAddressResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.deleteChart(
                "Bearer $token",
                id
            )

            if (response.statusCode == 200) {
                Log.d("error response", "true: Berhasil ")
                emit(Result.Success(response))
            } else {
                Log.d("error response", "false: Gagal")
                emit(Result.Error(response.message))
            }

        } catch (e: Exception) {
            Log.d("BarterinRepository", "error: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun offerBarter(
        token: String,
        id: String,
        withId: String
    ): LiveData<Result<DeleteAddressResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.barterOffer(
                "Bearer $token",
                id,
                withId
            )

            if (response.statusCode == 200) {
                Log.d("error response", "true: Berhasil ")
                emit(Result.Success(response))
            } else {
                Log.d("error response", "false: Gagal")
                emit(Result.Error(response.message))
            }

        } catch (e: Exception) {
            Log.d("BarterinRepository", "error: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getMyItem(token: String): LiveData<Result<List<GetMyItems>>> = liveData {
        emit(Result.Loading)

        try {

            val response = apiService.getMyItems("Bearer $token")


            if (response.statusCode == 200) {
                Log.d("error response", "true: Berhasil ")
                emit(Result.Success(response.data))
            } else {
                Log.d("error response", "false: Gagal")
            }
        } catch (e: Exception) {
            Log.d("BarterinRepository", "getAddressList: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }


    fun getOfferList(token: String): LiveData<Result<List<OfferData>>> = liveData {
        emit(Result.Loading)

        try {

            val response = apiService.getOfferBarter("Bearer $token")


            if (response.statusCode == 200) {
                Log.d("error response", "true: Berhasil ")
                emit(Result.Success(response.data))
            } else {
                Log.d("error response", "false: Gagal")
            }
        } catch (e: Exception) {
            Log.d("BarterinRepository", "getAddressList: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun acceptBarter(token: String, id: String): LiveData<Result<AcceptOfferResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.acceptOffer(
                "Bearer $token",
                id
            )

            if (response.statusCode == 200) {
                Log.d("error response", "true: Berhasil ")
                emit(Result.Success(response))
            } else {
                Log.d("error response", "false: Gagal")
                emit(Result.Error(response.message))
            }

        } catch (e: Exception) {
            Log.d("BarterinRepository", "error: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getBidderList(id: String, token: String): LiveData<Result<List<OfferData>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getBidderList(id, "Bearer $token")
            if (response.statusCode == 200) {
                Log.d("error response", "true: Berhasil ")
                emit(Result.Success(response.data))
            } else {
                Log.d("error response", "false: Gagal")
            }
        } catch (e: Exception) {
            Log.d("BarterinRepository", "getAddressList: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }


    fun deleteItem(token: String, id: String): LiveData<Result<DeleteAddressResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.deleteItem(
                "Bearer $token",
                id
            )

            if (response.statusCode == 200) {
                Log.d("error response", "true: Berhasil ")
                emit(Result.Success(response))
            } else {
                Log.d("error response", "false: Gagal")
                emit(Result.Error(response.message))
            }

        } catch (e: Exception) {
            Log.d("BarterinRepository", "error: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun updateItem(
        token: String,
        id: String,
        type_id: String,
        address_id: String,
        name: String,
        description: String,
        used_time: String,
        date_unit: String,
        purchase_price: String,
        item_for: String,
    ): LiveData<Result<UpdateAddressResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.updateItem(
                "Bearer $token",
                id,
                type_id,
                address_id,
                name,
                description,
                used_time,
                date_unit,
                purchase_price,
                item_for
            )

            if (response.statusCode == 200) {
                Log.d("error response", "true: Berhasil ")
                emit(Result.Success(response))
            } else {
                Log.d("error response", "false: Gagal")
                emit(Result.Error(response.message))
            }

        } catch (e: Exception) {
            Log.d("BarterinRepository", "error: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }


    companion object {
        @Volatile
        private var instance: BarterinRepository? = null
        fun getInstance(
            apiService: ApiService,
//            storyDao: BarterinDao
        ): BarterinRepository =
            instance ?: synchronized(this) {
                instance ?: BarterinRepository(apiService)
            }.also { instance = it }
    }

}
