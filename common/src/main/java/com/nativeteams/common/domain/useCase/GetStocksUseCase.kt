package com.nativeteams.common.domain.useCase

import com.nativeteams.common.domain.models.StockItem
import com.nativeteams.common.domain.repository.StocksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetStocksUseCase @Inject constructor(
    private val stocksRepository: StocksRepository,
) {

    suspend operator fun invoke(): Flow<List<StockItem>> =
        flow {
            emit(stocksRepository.getStocks())
        }

}