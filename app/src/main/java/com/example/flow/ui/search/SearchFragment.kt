package com.example.flow.ui.search

import com.example.flow.R
import com.example.flow.databinding.FragmentSearchBinding
import com.example.flow.ui.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    override fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

}