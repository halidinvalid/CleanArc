package com.hx.codecase.presentation.entities

sealed class DataHolder<T>(
    var data: T? = null,
    var error: String? = null
) {
    data class Success<T>(val successData: T) : DataHolder<T>(data = successData)
    class Loading<T> : DataHolder<T>()
    data class Error<T>(val message: String? = null) : DataHolder<T>(error = message)
}