package com.hx.codecase.domain.interactor

import com.hx.codecase.domain.model.ProductResponse
import com.hx.codecase.domain.repository.ProductRepository
import com.hx.codecase.presentation.entities.DataHolder

class ProductInteractorImp(private var repositories: ProductRepository) : ProductInteractor {

    override suspend fun getProducts(
        term: String?,
        limit: String?,
        media: String?
    ): DataHolder<ProductResponse?> {
        return repositories.getProducts(term, limit, media)
    }
}