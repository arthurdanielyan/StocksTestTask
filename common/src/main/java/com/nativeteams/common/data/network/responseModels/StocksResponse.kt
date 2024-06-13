package com.nativeteams.common.data.network.responseModels

import com.google.gson.annotations.SerializedName

data class StocksResponse(
    @SerializedName("marketSummaryAndSparkResponse")
    val summary: Summary?,
)

data class Summary(
    @SerializedName("result")
    val stocks: List<StockResponse>?,
)

data class StockResponse(
    @SerializedName("fullExchangeName")
    val fullExchangeName: String?,

    @SerializedName("symbol")
    val symbol: String?,

    @SerializedName("spark")
    val spark: Spark?,
)

data class Spark(
    @SerializedName("previousClose")
    val previousClose: Double?,

    @SerializedName("close")
    val close: List<Double>?,
)