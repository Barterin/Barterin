package com.barterin.barterinapps.ui.bottomnavigation.ui.offer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barterin.barterinapps.data.BarterinRepository

class OfferViewModel(private val barterinRepository: BarterinRepository) : ViewModel() {

    fun getOfferItem(token: String) = barterinRepository.getOfferList(token)

}