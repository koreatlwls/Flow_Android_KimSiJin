package com.example.flow

import com.example.flow.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    override fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
    }

}