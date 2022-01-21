package com.hx.codecase.domain.datasource

import com.hx.codecase.domain.model.ProductResponse

interface RemoteDataSource {

    suspend fun getProducts(term: String?, limit: String?, media: String?): ProductResponse?

}