package com.nativeteams.stocksscreen.view.viewState

import com.nightx.ingale.core.domain.LoadingViewState

data class StocksScreenViewState(
    val stocks: List<StockItemViewState> = emptyList(),
    val loadingState: LoadingViewState = LoadingViewState.Loading,
)