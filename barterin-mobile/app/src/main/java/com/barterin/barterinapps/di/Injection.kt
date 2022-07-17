package com.barterin.barterinapps.di

import android.content.Context
import com.barterin.barterinapps.data.BarterinRepository
import com.barterin.barterinapps.data.remote.retrofit.ApiConfig

object Injection {

    fun provideRepository(context: Context): BarterinRepository {
        val apiService = ApiConfig.getApiService()
//        val database = BarterinDatabase.getInstance(context)
//        val dao = database.barterinDao()
        return BarterinRepository.getInstance(apiService)
    }

}