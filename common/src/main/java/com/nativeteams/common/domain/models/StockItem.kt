package com.nativeteams.common.domain.models

data class StockItem(
    val symbol: String,
    val fullExchangeName: String,
    val latestStockAmount: Double,
    val previousStockAmount: Double,
)
