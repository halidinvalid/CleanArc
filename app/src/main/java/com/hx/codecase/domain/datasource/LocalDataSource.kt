package com.hx.codecase.domain.datasource

import com.hx.codecase.domain.model.ProductResponse


interface LocalDataSource {

   suspend fun getCacheProducts(term: String?, limit: String?, media: String?): ProductResponse?

}