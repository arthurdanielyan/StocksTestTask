package com.nativeteams.stocksscreen.view.viewState.mapper

import com.nativeteams.common.domain.models.StockItem
import com.nativeteams.common.utils.Mapper
import com.nativeteams.stocksscreen.view.viewState.StockItemViewState
import java.math.RoundingMode
import java.text.DecimalFormat
import javax.inject.Inject

class StockItemViewStateMapper @Inject constructor() :
    Mapper<StockItem, StockItemViewState> {

    companion object {
        private const val AMOUNT_FORMATTER_PATTERN = "#,##0.00"
        private const val DIFF_FORMATTER_PATTERN = "+#,##0.00;-#,##0.00"
    }

    override fun map(from: StockItem): StockItemViewState {
        val amountFormatter = DecimalFormat(AMOUNT_FORMATTER_PATTERN)
        amountFormatter.roundingMode = RoundingMode.HALF_UP

        val amountDiffFormatter = DecimalFormat(DIFF_FORMATTER_PATTERN)
        amountDiffFormatter.roundingMode = RoundingMode.HALF_UP
        val amountChange = from.latestStockAmount - from.previousStockAmount

        val amountChangePercent = 100 * (from.latestStockAmount / from.previousStockAmount - 1)
        return StockItemViewState(
            symbol = from.symbol,
            fullExchangeName = from.fullExchangeName,
            amount = amountFormatter.format(from.latestStockAmount),
            amountChange = amountDiffFormatter.format(amountChange),
            amountChangePercent = "(${amountDiffFormatter.format(amountChangePercent)}%)",
            hasRisen = amountChange >= 0
        )
    }
}