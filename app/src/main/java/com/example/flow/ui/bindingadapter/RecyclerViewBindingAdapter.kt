package com.example.flow.ui.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flow.ui.search.UiState

@BindingAdapter("showSuccess")
fun RecyclerView.showSuccess(uiState: UiState) {
    visibility = if (uiState is UiState.Success)
        View.VISIBLE
    else
        View.GONE
}