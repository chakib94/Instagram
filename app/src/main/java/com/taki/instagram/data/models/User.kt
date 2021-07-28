package com.taki.instagram.data.models


import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.taki.instagram.data.models.ProfileImage
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user")
data class User(
    @SerializedName("id")
    @PrimaryKey val id: String,

    @SerializedName("updated_at")
    val updatedAt: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("twitter_username")
    val twitterUsername: String,

    @SerializedName("portfolio_url")
    val portfolioUrl: String,

    @SerializedName("bio")
    val bio: String,

    @SerializedName("location")
    val location: String,

    @SerializedName("profile_image")
    @Embedded
    val profileImage: ProfileImage? = null,
    @SerializedName("instagram_username")
    val instagramUsername: String,

    @SerializedName("total_collections")
    val totalCollections: Int,

    @SerializedName("total_likes")
    val totalLikes: Int,

    @SerializedName("total_photos")
    val totalPhotos: Int,

    @SerializedName("accepted_tos")
    val acceptedTos: Boolean,

    @SerializedName("for_hire")
    val forHire: Boolean,

    ) : Parcelable