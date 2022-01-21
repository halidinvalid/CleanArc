package com.hx.codecase.domain.model

import com.google.gson.annotations.SerializedName

data class ProductItem(
    @SerializedName("collectionId") var collectionId: String?,
    @SerializedName("artworkUrl100") var artworkUrl100: String?,
    @SerializedName("collectionPrice") var collectionPrice: String?,
    @SerializedName("collectionName") var collectionName: String?,
    @SerializedName("releaseDate") var releaseDate: String?
)

