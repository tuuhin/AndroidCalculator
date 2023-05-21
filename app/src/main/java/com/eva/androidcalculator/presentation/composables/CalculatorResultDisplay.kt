package com.eva.androidcalculator.presentation.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorResultDisplay(
    expression: String,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomEnd
    ) {
        BasicTextField(
            value = expression,
            onValueChange = {},
            textStyle = TextStyle(
                fontSize = 50.sp,
                color = textColor,

                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            singleLine = true,
            readOnly = true,
            cursorBrush = SolidColor(textColor),
            modifier = Modifier.fillMaxWidth()
        )
    }
}