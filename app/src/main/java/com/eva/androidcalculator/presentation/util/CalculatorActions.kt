package com.eva.androidcalculator.presentation.util

import com.eva.androidcalculator.domain.MathsSymbols
import com.eva.androidcalculator.domain.Operators

val actions = listOf(
    CalculatorButtonAction(
        action = "AC",
        type = CalculatorButtonType.Functions,
        onPress = CalculatorEvents.ClearAll
    ),
    CalculatorButtonAction(
        action = "()",
        type = CalculatorButtonType.Functions,
        onPress = CalculatorEvents.Parenthesis
    ),
    CalculatorButtonAction(
        action = "%",
        type = CalculatorButtonType.Functions,
        onPress = CalculatorEvents.Operation(Operators.PERCENTAGE)
    ),
    CalculatorButtonAction(
        action = "/",
        type = CalculatorButtonType.Operator,
        onPress = CalculatorEvents.Operation(Operators.DIVIDE)
    ),
    CalculatorButtonAction(
        action = "7",
        type = CalculatorButtonType.Operand,
        onPress = CalculatorEvents.Number(7)
    ),
    CalculatorButtonAction(
        action = "8",
        type = CalculatorButtonType.Operand,
        onPress = CalculatorEvents.Number(8)
    ),
    CalculatorButtonAction(
        action = "9",
        type = CalculatorButtonType.Operand,
        onPress = CalculatorEvents.Number(9)
    ),
    CalculatorButtonAction(
        action = "x",
        type = CalculatorButtonType.Operator,
        onPress = CalculatorEvents.Operation(Operators.MULTIPLY)
    ),
    CalculatorButtonAction(
        action = "4",
        type = CalculatorButtonType.Operand,
        onPress = CalculatorEvents.Number(4)
    ),
    CalculatorButtonAction(
        action = "5",
        type = CalculatorButtonType.Operand,
        onPress = CalculatorEvents.Number(5)
    ),
    CalculatorButtonAction(
        action = "6",
        type = CalculatorButtonType.Operand,
        onPress = CalculatorEvents.Number(6)
    ),
    CalculatorButtonAction(
        action = "-",
        type = CalculatorButtonType.Operator,
        onPress = CalculatorEvents.Operation(Operators.SUBTRACT)
    ),
    CalculatorButtonAction(
        action = "1",
        type = CalculatorButtonType.Operand,
        onPress = CalculatorEvents.Number(1)
    ),
    CalculatorButtonAction(
        action = "2",
        type = CalculatorButtonType.Operand,
        onPress = CalculatorEvents.Number(2)
    ),
    CalculatorButtonAction(
        action = "3",
        type = CalculatorButtonType.Operand,
        onPress = CalculatorEvents.Number(3)
    ),
    CalculatorButtonAction(
        action = "+",
        type = CalculatorButtonType.Operator,
        onPress = CalculatorEvents.Operation(Operators.ADD)
    ),
    CalculatorButtonAction(
        action = "C",
        type = CalculatorButtonType.Operand,
        onPress = CalculatorEvents.Clear
    ),
    CalculatorButtonAction(
        action = "0",
        type = CalculatorButtonType.Operand,
        onPress = CalculatorEvents.Number(0)
    ),
    CalculatorButtonAction(
        action = ".",
        type = CalculatorButtonType.Operand,
        onPress = CalculatorEvents.Decimal
    ),
    CalculatorButtonAction(
        action = "=",
        type = CalculatorButtonType.Operator,
        onPress = CalculatorEvents.Evaluate
    )
)

val extraActions =
    listOf(
        CalculatorButtonAction(
            action = "${MathsSymbols.SQRT_SYMBOL}",
            type = CalculatorButtonType.ExtraFunction,
            onPress = CalculatorEvents.Sqrt
        ),
        CalculatorButtonAction(
            action = "${MathsSymbols.PI_SYMBOL}",
            type = CalculatorButtonType.ExtraFunction,
            onPress = CalculatorEvents.PI
        ),
        CalculatorButtonAction(
            action = "^",
            type = CalculatorButtonType.ExtraFunction,
            onPress = CalculatorEvents.Operation(Operators.EXPONENT)
        ),
        CalculatorButtonAction(
            action = "${MathsSymbols.FACTORIAL_SYMBOL}",
            type = CalculatorButtonType.ExtraFunction,
            onPress = CalculatorEvents.Factorial
        )
    )