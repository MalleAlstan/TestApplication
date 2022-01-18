package com.example.testapplication.ui.fragment.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.databinding.FragmentCurrencyInfoBinding
import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.ui.activity.main.MainViewModel
import com.example.testapplication.ui.adapter.CurrencyInfoAdapter
import com.example.testapplication.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyListFragment: BaseFragment() {

    private lateinit var _binding: FragmentCurrencyInfoBinding
    private val binding get() = _binding

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var currencyInfoAdapter: CurrencyInfoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCurrencyInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {

        currencyInfoAdapter =
            CurrencyInfoAdapter(mainViewModel.currencyList.value?: arrayListOf()) { onCurrencyInfoClicked(it) }

        binding.rvCurrencyInfo.apply {
            layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
            adapter = currencyInfoAdapter
        }
    }

    override fun bindData() {

        mainViewModel.currencyList.observe(this, {
            updateRecyclerView(it)
        })
    }

    private fun updateRecyclerView(list: List<CurrencyInfo>) {
        if (list.isNotEmpty()) {
            currencyInfoAdapter.setItems(list)
        }
    }

    private fun onCurrencyInfoClicked(currencyInfo: CurrencyInfo) {
        mainViewModel.onSelectCurrencyInfo(currencyInfo)
    }
}

