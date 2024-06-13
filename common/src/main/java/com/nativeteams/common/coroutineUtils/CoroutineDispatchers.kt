package com.nativeteams.common.coroutineUtils

import kotlinx.coroutines.CoroutineDispatcher

data class CoroutineDispatchers(
    val default: CoroutineDispatcher,
    val io: CoroutineDispatcher,
    val main: CoroutineDispatcher,
)
