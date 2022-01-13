package com.example.testapplication.ui.fragment.currency

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.testapplication.databinding.FragmentCurrencyInfoBinding
import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.ui.activity.main.MainViewModel
import com.example.testapplication.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyListFragment: BaseFragment() {

    private var _binding: FragmentCurrencyInfoBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModels()

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

    }

    override fun bindData() {

        mainViewModel.currencyList.observe(this, {
            updateRecyclerView(it)
        })
    }

    private fun updateRecyclerView(list: List<CurrencyInfo>) {

        var text = ""
        if (list.isNotEmpty()) {
            for (info in list) {
                text += (info.id + "-" + info.name + " (" + list.first().symbol + ")" + "\n")
            }
        }
        binding.textview.text = text
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

