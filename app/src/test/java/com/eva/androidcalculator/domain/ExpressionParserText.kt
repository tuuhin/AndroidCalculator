package com.eva.androidcalculator.domain

import org.junit.Test
import junit.framework.TestCase.assertEquals


class ExpressionParserTest {
    private val expressionParser = ExpressionParser()

    @Test
    fun `normal sum text parsing`() {
        val expression = "1+2"
        val result = listOf(
            Expression.Number(1.0),
            Expression.Operation(Operators.ADD),
            Expression.Number(2.0)
        )
        assertEquals(expressionParser.parse(expression), result)
    }

    @Test
    fun `normal subtract test`() {
        val expression = "1-2"
        val result = listOf(
            Expression.Number(1.0),
            Expression.Operation(Operators.SUBTRACT),
            Expression.Number(2.0)
        )
        assertEquals(expressionParser.parse(expression), result)
    }

    @Test
    fun `normal multiply test`() {
        val expression = "1x2"
        val result = listOf(
            Expression.Number(1.0),
            Expression.Operation(Operators.MULTIPLY),
            Expression.Number(2.0)
        )
        assertEquals(expressionParser.parse(expression), result)
    }

    @Test
    fun `normal divide  test with decimals`() {
        val expression = "1.54/2.78"
        val result = listOf(
            Expression.Number(1.54),
            Expression.Operation(Operators.DIVIDE),
            Expression.Number(2.78)
        )
        assertEquals(expressionParser.parse(expression), result)
    }

    @Test
    fun `normal parsing with multiple operands`() {
        val expression = "1+25x34-3/2"
        val result = listOf(
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
        assertEquals(expressionParser.parse(expression), result)
    }


    @Test
    fun `expression with factorial`() {
        val expression = "2+3!"
        val result = listOf(
            Expression.Number(2.0),
            Expression.Operation(Operators.ADD),
            Expression.Factorial(3.0)
        )
        assertEquals(expressionParser.parse(expression), result)
    }

    @Test
    fun `expression with pi`() {
        val expression = "4π3^2"
        val result = listOf(
            Expression.Number(4.0),
            Expression.PI,
            Expression.Number(3.0),
            Expression.Operation(Operators.EXPONENT),
            Expression.Number(2.0)
        )
        assertEquals(expressionParser.parse(expression), result)
    }

    @Test
    fun `normal pi expression`() {
        val expression = "12π"
        val result = listOf(
            Expression.Number(12.0),
            Expression.PI,
            Expression.Number(1.0)
        )
        assertEquals(expressionParser.parse(expression), result)
    }

    @Test
    fun `expression with sqrt`() {
        val expression = "2+√2-2"
        val result = listOf(
            Expression.Number(2.0),
            Expression.Operation(Operators.ADD),
            Expression.Sqrt(2.0),
            Expression.Operation(Operators.SUBTRACT),
            Expression.Number(2.0)
        )
        assertEquals(expressionParser.parse(expression), result)
    }

    @Test
    fun `expression with sqrt complex`() {
        val expression = "2+2√2"
        val result = listOf(
            Expression.Number(2.0),
            Expression.Operation(Operators.ADD),
            Expression.Number(2.0),
            Expression.Operation(Operators.MULTIPLY),
            Expression.Sqrt(2.0)
        )
        assertEquals(expressionParser.parse(expression), result)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `sqrt failed test`() {
        val expression = "3√"
        val result = listOf(Expression.Number(3.0), Expression.Sqrt(1.0))
        assertEquals(expressionParser.parse(expression), result)
    }

    @Test
    fun `expression with brackets`() {
        val expression = "1+56-(45-9/3)x2"
        val result = listOf(
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

        assertEquals(expressionParser.parse(expression), result)
    }
}