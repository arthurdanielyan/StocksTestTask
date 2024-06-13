package com.nativeteams.stocksscreen.stockItemsRecyclerView

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.nativeteams.stocksscreen.R
import com.nativeteams.stocksscreen.databinding.StockItemBinding
import com.nativeteams.stocksscreen.view.viewState.StockItemViewState

class StockItemViewHolder(
    private val context: Context,
    private val binding: StockItemBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(stockItem: StockItemViewState) {
        binding.stockName.text = stockItem.symbol
        binding.stockFullName.text = stockItem.fullExchangeName
        binding.stockValue.text = stockItem.amount
        binding.stockValueDiff.text = stockItem.amountChange
        binding.stockValueDiffPercentage.text = stockItem.amountChangePercent

        if (stockItem.hasRisen) {
            binding.stockValueDiff.setTextColor(context.getColor(R.color.stockRaise))
            binding.stockValueDiffPercentage.setTextColor(context.getColor(R.color.stockRaise))
        } else {
            binding.stockValueDiff.setTextColor(context.getColor(R.color.stockDrop))
            binding.stockValueDiffPercentage.setTextColor(context.getColor(R.color.stockDrop))
        }
    }
}