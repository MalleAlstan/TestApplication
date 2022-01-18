package com.example.testapplication.ui.activity.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.testapplication.R
import com.example.testapplication.databinding.ActivityMainBinding
import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.ui.activity.BaseActivity
import com.example.testapplication.ui.fragment.BaseFragment
import com.example.testapplication.ui.fragment.currency.CurrencyListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.fetchCurrency() // this will trigger the mock remote type fetching flow
    }

    override fun initView() {

        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnGet.setOnClickListener {
            mainViewModel.fetchCurrency() // this will trigger the local db fetching flow
        }

        binding.btnSort.setOnClickListener {
            mainViewModel.sortCurrency()
        }

        transToFrag(CurrencyListFragment())
    }

    override fun bindData() {

        mainViewModel.toastMessage.observe(this, {
            showToast(it)
        })
        mainViewModel.selectedCurrencyInfo.observe(this, {
            if (it.id.isNotEmpty()) {
                showToast(String.format(getString(R.string.toast_currency_info), it.name, it.symbol))

            }
        })
    }

    private fun transToFrag(frag: BaseFragment) {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, frag)
            .commit()
    }
}