package com.example.flow.ui.bindingadapter

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.flow.ui.search.UiState

@BindingAdapter("showEmpty")
fun TextView.showEmpty(uiState: UiState) {
    visibility = if (uiState is UiState.Empty)
        View.VISIBLE
    else
        View.GONE
}