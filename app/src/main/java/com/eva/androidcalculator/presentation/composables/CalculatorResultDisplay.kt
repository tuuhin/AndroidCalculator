package com.eva.androidcalculator.presentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorResultDisplay(
    expression: String,
    isError: Boolean,
    result: String,
    textColor: Color,
    modifier: Modifier = Modifier
) {

    val hScroll = rememberScrollState()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        BasicTextField(
            value = expression,
            onValueChange = {},
            textStyle = TextStyle(
                fontSize = 50.sp,
                color = if (isError)
                    MaterialTheme.colorScheme.onErrorContainer
                else
                    textColor,
                textAlign = TextAlign.End,
                fontWeight = FontWeight.Bold,
            ),
            maxLines = 1,
            singleLine = true,
            readOnly = true,
            cursorBrush = SolidColor(textColor),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)

        )
        AnimatedVisibility(
            visible = result.isNotEmpty() || expression != "0",
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = result,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 20.dp)
                    .horizontalScroll(hScroll),
                style = TextStyle(
                    fontSize = 40.sp,
                    color = if (isError)
                        MaterialTheme.colorScheme.onErrorContainer
                    else
                        textColor,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.SemiBold,
                ), overflow = TextOverflow.Ellipsis
            )
        }
        RoundedDash(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}