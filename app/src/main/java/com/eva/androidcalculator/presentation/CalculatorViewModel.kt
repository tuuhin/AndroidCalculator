package com.eva.androidcalculator.presentation

import androidx.lifecycle.ViewModel
import com.eva.androidcalculator.domain.ExpressionEvaluator
import com.eva.androidcalculator.domain.ExpressionParser
import com.eva.androidcalculator.domain.MathsSymbols
import com.eva.androidcalculator.domain.Operators
import com.eva.androidcalculator.presentation.util.CalculatorEvents
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CalculatorViewModel(
    private val parser: ExpressionParser = ExpressionParser(),
    private val evaluator: ExpressionEvaluator = ExpressionEvaluator()
) : ViewModel() {

    private val _result = MutableStateFlow("")
    val result = _result.asStateFlow()

    private val _expression = MutableStateFlow("")
    val expression = _expression.asStateFlow()

    fun onCalculatorEvent(events: CalculatorEvents) {
        when (events) {
            CalculatorEvents.Evaluate -> {
                val parsed = parser.parse(_expression.value)
                val evaluated = evaluator.evaluate(parsed)
                _result.update { evaluated.toString() }
            }

            CalculatorEvents.ClearAll -> _expression.update { "" }

            CalculatorEvents.Decimal -> if (canEnterDecimal()) _expression.update { "${it}." }

            CalculatorEvents.Clear -> _expression.value = _expression.value.dropLast(1)

            is CalculatorEvents.Number -> _expression.value += events.number

            is CalculatorEvents.Operation -> if (canEnterOperation(events.operators))
                _expression.value += events.operators.symbol

            is CalculatorEvents.Parenthesis -> processParentheses()
            is CalculatorEvents.Factorial -> processFact()
            CalculatorEvents.PI -> processPi()
            is CalculatorEvents.Sqrt -> processSqrt()
        }

    }

    private fun processSqrt() {


    }

    private fun processFact() {
        if (_expression.value.isNotEmpty() || _expression.value.last() in "0123456789)")
            _expression.update { "$it!" }
    }

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

    private fun canEnterDecimal(): Boolean {
        if (_expression.value.isEmpty() || _expression.value.last() in "${Operators.allOperatorSymbols}.()") {
            return false
        }
        return !_expression.value.takeLastWhile { it in "0123456789." }.contains(".")
    }

    private fun canEnterOperation(operation: Operators): Boolean {
        if (operation in listOf(Operators.ADD, Operators.SUBTRACT)) {
            return _expression.value.isEmpty() || _expression.value.last() in "${Operators.allOperatorSymbols}()0123456789"
        }
        return _expression.value.isNotEmpty() || _expression.value.last() in "0123456789)"
    }
}