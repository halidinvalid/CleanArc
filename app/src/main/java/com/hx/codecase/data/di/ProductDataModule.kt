package com.hx.codecase.data.di

import com.hx.codecase.data.repository.ProductRepositoryImpl
import com.hx.codecase.domain.datasource.RemoteDataSource
import com.hx.codecase.domain.repository.ProductRepository
import org.koin.dsl.module.module

val productDataModules = module {

    fun provideProductsRepository(
        remoteDataSource: RemoteDataSource,
    ): ProductRepository =
        ProductRepositoryImpl(
            remoteDataSource = remoteDataSource
        )
    single {
        provideProductsRepository(
            remoteDataSource = get()
        )
    }
}