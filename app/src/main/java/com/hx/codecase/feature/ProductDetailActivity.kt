package com.hx.codecase.feature

import androidx.fragment.app.Fragment
import com.hx.codecase.presentation.base.BaseActivity

class ProductDetailActivity : BaseActivity() {

    private lateinit var productImage: String
    private lateinit var productName: String
    private lateinit var productPrice: String
    private lateinit var productReleaseDate: String

    override fun provideInitialFragment(): Fragment {
        intent?.apply {
            productImage = getStringExtra(BUNDLE_PRODUCT_IMAGE).toString()
            productName = getStringExtra(BUNDLE_PRODUCT_NAME).toString()
            productPrice = getStringExtra(BUNDLE_PRODUCT_PRICE).toString()
            productReleaseDate = getStringExtra(BUNDLE_PRODUCT_RELEASE_DATE).toString()
        }
        return ProductDetailFragment.newInstance(
            productImage,
            productName,
            productPrice,
            productReleaseDate
        )
    }

    companion object {
        const val BUNDLE_PRODUCT_IMAGE = "product-image"
        const val BUNDLE_PRODUCT_NAME = "product-name"
        const val BUNDLE_PRODUCT_PRICE = "product-price"
        const val BUNDLE_PRODUCT_RELEASE_DATE = "product-release-date"
    }
}