package com.barterin.barterinapps.ui.updateprofile

import androidx.lifecycle.ViewModel
import com.barterin.barterinapps.data.BarterinRepository
import okhttp3.MultipartBody
import okhttp3.RequestBody

class UpdateProfileViewModel(private val barterinRepository: BarterinRepository) : ViewModel() {

    fun uploadProfilePhoto(token: String, file: MultipartBody.Part) =
        barterinRepository.uploadImage(token, file)

    fun updateDataProfile(
        token: String,
        fullname: String,
        phone: String,
        born: String,
        gender: String
    ) = barterinRepository.editProfile(token, fullname, phone, born, gender)

    fun getDataUser(token: String) = barterinRepository.getDataUser(token)

}