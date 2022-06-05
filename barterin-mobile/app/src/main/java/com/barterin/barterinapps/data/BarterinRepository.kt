package com.barterin.barterinapps.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.barterin.barterinapps.data.local.entity.AddressEntity
import com.barterin.barterinapps.data.local.room.BarterinDao
import com.barterin.barterinapps.data.remote.response.*
import com.barterin.barterinapps.data.remote.retrofit.ApiService
import com.barterin.barterinapps.utils.AppExecutors
import okhttp3.MultipartBody
import okhttp3.RequestBody


class BarterinRepository private constructor(
    private val apiService: ApiService,
    private val barterinDao: BarterinDao
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
            val address = response.data

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

    fun deleteAddress(token: String, id: String): LiveData<Result<DeleteAddressResponse>> = liveData {
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

    companion object {
        @Volatile
        private var instance: BarterinRepository? = null
        fun getInstance(
            apiService: ApiService,
            storyDao: BarterinDao
        ): BarterinRepository =
            instance ?: synchronized(this) {
                instance ?: BarterinRepository(apiService, storyDao)
            }.also { instance = it }
    }

}
