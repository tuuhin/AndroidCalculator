package com.eva.androidcalculator.domain

enum class Operators(val symbol: Char) {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('x'),
    DIVIDE('/'),
    EXPONENT('^'),
    PERCENTAGE('%');

    companion object {
        val allOperatorSymbols = Operators.values().map { it.symbol }.joinToString("")

        fun operatorFromSymbol(symbol: Char): Operators =
            Operators.values().find { it.symbol == symbol } ?: throw IllegalArgumentException(
                "Invalid symbol found"
            )
    }
}