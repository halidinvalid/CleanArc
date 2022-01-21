package com.hx.codecase.feature.di

import com.hx.codecase.feature.ProductViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val productViewModelModules = module {
    viewModel {
        ProductViewModel(
            get()
        )
    }
}