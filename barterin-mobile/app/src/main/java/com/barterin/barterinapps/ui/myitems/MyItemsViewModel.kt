package com.barterin.barterinapps.ui.myitems

import androidx.lifecycle.ViewModel
import com.barterin.barterinapps.data.BarterinRepository

class MyItemsViewModel(private val barterinRepository: BarterinRepository) : ViewModel() {

    fun getMyItems(token: String) = barterinRepository.getMyItem(token)

    fun offerBarter(token: String, id:String, withId: String) = barterinRepository.offerBarter(token, id, withId)


}