package com.nativeteams.stocksscreen.view.viewState

data class StockItemViewState(
    val symbol: String,
    val fullExchangeName: String,
    val amount: String,
    val amountChange: String,
    val amountChangePercent: String,
    val hasRisen: Boolean,
)