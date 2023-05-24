package com.eva.androidcalculator.domain

import com.eva.androidcalculator.util.factorial
import kotlin.math.PI
import kotlin.math.floor
import kotlin.math.pow


class ExpressionEvaluator {

    fun evaluate(expression: List<Expression>): Double =
        evalExpression(expression).value


    private fun evalExpression(expression: List<Expression>): ExpressionResult {
        val result = evalTerm(expression)
        var remaining = result.remaining
        var sum = result.value
        while (true) {
            when (remaining.firstOrNull()) {
                Expression.Operation(Operators.ADD) -> {
                    val term = evalExpression(remaining.drop(1))
                    sum += term.value
                    remaining = term.remaining
                }

                Expression.Operation(Operators.SUBTRACT) -> {
                    val term = evalExpression(remaining.drop(1))
                    sum -= term.value
                    remaining = term.remaining
                }

                else -> return ExpressionResult(remaining, sum)
            }
        }
    }

    private fun evalTerm(expression: List<Expression>): ExpressionResult {
        val result = evalFactor(expression)
        var remaining = result.remaining
        var sum = result.value
        while (true) {
            when (remaining.firstOrNull()) {
                Expression.Operation(Operators.MULTIPLY) -> {
                    val term = evalFactor(remaining.drop(1))
                    sum *= term.value
                    remaining = term.remaining
                }

                Expression.Operation(Operators.DIVIDE) -> {
                    val term = evalFactor(remaining.drop(1))
                    sum /= term.value
                    remaining = term.remaining
                }

                Expression.Operation(Operators.PERCENTAGE) -> {
                    val factor = evalFactor(remaining.drop(1))
                    sum *= (factor.value / 100.0)
                    remaining = factor.remaining
                }

                Expression.Operation(Operators.EXPONENT) -> {
                    val factor = evalFactor(remaining.drop(1))
                    sum *= sum.pow(factor.value)
                    remaining = factor.remaining
                }

                Expression.PI -> {
                    val factor = evalFactor(remaining.drop(1))
                    sum *= PI * factor.value
                    remaining = factor.remaining
                }

                else -> return ExpressionResult(remaining, sum)
            }
        }
    }

    private fun evalFactor(expression: List<Expression>): ExpressionResult {
        return when (val part = expression.firstOrNull()) {
            Expression.Operation(Operators.ADD) -> evalFactor(expression.drop(1))
            Expression.Operation(Operators.SUBTRACT) ->
                evalFactor(expression.drop(1)).run {
                    ExpressionResult(remaining, -value)
                }

            Expression.Parenthesis(ParenthesisType.Opening) ->
                evalExpression(expression.drop(1)).run {
                    ExpressionResult(remaining.drop(1), value)
                }

            Expression.Operation(Operators.PERCENTAGE) -> evalExpression(expression.drop(1))

            is Expression.Number -> ExpressionResult(
                remaining = expression.drop(1),
                value = part.number
            )

            is Expression.Factorial -> {
                if (part.number == floor(part.number)) {
                    return ExpressionResult(
                        remaining = expression.drop(1),
                        value = part.number.toInt().factorial()
                    )
                } else {
                    throw IllegalArgumentException("Domain Exception")
                }
            }

            is Expression.Sqrt -> ExpressionResult(
                remaining = expression.drop(1),
                value = part.number.pow(0.5)
            )

            else -> throw RuntimeException("Invalid part")

        }
    }
}