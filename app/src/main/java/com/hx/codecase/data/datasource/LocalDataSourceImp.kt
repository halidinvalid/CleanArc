package com.hx.codecase.data.datasource

import com.hx.codecase.domain.datasource.LocalDataSource
import com.hx.codecase.domain.model.ProductResponse

class LocalDataSourceImp() : LocalDataSource {

    override suspend fun getCacheProducts(
        term: String?,
        limit: String?,
        media: String?
    ): ProductResponse? {
        //Not yet implemented
        return null
    }

}