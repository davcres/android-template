package com.davidcrespo.meet.core.common.extensions

fun Boolean?.orEmpty(): Boolean = this == true

fun Boolean.Companion.empty() = false
