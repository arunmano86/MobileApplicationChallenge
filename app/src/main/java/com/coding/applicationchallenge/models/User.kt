package com.coding.applicationchallenge.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(val id: Int, var name: String, var username: String, var email: String, var address: Address, var phone: String, var website: String, var company : Company) : Parcelable{
    fun User() {

    }
}

@Parcelize
data class Company( var name: String, var catchPhrase: String, var bs: String) : Parcelable