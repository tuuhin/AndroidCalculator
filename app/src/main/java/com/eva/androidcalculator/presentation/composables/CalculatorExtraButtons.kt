package com.eva.androidcalculator.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eva.androidcalculator.presentation.util.CalculatorEvents
import com.eva.androidcalculator.presentation.util.extraActions

@Composable
fun CalculatorExtraButtons(
    modifier: Modifier = Modifier,
    onTap: (CalculatorEvents) -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(4),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 10.dp),
        userScrollEnabled = false
    ) {
        items(extraActions.size) { idx ->
            CalculatorButton(
                onClick = { onTap(extraActions[idx].onPress) },
                action = extraActions[idx]
            )
        }
    }
}