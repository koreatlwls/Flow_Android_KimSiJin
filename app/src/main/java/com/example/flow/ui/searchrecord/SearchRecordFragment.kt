package com.example.flow.ui.searchrecord

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flow.R
import com.example.flow.databinding.FragmentSearchRecordBinding
import com.example.flow.ui.BaseFragment
import com.example.flow.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchRecordFragment : BaseFragment<FragmentSearchRecordBinding>(R.layout.fragment_search_record) {

    private val searchViewModel: SearchViewModel by viewModels()

    private lateinit var searchRecordAdapter: SearchRecordAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }

    override fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.searchViewModel = searchViewModel
        binding.fragment = this
    }

    private fun initAdapter() {
        searchRecordAdapter = SearchRecordAdapter {
            Log.e("ABC", it)
        }

        binding.recyclerViewSearchRecord.adapter = searchRecordAdapter
        binding.recyclerViewSearchRecord.layoutManager = LinearLayoutManager(requireContext())
    }

    fun moveBack() {
        binding.root.findNavController().popBackStack()
    }

}