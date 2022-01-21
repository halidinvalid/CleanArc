package com.hx.codecase.feature

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hx.codecase.databinding.ItemProductListBinding
import com.hx.codecase.domain.model.ProductItem
import com.hx.codecase.presentation.extension.formatDate
import com.hx.codecase.presentation.extension.loadImage

@SuppressLint("NewApi")
class ProductAdapter(private val onItemClickListener: ((ProductItem?) -> Unit)? = null) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var oldProductList = mutableListOf<ProductItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemBinding =
            ItemProductListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return oldProductList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(oldProductList[position])
    }

    fun updateList(list: MutableList<ProductItem>) {
        val diffResult = DiffUtil.calculateDiff(ProductDiffUtil(oldProductList, list))
        diffResult.dispatchUpdatesTo(this)
        oldProductList = list
    }

    inner class ProductViewHolder(private val itemBinding: ItemProductListBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(productItemData: ProductItem?) {
            itemBinding.apply {
                productItemData?.let { productItem ->
                    textViewProductName.text = productItem.collectionName
                    textViewProductPrice.text = productItem.collectionPrice
                    productItem.artworkUrl100?.loadImage(
                        context = root.context,
                        imageView = imageViewProduct
                    )
                    textViewProductReleaseDate.text = productItemData.releaseDate?.formatDate()
                    itemView.setOnClickListener {
                        onItemClickListener?.invoke(productItem)
                    }
                }
            }
        }
    }
}