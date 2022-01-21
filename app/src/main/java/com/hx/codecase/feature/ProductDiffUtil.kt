package com.hx.codecase.feature

import androidx.recyclerview.widget.DiffUtil
import com.hx.codecase.domain.model.ProductItem

class ProductDiffUtil(
    private val oldList: List<ProductItem>,
    private val newList: List<ProductItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].collectionId === newList[newItemPosition].collectionId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}