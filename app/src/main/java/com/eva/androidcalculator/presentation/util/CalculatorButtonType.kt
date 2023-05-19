package com.eva.androidcalculator.presentation.util

sealed interface CalculatorButtonType {
    object Operator : CalculatorButtonType
    object Operand : CalculatorButtonType
    object Functions : CalculatorButtonType
}
