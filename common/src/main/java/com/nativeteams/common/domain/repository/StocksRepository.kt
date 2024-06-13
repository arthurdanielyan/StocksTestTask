package com.nativeteams.common.domain.repository

import com.nativeteams.common.domain.models.StockItem

interface StocksRepository {

    suspend fun getStocks(): List<StockItem>
}