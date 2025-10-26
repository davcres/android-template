package com.davcres.template.core.common.extensions

fun Boolean?.orEmpty(): Boolean = this == true

fun Boolean.Companion.empty() = false
