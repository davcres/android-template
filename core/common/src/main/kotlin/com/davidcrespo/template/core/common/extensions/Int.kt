package com.davidcrespo.template.core.common.extensions

fun Int.Companion.empty() = 0

fun Int?.orEmpty(): Int = this ?: 0
