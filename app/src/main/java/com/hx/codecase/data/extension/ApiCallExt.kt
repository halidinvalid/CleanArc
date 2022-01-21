package com.hx.codecase.data.extension

import com.hx.codecase.presentation.entities.DataHolder
import com.hx.codecase.presentation.entities.Error
import com.hx.codecase.presentation.entities.Status

suspend fun <T> apiCall(
    apiCallResponse: (suspend () -> T?)
): DataHolder<T> {
    return try {
        val response = apiCallResponse.invoke()
        if (response == null) {
            DataHolder(
                responseType = Status.ERROR,
                error = Error("Unknown Error!")
            )
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