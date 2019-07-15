package com.tpm.retrobic.model

import android.content.Context
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

data class Post(
    @SerializedName("id")
    val id : String,
    @SerializedName("userId")
    val userId : String,
    @SerializedName("title")
    val title : String,
    @SerializedName("body")
    val body : String
)