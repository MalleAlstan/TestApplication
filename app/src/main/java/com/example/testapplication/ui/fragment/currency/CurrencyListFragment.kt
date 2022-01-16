package com.example.testapplication.ui.fragment.currency

import android.os.Bundle
import android.util.Log
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

    private var _binding: FragmentCurrencyInfoBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var currencyInfoAdapter: CurrencyInfoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCurrencyInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        bindData()
    }

    override fun initView() {
        currencyInfoAdapter =
            CurrencyInfoAdapter(mainViewModel.currencyList.value?: arrayListOf()) { onItemClicked(it) }

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
        currencyInfoAdapter.setItems(list)
    }

    private fun onItemClicked(currencyInfo: CurrencyInfo) {
        mainViewModel.onSelectCurrencyInfo(currencyInfo)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

