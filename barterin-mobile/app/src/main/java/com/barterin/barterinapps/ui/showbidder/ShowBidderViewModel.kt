package com.barterin.barterinapps.ui.showbidder

import androidx.lifecycle.ViewModel
import com.barterin.barterinapps.data.BarterinRepository

class ShowBidderViewModel(private val barterinRepository: BarterinRepository) : ViewModel() {

    fun showBidder(id: String, token: String) = barterinRepository.getBidderList(id, token)

    fun acceptOffer(token: String, offerId: String) = barterinRepository.acceptBarter(token, offerId)

}