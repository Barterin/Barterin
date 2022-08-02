package com.barterin.barterinapps.ui.cart

import androidx.lifecycle.ViewModel
import com.barterin.barterinapps.data.BarterinRepository

class CartViewModel(private val barterinRepository: BarterinRepository) : ViewModel() {

    fun getCartItem(token: String) = barterinRepository.getChartItem(token)

    fun deleteChart(token: String, id: String) = barterinRepository.deleteChart(token, id)

    fun offerBarter(token: String, id:String, withId: String) = barterinRepository.offerBarter(token, id, withId)

}