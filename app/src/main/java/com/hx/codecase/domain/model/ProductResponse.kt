package com.hx.codecase.domain.model

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("results") var results: List<ProductItem?>
)