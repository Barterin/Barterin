package com.barterin.barterinapps.ui.bottomnavigation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.barterin.barterinapps.data.BarterinRepository

class HomeViewModel(private val barterinRepository: BarterinRepository) : ViewModel() {

    fun getCategoryList(token: String) = barterinRepository.getCategory(token)

    fun getAllBarterItems() = barterinRepository.getBarterItem()

}