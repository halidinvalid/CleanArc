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

        if (term.isNullOrEmpty()) {
            return DataHolder.Error("term cannot be null or empty")
        }
        if (limit.isNullOrEmpty()) {
            return DataHolder.Error("limit cannot be null or empty")
        }
        if (limit.toIntOrNull() == null) {
            return DataHolder.Error("limit is must be integer")
        }
        if (media.isNullOrEmpty()) {
            return DataHolder.Error("media cannot be null or empty")
        }
        return repositories.getProducts(term, limit, media)
    }
}