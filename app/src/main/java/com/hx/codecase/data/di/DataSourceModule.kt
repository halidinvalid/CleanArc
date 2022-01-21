package com.hx.codecase.data.di

import com.hx.codecase.data.datasource.RemoteDataSourceImp
import com.hx.codecase.domain.datasource.RemoteDataSource
import org.koin.dsl.module.module

val dataSourceModule = module {
    single<RemoteDataSource> { RemoteDataSourceImp(get()) }
}