package com.eva.androidcalculator.domain

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test


class ExpressionEvaluatorTest {
    private lateinit var expressionEvaluator: ExpressionEvaluator

    @Before
    fun setup() {
        expressionEvaluator = ExpressionEvaluator()
    }

    @Test
    fun `normal sum text parsing`() {
        val expression = listOf(
            Expression.Number(1.0),
            Expression.Operation(Operators.ADD),
            Expression.Number(2.0)
        )
        assertEquals(expressionEvaluator.evaluate(expression), 3.0)
    }

    @Test
    fun `normal subtract test`() {
        val expression = listOf(
            Expression.Number(1.0),
            Expression.Operation(Operators.SUBTRACT),
            Expression.Number(2.0)
        )
        assertEquals(expressionEvaluator.evaluate(expression), -1.0)
    }

    @Test
    fun `normal multiply test`() {

        val expression = listOf(
            Expression.Number(1.0),
            Expression.Operation(Operators.MULTIPLY),
            Expression.Number(2.0)
        )
        assertEquals(expressionEvaluator.evaluate(expression), 2.0)
    }

    @Test
    fun `normal divide  test with decimals`() {
        val expression = listOf(
            Expression.Number(1.54),
            Expression.Operation(Operators.DIVIDE),
            Expression.Number(2.78)
        )
        assertEquals(expressionEvaluator.evaluate(expression), 0.5539568345323741)
    }

    @Test
    fun `normal parsing with multiple operands`() {
        val expression = listOf(
            Expression.Number(1.0),
            Expression.Operation(Operators.ADD),
            Expression.Number(25.0),
            Expression.Operation(Operators.MULTIPLY),
            Expression.Number(34.0),
            Expression.Operation(Operators.SUBTRACT),
            Expression.Number(3.0),
            Expression.Operation(Operators.DIVIDE),
            Expression.Number(2.0)
        )
        assertEquals(expressionEvaluator.evaluate(expression), 849.5)
    }

    @Test
    fun `evaluate complex expression`() {
        val expression = listOf(
            Expression.Number(1.0),
            Expression.Operation(Operators.ADD),
            Expression.Number(56.0),
            Expression.Operation(Operators.SUBTRACT),
            Expression.Parenthesis(ParenthesisType.Opening),
            Expression.Number(45.0),
            Expression.Operation(Operators.SUBTRACT),
            Expression.Number(9.0),
            Expression.Operation(Operators.DIVIDE),
            Expression.Number(3.0),
            Expression.Parenthesis(ParenthesisType.Closing),
            Expression.Operation(Operators.MULTIPLY),
            Expression.Number(2.0)
        )
        assertEquals(expressionEvaluator.evaluate(expression), -27.0)
    }

    @Test
    fun `check factorial`() {
        val expression = listOf(
            Expression.Number(2.0),
            Expression.Operation(Operators.ADD),
            Expression.Factorial(3.0)
        )
        assertEquals(expressionEvaluator.evaluate(expression), 8.0)
    }

    @Test
    fun `check pi`() {
        val expression = listOf(
            Expression.Number(4.0),
            Expression.PI,
        )
        assertEquals(expressionEvaluator.evaluate(expression), 113.097)
    }
}