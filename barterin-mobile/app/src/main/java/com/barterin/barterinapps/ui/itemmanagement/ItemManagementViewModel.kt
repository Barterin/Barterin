package com.barterin.barterinapps.ui.itemmanagement

import androidx.lifecycle.ViewModel
import com.barterin.barterinapps.data.BarterinRepository

class ItemManagementViewModel(private val barterinRepository: BarterinRepository) : ViewModel() {

    fun getMyItems(token: String) = barterinRepository.getMyItem(token)

    fun deleteItem(token: String, id: String) = barterinRepository.deleteItem(token, id)

}