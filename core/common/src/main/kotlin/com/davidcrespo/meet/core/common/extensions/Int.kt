package com.davidcrespo.meet.core.common.extensions

fun Int.Companion.empty() = 0

fun Int?.orEmpty(): Int = this ?: 0
