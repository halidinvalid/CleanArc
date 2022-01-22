package com.hx.codecase.data.extension

import com.hx.codecase.presentation.entities.DataHolder
import com.hx.codecase.presentation.entities.Error
import com.hx.codecase.presentation.entities.Status
import retrofit2.HttpException
import java.io.IOException
import java.net.HttpURLConnection

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
    } catch (throwable: Throwable) {
        val errorMessage = when (throwable) {
            is IOException -> "Unable to resolve host"
            is HttpException -> {
                when (throwable.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> "Http 404 (Not Found)"
                    HttpURLConnection.HTTP_FORBIDDEN -> "Http 403 (Forbidden)"
                    HttpURLConnection.HTTP_UNAVAILABLE -> "HTTP 503 (Service Unavailable)"
                    else -> "Unknown Error"
                }
            }
            else -> throwable.message
        }
        DataHolder(
            responseType = Status.ERROR,
            error = Error(
                errorMessage
            )
        )
    }
}