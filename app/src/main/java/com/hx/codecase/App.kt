package com.hx.codecase

import android.app.Application
import com.hx.codecase.data.di.dataModule
import com.hx.codecase.domain.di.domainModule
import com.hx.codecase.presentation.di.viewModelModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        loadKoin()
    }

    private fun loadKoin() {
        val appModules = dataModule + domainModule + viewModelModule

        startKoin(
            this,
            appModules
        )
    }
}