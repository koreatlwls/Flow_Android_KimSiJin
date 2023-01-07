package com.example.flow.ui.searchrecord

import com.example.flow.R
import com.example.flow.databinding.FragmentSearchRecordBinding
import com.example.flow.ui.BaseFragment

class SearchRecordFragment : BaseFragment<FragmentSearchRecordBinding>(R.layout.fragment_search_record) {

    override fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

}