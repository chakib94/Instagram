package com.taki.instagram.data.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileImage(

    @SerializedName("medium")
    val medium: String? = null,

    @SerializedName("large")
    val large: String? = null,

    var isSelected: Boolean ? = false

) : Parcelable