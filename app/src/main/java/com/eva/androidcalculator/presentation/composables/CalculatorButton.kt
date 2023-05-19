package com.eva.androidcalculator.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.sp
import com.eva.androidcalculator.presentation.util.CalculatorButtonType
import com.eva.androidcalculator.presentation.util.CalculatorButtonAction

@Composable
fun CalculatorButton(
    onClick: () -> Unit,
    action: CalculatorButtonAction,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.large)
            .background(
                when (action.type) {
                    is CalculatorButtonType.Functions -> MaterialTheme.colorScheme.secondaryContainer
                    is CalculatorButtonType.Operator -> MaterialTheme.colorScheme.primaryContainer
                    else -> MaterialTheme.colorScheme.surfaceVariant
                }
            )
            .aspectRatio(1f)
            .clickable(onClick = onClick, role = Role.Button),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = action.action,
            textAlign = TextAlign.Center,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp),
            color = when (action.type) {
                is CalculatorButtonType.Functions -> MaterialTheme.colorScheme.onSecondaryContainer
                is CalculatorButtonType.Operator -> MaterialTheme.colorScheme.onPrimaryContainer
                else -> MaterialTheme.colorScheme.onSurfaceVariant
            }
        )
    }
}

class CalculatorButtonPreviewParams() : PreviewParameterProvider<CalculatorButtonAction> {
    override val values: Sequence<CalculatorButtonAction>
        get() = sequenceOf(
            CalculatorButtonAction(action = "0", type = CalculatorButtonType.Operator),
            CalculatorButtonAction(action = "%", type = CalculatorButtonType.Functions),
            CalculatorButtonAction(action = "+", type = CalculatorButtonType.Operand)
        )
}

@Preview(showBackground = true)
@Composable
fun CalculatorButtonPreview(
    @PreviewParameter(CalculatorButtonPreviewParams::class) type: CalculatorButtonAction
) {
    CalculatorButton(action = type, onClick = {})
}