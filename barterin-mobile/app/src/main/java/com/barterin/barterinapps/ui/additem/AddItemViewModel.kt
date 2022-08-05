package com.barterin.barterinapps.ui.additem

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.barterin.barterinapps.data.BarterinRepository
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import okhttp3.MultipartBody
import okhttp3.RequestBody


class AddItemViewModel(private val barterinRepository: BarterinRepository) : ViewModel() {

    fun getCategoryList(token: String) = barterinRepository.getCategory(token)

    fun uploadItem(
        token: String,
        type_id: RequestBody,
        address_id: RequestBody,
        name: RequestBody,
        description: RequestBody,
        used_time: RequestBody,
        date_unit: RequestBody,
        purchase_price: RequestBody,
        item_for: RequestBody,
        file: Array<MultipartBody.Part>
    ) = barterinRepository.uploadItem(
        token,
        type_id,
        address_id,
        name,
        description,
        used_time,
        date_unit,
        purchase_price,
        item_for,
        file
    )

    fun getTypeList(id: String, token: String) = barterinRepository.getType(id, token)

}