package com.hx.codecase.feature

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.hx.codecase.R
import com.hx.codecase.databinding.FragmentProductBinding
import com.hx.codecase.domain.model.ProductItem
import com.hx.codecase.presentation.base.BaseFragment
import com.hx.codecase.presentation.entities.MediaType
import com.hx.codecase.presentation.extension.launchActivity
import com.hx.codecase.presentation.extension.observeResponse
import com.hx.codecase.presentation.extension.setup
import com.hx.codecase.presentation.navigation.UiNavigation
import org.koin.android.viewmodel.ext.android.viewModel

class ProductFragment : BaseFragment<FragmentProductBinding>(FragmentProductBinding::inflate) {

    private val productViewModel: ProductViewModel by viewModel()
    private var selectedMediaType: MediaType = MediaType.MOVIE
    override val uiNavigation = UiNavigation.ROOT

    private val productAdapter by lazy {
        ProductAdapter(onItemClickListener = {
            context?.launchActivity<ProductDetailActivity> {
                putExtra(ProductDetailActivity.BUNDLE_PRODUCT_IMAGE, it?.artworkUrl100)
                putExtra(ProductDetailActivity.BUNDLE_PRODUCT_NAME, it?.collectionName)
                putExtra(ProductDetailActivity.BUNDLE_PRODUCT_PRICE, it?.collectionPrice)
                putExtra(ProductDetailActivity.BUNDLE_PRODUCT_RELEASE_DATE, it?.releaseDate)
            }
        })
    }

    override fun getLayoutRes() = R.layout.fragment_product

    override fun initView() {
        super.initView()
        binding.apply {
            recyclerViewProducts.apply {
                setup(
                    layoutManager = GridLayoutManager(context, GRID_SPAN_COUNT),
                    adapter = productAdapter
                )
            }
            buttonMovies.setOnClickListener {
                filterProduct(MediaType.MOVIE)
            }
            buttonMusic.setOnClickListener {
                filterProduct(MediaType.MUSIC)
            }
            buttonApps.setOnClickListener {
                filterProduct(MediaType.APP)
            }
            buttonBooks.setOnClickListener {
                filterProduct(MediaType.BOOKS)
            }
        }
    }

    override fun observeData() {
        super.observeData()
        productViewModel.productsLiveData.observeResponse(
            owner = viewLifecycleOwner,
            progressView = binding.progressBar,
            success = {
                it?.results?.let { productList ->
                    binding.apply {
                        if (productList.isNotEmpty()) {
                            recyclerViewProducts.visibility = View.VISIBLE
                            textViewEmptyList.visibility = View.GONE
                            productAdapter.updateList(productList as MutableList<ProductItem>)
                        } else {
                            recyclerViewProducts.visibility = View.GONE
                            textViewEmptyList.visibility = View.VISIBLE
                        }
                    }
                }
            },
            fail = {
                Toast.makeText(context, it?.message, Toast.LENGTH_LONG).show()
            }
        )
    }

    private fun filterProduct(mediaType: MediaType) {
        binding.apply {
            val filterText = editTextSearch.text.toString()
            if (filterText.length < 2) {
                Toast.makeText(context, "En az 2 karakter girilmelidir", Toast.LENGTH_LONG).show()
                return
            }
            textViewEmptyList.visibility = View.GONE
            productViewModel.getProducts(
                term = filterText,
                limit = LIST_ITEM_COUNT,
                media = mediaType.get
            )
            selectedMediaType = mediaType
        }
    }

    companion object {
        private const val GRID_SPAN_COUNT = 2
        private const val LIST_ITEM_COUNT = "20"
        fun newInstance() = ProductFragment()
    }
}