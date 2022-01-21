package com.hx.codecase.domain.interactor

import com.hx.codecase.domain.model.ProductResponse
import com.hx.codecase.presentation.entities.DataHolder

interface ProductInteractor {

    suspend fun getProducts(
        term: String?,
        limit: String?,
        media: String?
    ): DataHolder<ProductResponse?>

}