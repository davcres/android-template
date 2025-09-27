package com.davidcrespo.meet.core.common.extensions

import java.util.Locale

fun String.Companion.empty() = ""

fun String.toCamelCase(): String {
    val words = this.split("[^A-Za-z0-9]+".toRegex())
        .filter { it.isNotBlank() }

    require(words.isNotEmpty()) { "Invalid tag string: $this" }

    val firstWord = words.first().lowercase()

    val camelCasedRemainder = words.drop(1)
        .joinToString("") {
            it.lowercase().capitalizeFirstLetter()
        }

    return firstWord + camelCasedRemainder
}

fun String?.capitalizeFirstLetter(): String =
    this?.replaceFirstChar {
        if (it.isLetter()) it.titlecase(Locale.getDefault()) else it.toString()
    }.orEmpty()
