package com.example.flow.ui.bindingadapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.example.flow.ui.search.UiState
import com.google.android.material.button.MaterialButton

@BindingAdapter("showError")
fun MaterialButton.showError(uiState: UiState) {
    visibility = if (uiState is UiState.Error)
        View.VISIBLE
    else
        View.GONE
}