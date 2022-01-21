package com.hx.codecase.domain.datasource

import com.hx.codecase.domain.model.ProductResponse


interface LocalDataSource {

    fun getCacheProducts(term: String?, limit: String?, media: String?): ProductResponse?

}