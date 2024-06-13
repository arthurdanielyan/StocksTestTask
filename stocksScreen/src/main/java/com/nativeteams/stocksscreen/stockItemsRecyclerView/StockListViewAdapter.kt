package com.nativeteams.stocksscreen.stockItemsRecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nativeteams.stocksscreen.databinding.StockItemBinding
import com.nativeteams.stocksscreen.view.viewState.StockItemViewState

class StockListViewAdapter : ListAdapter<StockItemViewState, RecyclerView.ViewHolder>(
    stocksDiffCallback
) {
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let { (holder as StockItemViewHolder).bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        StockItemViewHolder(
            parent.context,
            StockItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    companion object {
        val stocksDiffCallback = object : DiffUtil.ItemCallback<StockItemViewState>() {
            override fun areItemsTheSame(
                oldItem: StockItemViewState,
                newItem: StockItemViewState,
            ): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: StockItemViewState,
                newItem: StockItemViewState,
            ): Boolean = oldItem == newItem
        }
    }
}