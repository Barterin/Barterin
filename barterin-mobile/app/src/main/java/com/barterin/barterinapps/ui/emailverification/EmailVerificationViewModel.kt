package com.barterin.barterinapps.ui.emailverification

import androidx.lifecycle.ViewModel
import com.barterin.barterinapps.data.BarterinRepository

class EmailVerificationViewModel(private val barterinRepository: BarterinRepository) : ViewModel() {

    fun emailVerification(token: String) = barterinRepository.emailVerification(token)

    fun sendVerification(token: String, verifyCode: String) = barterinRepository.sendVerification(token, verifyCode)

}