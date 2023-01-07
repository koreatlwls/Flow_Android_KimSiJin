package com.example.flow.ui.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.flow.ui.search.UiState
import com.google.android.material.progressindicator.CircularProgressIndicator

@BindingAdapter("showOnLoading")
fun CircularProgressIndicator.showOnLoading(uiState: UiState) {
    visibility = if (uiState is UiState.Loading)
        View.VISIBLE
    else
        View.GONE
}