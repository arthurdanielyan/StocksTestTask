package com.nativeteams.common.data.repository

import com.nativeteams.common.data.network.NetworkApiService
import com.nativeteams.common.data.repository.mappers.StockResponseMapper
import com.nativeteams.common.domain.models.StockItem
import com.nativeteams.common.domain.repository.StocksRepository
import javax.inject.Inject

class StocksRepositoryImpl @Inject constructor(
    private val networkApiService: NetworkApiService,
    private val stockResponseMapper: StockResponseMapper,
) : StocksRepository {

    companion object {
        private const val REGION_KEY = "region"
        private const val REGION_US = "US"
    }

    override suspend fun getStocks(): List<StockItem> =
        stockResponseMapper.map(
            networkApiService.getStocks(
                parameters = mapOf(
                    REGION_KEY to REGION_US
                )
            )
        )
}