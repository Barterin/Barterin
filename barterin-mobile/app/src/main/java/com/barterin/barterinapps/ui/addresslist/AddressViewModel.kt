package com.barterin.barterinapps.ui.addresslist

import androidx.lifecycle.ViewModel
import com.barterin.barterinapps.data.BarterinRepository

class AddressViewModel(private val barterinRepository: BarterinRepository) : ViewModel() {

    fun getDataAddress(token: String) = barterinRepository.getAddressList(token)

    fun deleteAddress(token: String, id: String) = barterinRepository.deleteAddress(token, id)

}