package com.hx.codecase.data.di

import com.hx.codecase.data.api.createNetworkClient
import com.hx.codecase.data.services.ProductService
import org.koin.dsl.module.module
import retrofit2.Retrofit

val apiModule = module {
    single(name = RETROFIT_INSTANCE) {
        createNetworkClient(BASE_URL)
    }
    single(name = API) {
        (get(RETROFIT_INSTANCE) as Retrofit).create(ProductService::class.java)
    }
}

private const val BASE_URL = "https://itunes.apple.com"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"