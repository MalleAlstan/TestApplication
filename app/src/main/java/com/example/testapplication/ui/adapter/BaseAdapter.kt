package com.example.testapplication.ui.adapter

import androidx.annotation.MainThread
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<D, VH : RecyclerView.ViewHolder>
    (protected val itemList: MutableList<D> = arrayListOf()) : RecyclerView.Adapter<VH>() {

    override fun getItemCount(): Int {
        return itemList.size
    }

    @MainThread
    open fun setItems(items: List<D>) {
        itemList.clear()
        itemList.addAll(items)

        notifyDataSetChanged()
    }

    @MainThread
    fun clearItems() {
        itemList.clear()
        notifyDataSetChanged()
    }
}