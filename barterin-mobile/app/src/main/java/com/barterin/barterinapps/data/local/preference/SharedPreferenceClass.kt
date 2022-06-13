package com.barterin.barterinapps.data.local.preference

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceClass(context: Context) {

    private var userSharedPref: SharedPreferences =
        context.getSharedPreferences("data_user_local", Context.MODE_PRIVATE)

    fun saveToken(accessToken: String, tokenType: String, expiresIn: Int, isLogin: Boolean) {
        val edit = userSharedPref.edit()
        edit.putString("accessToken", accessToken)
        edit.putString("tokenType", tokenType)
        edit.putInt("expiresIn", expiresIn)
        edit.putBoolean("isLogin", isLogin)
        edit.apply()
    }

    fun saveDataUser(
        id: String,
        username: String,
        email: String,
        fullname: String,
        verified_email: String,
        verified_account: String,
        phone: String,
        born: String,
        profile_picture: String,
        gender: String,
        isLogin: Boolean
        ) {
        val edit = userSharedPref.edit()
        edit.putString("id", id)
        edit.putString("username", username)
        edit.putString("email", email)
        edit.putString("fullname", fullname)
        edit.putString("verifiedEmail", verified_email)
        edit.putString("verifiedAccount", verified_account)
        edit.putString("phone", phone)
        edit.putString("born", born)
        edit.putString("profile_picture", profile_picture)
        edit.putString("gender", gender)
        edit.putBoolean("isLogin", isLogin)
        edit.apply()
    }

    fun checkState(): Boolean {
        return userSharedPref.getBoolean("isLogin", false)
    }

    fun getToken(): String {
        return userSharedPref.getString("accessToken", null).toString()
    }

    fun getVerifiedEmail(): String {
        return userSharedPref.getString("verifiedEmail", null).toString()
    }

    fun getEmail(): String {
        return userSharedPref.getString("email", null).toString()
    }

    fun getName(): String {
        return userSharedPref.getString("fullname", null).toString()
    }

    fun getUsername(): String {
        return userSharedPref.getString("username", null).toString()
    }

    fun getProfilePicture(): String {
        return userSharedPref.getString("profile_picture", null).toString()
    }

    fun getPhoneNumber(): String {
        return userSharedPref.getString("phone", null).toString()
    }

    fun getBorn(): String {
        return userSharedPref.getString("born", null).toString()
    }

    fun getGender(): String {
        return userSharedPref.getString("gender", null).toString()
    }


    fun verifyEmail(isVerified: String) {
        val edit = userSharedPref.edit()
        edit.putString("verifiedEmail", isVerified)
        edit.apply()
    }

}