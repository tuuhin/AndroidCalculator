package com.eva.androidcalculator.domain

data class ExpressionResult(
    val remaining: List<Expression>,
    val value: Double
)