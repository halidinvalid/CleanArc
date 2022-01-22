package com.hx.codecase.domain.interactor

import com.hx.codecase.domain.model.ProductResponse
import com.hx.codecase.domain.repository.ProductRepository
import com.hx.codecase.presentation.entities.DataHolder
import com.hx.codecase.presentation.entities.Error
import com.hx.codecase.presentation.entities.Status

class ProductInteractorImp(private var repositories: ProductRepository) : ProductInteractor {

    override suspend fun getProducts(
        term: String?,
        limit: String?,
        media: String?
    ): DataHolder<ProductResponse?> {

        if (term.isNullOrEmpty()) {
            return (DataHolder(
                responseType = Status.ERROR,
                error = Error("Term cannot be null or empty")
            ))
        }
        if (limit.isNullOrEmpty()) {
            return (DataHolder(
                responseType = Status.ERROR,
                error = Error("Limit cannot be null or empty")
            ))
        }
        if (limit.toIntOrNull() == null) {
            return (DataHolder(
                responseType = Status.ERROR,
                error = Error("Limit is must be integer")
            ))
        }
        if (media.isNullOrEmpty()) {
            return (DataHolder(
                responseType = Status.ERROR,
                error = Error("Media cannot be null or empty")
            ))
        }
        return repositories.getProducts(term, limit, media)
    }
}