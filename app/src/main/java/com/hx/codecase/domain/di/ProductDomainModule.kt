package com.hx.codecase.domain.di

import com.hx.codecase.domain.interactor.ProductInteractor
import com.hx.codecase.domain.interactor.ProductInteractorImp
import org.koin.dsl.module.module

val productDomainModules = module {
    single<ProductInteractor> { ProductInteractorImp(get()) }

}
