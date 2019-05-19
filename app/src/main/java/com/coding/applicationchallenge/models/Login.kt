package com.coding.applicationchallenge.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Login(var userName: String, var password: String, var country: String) : Parcelable