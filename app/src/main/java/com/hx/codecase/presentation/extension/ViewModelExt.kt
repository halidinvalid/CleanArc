package com.hx.codecase.presentation.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

inline fun ViewModel.launchViewModelScope(
    crossinline scopeMethod: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch {
        withContext(Dispatchers.Default) {
            scopeMethod()
        }
    }
}

