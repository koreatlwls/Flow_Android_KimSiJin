package com.example.flow.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flow.R
import com.example.flow.databinding.FragmentSearchBinding
import com.example.flow.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val searchViewModel: SearchViewModel by viewModels()

    private lateinit var searchPagingAdapter: SearchPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        searchViewModel.getMovies("Iron Man")
        lifecycleScope.launch{
            searchViewModel.movieResponse.collectLatest {
                searchPagingAdapter.submitData(it)
            }
        }

    }

    override fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initAdapter() {
        searchPagingAdapter = SearchPagingAdapter {
            Log.e("ABC", it.toString())
        }

        binding.recyclerViewSearchResult.adapter = searchPagingAdapter
        binding.recyclerViewSearchResult.layoutManager = LinearLayoutManager(requireContext())
    }

}