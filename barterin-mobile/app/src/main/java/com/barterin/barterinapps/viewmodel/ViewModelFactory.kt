package com.barterin.barterinapps.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.barterin.barterinapps.data.BarterinRepository
import com.barterin.barterinapps.di.Injection
import com.barterin.barterinapps.ui.addaddress.AddAddressViewModel
import com.barterin.barterinapps.ui.additem.AddItemViewModel
import com.barterin.barterinapps.ui.addresslist.AddressViewModel
import com.barterin.barterinapps.ui.bottomnavigation.ui.chat.ChatViewModel
import com.barterin.barterinapps.ui.bottomnavigation.ui.home.HomeViewModel
import com.barterin.barterinapps.ui.bottomnavigation.ui.offer.OfferViewModel
import com.barterin.barterinapps.ui.bottomnavigation.ui.profile.ProfileViewModel
import com.barterin.barterinapps.ui.cart.CartViewModel
import com.barterin.barterinapps.ui.detailitem.DetailItemViewModel
import com.barterin.barterinapps.ui.emailverification.EmailVerificationActivity
import com.barterin.barterinapps.ui.emailverification.EmailVerificationViewModel
import com.barterin.barterinapps.ui.itemmanagement.ItemManagementViewModel
import com.barterin.barterinapps.ui.login.LoginViewModel
import com.barterin.barterinapps.ui.myitems.MyItemsViewModel
import com.barterin.barterinapps.ui.register.RegisterViewModel
import com.barterin.barterinapps.ui.searchresult.SearchResultActivity
import com.barterin.barterinapps.ui.searchresult.SearchResultViewModel
import com.barterin.barterinapps.ui.showbidder.ShowBidderViewModel
import com.barterin.barterinapps.ui.updateaddress.UpdateAddressViewModel
import com.barterin.barterinapps.ui.updateitem.UpdateItemViewModel
import com.barterin.barterinapps.ui.updateprofile.UpdateProfileViewModel

class ViewModelFactory private constructor(private val barterinRepository: BarterinRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(barterinRepository) as T
        }
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(barterinRepository) as T
        }
        if (modelClass.isAssignableFrom(EmailVerificationViewModel::class.java)) {
            return EmailVerificationViewModel(barterinRepository) as T
        }
        if (modelClass.isAssignableFrom(AddAddressViewModel::class.java)) {
            return AddAddressViewModel(barterinRepository) as T
        }
        if (modelClass.isAssignableFrom(AddressViewModel::class.java)) {
            return AddressViewModel(barterinRepository) as T
        }
        if (modelClass.isAssignableFrom(UpdateAddressViewModel::class.java)) {
            return UpdateAddressViewModel(barterinRepository) as T
        }
        if (modelClass.isAssignableFrom(UpdateProfileViewModel::class.java)) {
            return UpdateProfileViewModel(barterinRepository) as T
        }
        if (modelClass.isAssignableFrom(AddItemViewModel::class.java)) {
            return AddItemViewModel(barterinRepository) as T
        }
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(barterinRepository) as T
        }
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(barterinRepository) as T
        }
        if (modelClass.isAssignableFrom(DetailItemViewModel::class.java)) {
            return DetailItemViewModel(barterinRepository) as T
        }
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(barterinRepository) as T
        }
        if (modelClass.isAssignableFrom(MyItemsViewModel::class.java)) {
            return MyItemsViewModel(barterinRepository) as T
        }
        if (modelClass.isAssignableFrom(OfferViewModel::class.java)) {
            return OfferViewModel(barterinRepository) as T
        }
        if (modelClass.isAssignableFrom(ShowBidderViewModel::class.java)) {
            return ShowBidderViewModel(barterinRepository) as T
        }
        if (modelClass.isAssignableFrom(SearchResultViewModel::class.java)) {
            return SearchResultViewModel(barterinRepository) as T
        }
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            return ChatViewModel(barterinRepository) as T
        }
        if (modelClass.isAssignableFrom(ItemManagementViewModel::class.java)) {
            return ItemManagementViewModel(barterinRepository) as T
        }
        if (modelClass.isAssignableFrom(UpdateItemViewModel::class.java)) {
            return UpdateItemViewModel(barterinRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}