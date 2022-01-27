package com.hx.codecase.presentation.extension

import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.hx.codecase.presentation.entities.DataHolder

inline fun <T : Any?> LiveData<DataHolder<T?>>.observeResponse(
    owner: LifecycleOwner,
    progressView: ProgressBar?,
    crossinline success: (T?) -> Unit = {
        // no-op
    },
    crossinline fail: (String?) -> Unit = {
        // no-op
    }
) {
    observe(owner) { holder: DataHolder<T?>? ->
        when (holder) {
            is DataHolder.Success -> {
                progressView?.visibility = View.GONE
                success(holder.data)
            }
            is DataHolder.Error -> {
                progressView?.visibility = View.GONE
                fail(holder.error)
            }
            is DataHolder.Loading -> {
                progressView?.visibility = View.VISIBLE
            }
            else -> {}
        }
    }
}

