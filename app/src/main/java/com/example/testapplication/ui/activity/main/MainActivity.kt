package com.example.testapplication.ui.activity.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.testapplication.R
import com.example.testapplication.databinding.ActivityMainBinding
import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.ui.activity.BaseActivity
import com.example.testapplication.ui.fragment.BaseFragment
import com.example.testapplication.ui.fragment.currency.CurrencyListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity: BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        bindData()

        mainViewModel.fetchCurrency() // this will trigger the mock remote type fetching flow
    }

    override fun initView() {

        binding = ActivityMainBinding.inflate(layoutInflater)
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

        mainViewModel.selectedCurrency.observe(this, {
            onCurrencyInfoClick(it)
        })
    }

    private fun transToFrag(frag: BaseFragment) {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, frag)
            .commit()
    }

    private fun onCurrencyInfoClick(info: CurrencyInfo) {
        if (info.id.isNotEmpty()) {
            Toast.makeText(this, info.name, Toast.LENGTH_SHORT).show()
        }
    }
}