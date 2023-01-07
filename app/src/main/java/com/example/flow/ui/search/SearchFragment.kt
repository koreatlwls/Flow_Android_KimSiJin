package com.example.flow.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.flow.R
import com.example.flow.databinding.FragmentSearchBinding
import com.example.flow.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val searchViewModel : SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.getMovies()
    }

    override fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

}