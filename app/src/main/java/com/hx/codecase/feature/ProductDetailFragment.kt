package com.hx.codecase.feature

import android.os.Bundle
import com.hx.codecase.R
import com.hx.codecase.databinding.FragmentProductDetailBinding
import com.hx.codecase.presentation.base.BaseFragment
import com.hx.codecase.presentation.extension.formatDate
import com.hx.codecase.presentation.extension.loadImage
import com.hx.codecase.presentation.navigation.UiNavigation

class ProductDetailFragment :
    BaseFragment<FragmentProductDetailBinding>(FragmentProductDetailBinding::inflate) {

    private var productImage: String? = null
    private var productName: String? = null
    private var productPrice: String? = null
    private var productReleaseDate: String? = null

    override val uiNavigation = UiNavigation.BACK

    override fun getLayoutRes(): Int = R.layout.fragment_product_detail

    override fun initView() {
        super.initView()
        arguments?.apply {
            productImage = getString(BUNDLE_PRODUCT_IMAGE)
            productName = getString(BUNDLE_PRODUCT_NAME)
            productPrice = getString(BUNDLE_PRODUCT_PRICE)
            productReleaseDate = getString(BUNDLE_PRODUCT_RELEASE_DATE)
        }

        binding.apply {
            productImage?.loadImage(context = context, imageView = imageViewProduct)
            textViewProductName.text = productName
            textViewProductPrice.text = productPrice
            textViewProductReleaseDate.text = productReleaseDate?.formatDate()
        }
        actionBar.title = productName
    }

    companion object {
        private const val BUNDLE_PRODUCT_IMAGE = "product-image"
        private const val BUNDLE_PRODUCT_NAME = "product-name"
        private const val BUNDLE_PRODUCT_PRICE = "product-price"
        private const val BUNDLE_PRODUCT_RELEASE_DATE = "product-release-date"

        fun newInstance(
            productImage: String?,
            productName: String?,
            productPrice: String?,
            productReleaseDate: String?
        ) = ProductDetailFragment().apply {
            arguments = Bundle().apply {
                putString(BUNDLE_PRODUCT_IMAGE, productImage)
                putString(BUNDLE_PRODUCT_NAME, productName)
                putString(BUNDLE_PRODUCT_PRICE, productPrice)
                putString(BUNDLE_PRODUCT_RELEASE_DATE, productReleaseDate)
            }
        }
    }
}