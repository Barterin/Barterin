package com.barterin.barterinapps.ui.login

import androidx.lifecycle.ViewModel
import com.barterin.barterinapps.data.BarterinRepository

class LoginViewModel(private val barterinRepository: BarterinRepository) : ViewModel() {

    fun userLogin(username: String, password: String) = barterinRepository.login(username, password)

    fun getDataUser(token: String) = barterinRepository.getDataUser(token)

}