package com.barterin.barterinapps.ui.register

import androidx.lifecycle.ViewModel
import com.barterin.barterinapps.data.BarterinRepository

class RegisterViewModel(private val barterinRepository: BarterinRepository) : ViewModel() {

    fun register(username: String, email: String, fullName: String, password: String) =
        barterinRepository.register(username, email, fullName, password)

}