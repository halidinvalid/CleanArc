package com.hx.codecase.data.datasource

import com.hx.codecase.data.services.ProductService
import com.hx.codecase.domain.datasource.RemoteDataSource

class RemoteDataSourceImp(private val productServices: ProductService) : RemoteDataSource {

    override suspend fun getProducts(
        term: String?,
        limit: String?,
        media: String?
    ) = productServices.getResults(term, limit, media)

}