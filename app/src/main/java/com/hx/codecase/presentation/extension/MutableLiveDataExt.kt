package com.hx.codecase.presentation.extension

import androidx.lifecycle.MutableLiveData
import com.hx.codecase.presentation.entities.DataHolder
import com.hx.codecase.presentation.entities.Status


fun <T : Any?> MutableLiveData<DataHolder<T?>>.loadingData() = apply {
    postValue(DataHolder(Status.LOADING))
}

fun <T : Any?> MutableLiveData<DataHolder<T?>>.responseData(responseMethod: DataHolder<T?>) =
    apply {
        postValue(responseMethod)
    }