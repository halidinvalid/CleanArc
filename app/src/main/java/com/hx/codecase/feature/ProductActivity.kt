package com.hx.codecase.feature

import com.hx.codecase.presentation.base.BaseActivity

class ProductActivity : BaseActivity() {
    override fun provideInitialFragment() = ProductFragment.newInstance()
}