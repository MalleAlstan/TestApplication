package com.example.testapplication.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    protected val TAG = this.javaClass.name
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        bindData()
    }

    abstract fun initView()
    abstract fun bindData()
}