package com.nativeteams.common.data.repository.mappers

import com.nativeteams.common.data.network.responseModels.StocksResponse
import com.nativeteams.common.domain.models.StockItem
import com.nativeteams.common.utils.Mapper
import javax.inject.Inject

class StockResponseMapper @Inject constructor() : Mapper<StocksResponse, List<StockItem>> {

    override fun map(from: StocksResponse): List<StockItem> {
        return from.summary?.stocks?.map { stockResponse ->
            StockItem(
                symbol = stockResponse.symbol ?: "",
                fullExchangeName = stockResponse.fullExchangeName ?: "",
                latestStockAmount = stockResponse.spark?.close?.lastOrNull() ?: 0.0,
                previousStockAmount = stockResponse.spark?.previousClose ?: 0.0
            )
        } ?: emptyList()
    }
}