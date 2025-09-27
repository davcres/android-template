package com.davidcrespo.meet.core.common.extensions

fun String.Companion.empty() = ""

fun String.toCamelCase(): String {
    val words = this.split("[^A-Za-z0-9]+".toRegex())
        .filter { it.isNotBlank() }

    if (words.isEmpty()) {
        throw IllegalArgumentException("Invalid tag string: $this")
    }

    val firstWord = words.first().lowercase()

    val camelCasedRemainder = words.drop(1)
        .joinToString("") {
            it.lowercase().capitalizeFirstLetter()
        }

    return firstWord + camelCasedRemainder
}

fun String?.capitalizeFirstLetter(): String {
    return this?.replaceFirstChar { char -> char.uppercase() }.orEmpty()
}
