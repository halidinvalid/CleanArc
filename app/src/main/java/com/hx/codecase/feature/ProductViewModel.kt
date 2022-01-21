package com.hx.codecase.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hx.codecase.domain.interactor.ProductInteractor
import com.hx.codecase.domain.model.ProductResponse
import com.hx.codecase.presentation.base.BaseViewModel
import com.hx.codecase.presentation.entities.DataHolder
import com.hx.codecase.presentation.extension.launchViewModelScope
import com.hx.codecase.presentation.extension.loadingData
import com.hx.codecase.presentation.extension.responseData

class ProductViewModel(
    private val productInteractor: ProductInteractor
) : BaseViewModel() {

    private val productsMutableLiveData = MutableLiveData<DataHolder<ProductResponse?>>()
    val productsLiveData: LiveData<DataHolder<ProductResponse?>>
        get() = productsMutableLiveData

    fun getProducts(term: String?, limit: String?, media: String?) = launchViewModelScope {
        productsMutableLiveData
            .loadingData()
            .responseData(productInteractor.getProducts(term, limit, media))
    }
}