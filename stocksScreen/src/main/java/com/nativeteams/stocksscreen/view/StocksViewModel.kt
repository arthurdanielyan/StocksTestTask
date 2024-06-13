package com.nativeteams.stocksscreen.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nativeteams.common.coroutineUtils.CoroutineDispatchers
import com.nativeteams.common.domain.useCase.GetStocksUseCase
import com.nativeteams.common.utils.mapList
import com.nativeteams.stocksscreen.view.viewState.StockItemViewState
import com.nativeteams.stocksscreen.view.viewState.StocksScreenViewState
import com.nativeteams.stocksscreen.view.viewState.mapper.StockItemViewStateMapper
import com.nightx.ingale.core.domain.LoadingViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class StocksViewModel @Inject constructor(
    private val getStocksUseCase: GetStocksUseCase,
    private val dispatchers: CoroutineDispatchers,
    private val stockItemMapper: StockItemViewStateMapper,
) : ViewModel(), StockScreenCallbacks {

    private val stocks = MutableStateFlow(emptyList<StockItemViewState>())
    private val loadingViewState = MutableStateFlow(LoadingViewState.Loading)

    val state = combine(
        stocks,
        loadingViewState
    ) { stocks, loadingViewState ->
        StocksScreenViewState(
            stocks = stocks,
            loadingState = loadingViewState
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), StocksScreenViewState())

    private val refreshEvent = MutableSharedFlow<Unit>()

    init {
        observeRefreshEvent()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeRefreshEvent() {
        viewModelScope.launch {
            refreshEvent
                .flatMapLatest {
                    loadingViewState.update { LoadingViewState.Loading }
                    getStocksUseCase()
                        .catch { handleError(it) }
                        .flowOn(dispatchers.io)
                }
                .mapLatest {
                    loadingViewState.update { LoadingViewState.Success }
                    stockItemMapper.mapList(it)
                }.collect(stocks)
        }
    }

    private fun handleError(throwable: Throwable) {
        loadingViewState.update {
            if (throwable is UnknownHostException) {
                LoadingViewState.NetworkError
            } else {
                LoadingViewState.Error
            }
        }
    }

    override fun refresh() {
        viewModelScope.launch {
            refreshEvent.emit(Unit)
        }
    }
}