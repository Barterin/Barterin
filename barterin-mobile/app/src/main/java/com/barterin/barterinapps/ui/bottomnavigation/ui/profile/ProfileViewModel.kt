package com.barterin.barterinapps.ui.bottomnavigation.ui.profile

import androidx.lifecycle.ViewModel
import com.barterin.barterinapps.data.BarterinRepository

class ProfileViewModel(private val barterinRepository: BarterinRepository) : ViewModel() {

    fun userLogout(token: String) = barterinRepository.logout(token)

}