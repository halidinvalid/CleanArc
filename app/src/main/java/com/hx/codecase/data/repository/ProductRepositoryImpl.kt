package com.hx.codecase.data.repository

import com.hx.codecase.data.extension.apiCall
import com.hx.codecase.domain.datasource.RemoteDataSource
import com.hx.codecase.domain.model.ProductResponse
import com.hx.codecase.domain.repository.ProductRepository
import com.hx.codecase.presentation.entities.DataHolder

class ProductRepositoryImpl constructor(
    private val remoteDataSource: RemoteDataSource
) : ProductRepository {

    override suspend fun getProducts(
        term: String?, limit: String?, media: String?
    ): DataHolder<ProductResponse?> {
        return apiCall {
            remoteDataSource.getProducts(term, limit, media)
        }
    }
}
