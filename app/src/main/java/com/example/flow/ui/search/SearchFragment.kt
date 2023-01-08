package com.example.flow.ui.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flow.R
import com.example.flow.databinding.FragmentSearchBinding
import com.example.flow.ui.BaseFragment
import com.example.flow.util.repeatOnStarted
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val searchViewModel: SearchViewModel by activityViewModels()

    private lateinit var searchPagingAdapter: SearchPagingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        listenSearchView()
        listenLoadState()
        observeQuery()
        observeMovieResponse()
        observeClickSearchRecord()

    }

    override fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = searchViewModel
        binding.fragment = this
    }

    private fun initAdapter() {
        searchPagingAdapter = SearchPagingAdapter {
            val action = SearchFragmentDirections.actionSearchFragmentToWebViewFragment(it.link)
            binding.root.findNavController().navigate(action)
        }

        binding.recyclerViewSearchResult.adapter = searchPagingAdapter
        binding.recyclerViewSearchResult.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun listenSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (it.isNotBlank()) {
                        searchViewModel.setQuery(it)
                        binding.searchView.clearFocus()
                    }
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean = false

        })
    }

    private fun listenLoadState() {
        searchPagingAdapter.addLoadStateListener {
            when (it.source.refresh) {
                is LoadState.Loading -> searchViewModel.setLoadingState()
                is LoadState.NotLoading -> searchViewModel.setSuccessState()
                is LoadState.Error -> searchViewModel.setErrorState()
            }

            if (it.source.refresh is LoadState.NotLoading && it.append.endOfPaginationReached && searchPagingAdapter.itemCount < 1) {
                searchViewModel.setEmptyState()
            }
        }
    }

    private fun observeQuery() {
        repeatOnStarted {
            searchViewModel.query.collectLatest {
                searchViewModel.getMovies(it)
            }
        }
    }

    private fun observeMovieResponse() {
        repeatOnStarted {
            searchViewModel.movieResponse.collectLatest {
                searchPagingAdapter.submitData(it)
            }
        }
    }

    private fun observeClickSearchRecord() {
        repeatOnStarted {
            searchViewModel.clickSearchRecord.collectLatest {
                binding.searchView.setQuery(it, true)
            }
        }
    }

    fun moveToSearchRecord() {
        binding.root.findNavController().navigate(R.id.action_searchFragment_to_searchRecordFragment)
    }

}