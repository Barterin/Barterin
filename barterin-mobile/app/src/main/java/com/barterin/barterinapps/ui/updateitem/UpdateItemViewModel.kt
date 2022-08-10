package com.barterin.barterinapps.ui.updateitem

import androidx.lifecycle.ViewModel
import com.barterin.barterinapps.data.BarterinRepository

class UpdateItemViewModel(private val barterinRepository: BarterinRepository) : ViewModel() {

    fun updateItem(
        token: String,
        id: String,
        type_id: String,
        address_id: String,
        name: String,
        description: String,
        used_time: String,
        date_unit: String,
        purchase_price: String,
        item_for: String,
    ) = barterinRepository.updateItem(
        token,
        id,
        type_id,
        address_id,
        name,
        description,
        used_time,
        date_unit,
        purchase_price,
        item_for
    )

}