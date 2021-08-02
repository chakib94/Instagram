package com.taki.instagram.data.models


import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.text.DateFormat
import java.text.SimpleDateFormat

@Entity(tableName = "photo")
@Parcelize
data class Photo(
    @PrimaryKey
    @SerializedName("id")
    val id: String,

    @SerializedName("created_at")
    val createdAt: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("promoted_at")
    val promotedAt: String,

    @SerializedName("width")
    val width: Int,

    @SerializedName("height")
    val height: Int,

    @SerializedName("color")
    val color: String,

    @SerializedName("blur_hash")
    val blurHash: String,

/*    @SerializedName("description")
    val description: String,*/

    @SerializedName("alt_description")
    val altDescription: String,

    @SerializedName("likes")
    val likes: Int,

    @SerializedName("liked_by_user")
    val likedByUser: Boolean,

    @SerializedName("user")
    @Embedded
    val user: User,

    @SerializedName("views")
    val views: Int,

    @SerializedName("downloads")
    val downloads: Int,

    @SerializedName("urls")
    @Embedded
    val urls: Urls,

    ) : Parcelable {
    val createdDateFormatted: String
        get() = createdAt.substring(0,10)

    val updatedDateFormatted: String
        get() = updatedAt.substring(0,10)
}