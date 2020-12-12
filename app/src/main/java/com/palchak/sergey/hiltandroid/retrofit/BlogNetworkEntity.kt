package com.palchak.sergey.hiltandroid.retrofit

import com.google.gson.annotations.SerializedName

data class BlogNetworkEntity(

    @SerializedName("pk")
    var id: Int,
    var title: String,
    var body: String,
    var image: String,
    var category: String
)
