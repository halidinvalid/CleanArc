package com.hx.codecase.domain.datasource

import com.hx.codecase.domain.model.ProductResponse
import retrofit2.Response

interface RemoteDataSource {

    suspend fun getProducts(
        term: String?,
        limit: String?,
        media: String?
    ): Response<ProductResponse?>

}