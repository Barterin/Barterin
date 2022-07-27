package com.barterin.barterinapps.ui.detailitem

import androidx.lifecycle.ViewModel
import com.barterin.barterinapps.data.BarterinRepository

class DetailItemViewModel(private val barterinRepository: BarterinRepository) : ViewModel() {

    fun addToCart(token: String, itemsId: String) = barterinRepository.addToCart(token, itemsId)

}