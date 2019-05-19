package com.coding.applicationchallenge.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(val street: String, var suite: String, var city: String, var zipcode: String, var geo : Geo) : Parcelable

@Parcelize
data class Geo(val lat: String, var lng: String) : Parcelable