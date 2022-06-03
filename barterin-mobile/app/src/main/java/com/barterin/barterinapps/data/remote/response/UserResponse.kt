package com.barterin.barterinapps.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("fullname")
    val fullname: String,

    @field:SerializedName("verified_email")
    val verified_email: String,

    @field:SerializedName("verified_account")
    val verified_account: String,

    @field:SerializedName("phone")
    val phone: String,

    @field:SerializedName("born")
    val born: String,

    @field:SerializedName("profile_picture")
    val profile_picture: String,

    @field:SerializedName("gender")
    val gender: String

)