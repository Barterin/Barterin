package com.barterin.barterinapps.ui.updateaddress

import androidx.lifecycle.ViewModel
import com.barterin.barterinapps.data.BarterinRepository

class UpdateAddressViewModel(private val barterinRepository: BarterinRepository) : ViewModel() {

    fun updateAddress(
        id: String,
        token: String,
        label: String,
        receiver: String,
        phoneNumber: String,
        kotaKecamatan: String,
        fullAddress: String,
        postCode: String
    ) = barterinRepository.updateAddress(
        id,
        token,
        label,
        receiver,
        phoneNumber,
        kotaKecamatan,
        fullAddress,
        postCode
    )

}