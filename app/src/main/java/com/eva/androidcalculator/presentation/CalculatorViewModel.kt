package com.eva.androidcalculator.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eva.androidcalculator.domain.ExpressionEvaluator
import com.eva.androidcalculator.domain.ExpressionParser
import com.eva.androidcalculator.domain.MathsSymbols
import com.eva.androidcalculator.domain.Operators
import com.eva.androidcalculator.presentation.util.CalculatorEvents
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.floor


class CalculatorViewModel(
    private val parser: ExpressionParser = ExpressionParser(),
    private val evaluator: ExpressionEvaluator = ExpressionEvaluator(),
) : ViewModel() {

    private val _result = MutableStateFlow("")
    val result = _result.asStateFlow()

    private val _isResultError = MutableStateFlow(false)
    val isResultError = _isResultError.asStateFlow()

    private val _expression = MutableStateFlow("")
    val expression = _expression.asStateFlow()


    fun onCalculatorEvent(events: CalculatorEvents) {
        if (_isResultError.value) _isResultError.update { false }
        when (events) {
            CalculatorEvents.Evaluate -> {
                viewModelScope.launch(Dispatchers.Default) {
                    try {
                        val parsed = parser.parse(prepareForCalculation())
                        val evaluated = evaluator.evaluate(parsed)

                        //  if the result is int return the int otherwise return double
                        _result.update {
                            if (floor(evaluated) == evaluated)
                                evaluated.toInt().toString()
                            else
                                evaluated.toString()
                        }

                    } catch (e: Exception) {
                        _isResultError.update { true }
                        _result.update { e.message ?: "Failed Evaluation" }
                        e.printStackTrace()
                    }
                }
            }

            CalculatorEvents.ClearAll -> {
                _expression.update { "" }
                _result.update { "0" }
            }

            CalculatorEvents.Decimal -> if (canEnterDecimal())
                _expression.update { "$it." }

            CalculatorEvents.Clear -> if (_expression.value.isNotEmpty())
                _expression.update { it.dropLast(1) }

            is CalculatorEvents.Number -> _expression.update { "$it${events.number}" }

            is CalculatorEvents.Operation -> if (canEnterOperation())
                _expression.update { "$it${events.operators.symbol}" }

            is CalculatorEvents.Parenthesis -> processParentheses()
            is CalculatorEvents.Factorial -> if (canEnterFactorial())
                _expression.update { "$it!" }

            CalculatorEvents.PI -> processPi()
            is CalculatorEvents.Sqrt -> if (canEnterSqrt())
                _expression.update { "$it${MathsSymbols.SQRT_SYMBOL}" }
        }

    }

    private fun canEnterSqrt(): Boolean = _expression.value.isEmpty()
            || _expression.value.isNotEmpty() && _expression.value.last() !in ")!"


    private fun canEnterFactorial(): Boolean = _expression.value.isNotEmpty()
            && _expression.value.last() in "0123456789"

    private fun processPi() {
        val previousPi = _expression.value.last() == MathsSymbols.PI_SYMBOL
        if (!previousPi) _expression.update { "$it${MathsSymbols.PI_SYMBOL}" }
    }

    private fun prepareForCalculation(): String {
        val newExpression =
            _expression.value.dropLastWhile { it in "${Operators.allOperatorSymbols}(." }
        if (newExpression.isEmpty()) return "0"
        return newExpression
    }

    private fun processParentheses() {
        val openingCount = _expression.value.count { it == '(' }
        val closingCount = _expression.value.count { it == ')' }
        _expression.value += when {
            _expression.value.isEmpty() ||
                    expression.value.last() in "${Operators.allOperatorSymbols}(" -> "("

            _expression.value.last() in "0123456789)" &&
                    openingCount == closingCount -> return

            else -> ")"
        }
    }

    private fun canEnterDecimal(): Boolean = _expression.value.isNotEmpty()
            && expression.value.last() !in "${Operators.allOperatorSymbols}.()"
            && !_expression.value.takeLastWhile { it in "0123456789." }
        .contains(".")


    private fun canEnterOperation(): Boolean = _expression.value.isNotEmpty()
            && _expression.value.last() in "0123456789)${MathsSymbols.PI_SYMBOL}${MathsSymbols.FACTORIAL_SYMBOL}"

}