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

@BindingAdapter("isEmptyList")
fun <T> TextView.isEmptyList(list: List<T>) {
    visibility = if (list.isEmpty())
        View.VISIBLE
    else
        View.GONE
}

@BindingAdapter("isNotEmptyList")
fun <T> TextView.isNotEmptyList(list: List<T>) {
    visibility = if (list.isEmpty())
        View.GONE
    else
        View.VISIBLE
}