package com.eva.androidcalculator.domain

sealed interface Expression {
    data class Number(val number: Double) : Expression
    data class Operation(val operators: Operators) : Expression
    data class Parenthesis(val type: ParenthesisType) : Expression
    object PI : Expression
    data class Sqrt(val number: Double) : Expression
    data class Factorial(val number: Double) : Expression
}

sealed interface ParenthesisType {
    object Opening : ParenthesisType
    object Closing : ParenthesisType
}