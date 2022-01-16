package com.example.testapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.model.data.CurrencyInfo
import com.example.testapplication.ui.adapter.CurrencyInfoAdapter.*

class CurrencyInfoAdapter(
    list: List<CurrencyInfo>,
    val onCurrencyInfoClicked: (CurrencyInfo) -> Unit
) : BaseAdapter<CurrencyInfo, CurrencyInfoViewHolder>(list.toMutableList()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyInfoViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_holder_currency_info, parent, false)

        return CurrencyInfoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CurrencyInfoViewHolder, position: Int) {
        val name = itemList[position].name
        if (name.isNotEmpty()) {
            holder.textLogo.text = itemList[position].name.first().toString()
        }
        holder.textName.text = itemList[position].name
        holder.textSymbol.text = itemList[position].symbol
    }

    inner class CurrencyInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textLogo: TextView = itemView.findViewById(R.id.text_currency_info_logo_item_holder)
        val textName: TextView = itemView.findViewById(R.id.text_currency_info_name_item_holder)
        val textSymbol: TextView = itemView.findViewById(R.id.text_currency_info_symbo_item_holder)

        init {
            itemView.setOnClickListener {
                onCurrencyInfoClicked(itemList[adapterPosition])
            }
        }
    }
}