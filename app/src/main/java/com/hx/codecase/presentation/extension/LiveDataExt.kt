package com.hx.codecase.presentation.extension

import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.hx.codecase.presentation.entities.DataHolder
import com.hx.codecase.presentation.entities.Error
import com.hx.codecase.presentation.entities.Status

inline fun <T : Any?> LiveData<DataHolder<T?>>.observeResponse(
    owner: LifecycleOwner,
    progressView: ProgressBar?,
    crossinline success: (T?) -> Unit = {
        // no-op
    },
    crossinline fail: (Error?) -> Unit = {
        // no-op
    }
) {
    this.observe(owner, { holder: DataHolder<T?>? ->
        when (holder?.responseType) {
            is Status.SUCCESSFUL -> {
                progressView?.visibility = View.GONE
                success(holder.data)
            }
            is Status.ERROR -> {
                progressView?.visibility = View.GONE
                fail(holder.error)
            }
            is Status.LOADING -> {
                progressView?.visibility = View.VISIBLE
            }
            else -> {}
        }
    })

}

