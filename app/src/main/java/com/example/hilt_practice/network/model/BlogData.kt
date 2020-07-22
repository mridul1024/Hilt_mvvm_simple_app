package com.example.hilt_practice.network.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BlogData(
    @SerializedName("pk")
    @Expose
    val primaryKey : String,

    @SerializedName("title")
    @Expose
    val title : String,

    @SerializedName("body")
    @Expose
    val body : String,

    @SerializedName("image")
    @Expose
    val image : String,

    @SerializedName("category")
    @Expose
    val category : String

    )