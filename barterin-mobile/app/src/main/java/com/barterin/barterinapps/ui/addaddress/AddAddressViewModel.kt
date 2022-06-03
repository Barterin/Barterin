package com.barterin.barterinapps.ui.addaddress

import androidx.lifecycle.ViewModel
import com.barterin.barterinapps.data.BarterinRepository

class AddAddressViewModel(private val barterinRepository: BarterinRepository) : ViewModel()  {

    fun addAddress(
        token: String,
        label: String,
        receiver: String,
        phoneNumber: String,
        kotaKecamatan: String,
        fullAddress: String,
        postCode: String
    ) = barterinRepository.addAddress(
        token,
        label,
        receiver,
        phoneNumber,
        kotaKecamatan,
        fullAddress,
        postCode
    )

}