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


class AddItemViewModel(private val barterinRepository: BarterinRepository) : ViewModel() {

    fun getCategoryList(token: String) = barterinRepository.getCategory(token)


}