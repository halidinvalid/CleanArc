package com.hx.codecase.data.extension

import com.hx.codecase.presentation.entities.DataHolder
import com.hx.codecase.presentation.entities.Error
import com.hx.codecase.presentation.entities.Status
import retrofit2.Response

suspend fun <T> apiCall(
    apiCallResponse: (suspend () -> T?)
): DataHolder<T> {
    return try {
        val response = apiCallResponse.invoke()
        if (response is Response<*>) {
            if (response.code() > 400) {
                DataHolder(
                    responseType = Status.ERROR,
                    error = Error(response.message())
                )
            } else {
                DataHolder(
                    responseType = Status.SUCCESSFUL,
                    data = response
                )
            }
        } else {
            DataHolder(
                responseType = Status.SUCCESSFUL,
                data = response
            )
        }
    } catch (t: Throwable) {
        DataHolder(
            responseType = Status.ERROR,
            error = Error(t.message)
        )
    }
}