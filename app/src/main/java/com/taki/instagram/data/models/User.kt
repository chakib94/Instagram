package com.taki.instagram.data.models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Embedded
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @NonNull
    @SerializedName("updated_at")
    val updatedAt: String? = null,

    @SerializedName("username")
    val username: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("first_name")
    val firstName: String? = null,

    @SerializedName("last_name")
    val lastName: String? = null,

    @SerializedName("profile_image")
    @Embedded
    val profileImage: ProfileImage? = null,

) : Parcelable