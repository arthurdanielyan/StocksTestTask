package com.nightx.ingale.core.domain

enum class LoadingViewState {
    Loading, Success, Error, NetworkError;

    val isLoading: Boolean
        get() = this == Loading

    val isError: Boolean
        get() = this == Error || this == NetworkError
}