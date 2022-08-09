package com.barterin.barterinapps.ui.searchresult

import com.barterin.barterinapps.data.BarterinRepository

class SearchResultViewModel(private val barterinRepository: BarterinRepository) {

    fun getSearchItem(query: String) = barterinRepository.searchBarterItem(query)

}