package com.hx.codecase.data.services

import com.hx.codecase.domain.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    @GET("/search")
    suspend fun getResults(
        @Query("term") term: String?,
        @Query("limit") limit: String?,
        @Query("media") media: String?
    ): ProductResponse?

}