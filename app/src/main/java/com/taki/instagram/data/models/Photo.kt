package com.taki.instagram.data.models


import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    @SerializedName("id")
    val id: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("alt_description")
    val altDescription: String,
    @SerializedName("user")
    val user: User? = null,
)