package com.nativeteams.common.utils

interface Mapper<F, T> {

    fun map(from: F): T
}

fun <F, T> Mapper<F, T>.mapList(from: List<F>): List<T> =
    from.map { map(it) }