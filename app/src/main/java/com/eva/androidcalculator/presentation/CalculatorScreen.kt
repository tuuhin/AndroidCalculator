package com.eva.androidcalculator.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eva.androidcalculator.presentation.composables.CalculatorGrid
import com.eva.androidcalculator.presentation.composables.CalculatorResultDisplay
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = isSystemInDarkTheme(),
) {
    val systemUiController = rememberSystemUiController()
    val statusColor = MaterialTheme.colorScheme.tertiaryContainer

    LaunchedEffect(darkTheme, systemUiController) {
        systemUiController.setStatusBarColor(color = statusColor, darkIcons = !darkTheme)

    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            CalculatorResultDisplay(
                expression = "1x23",
                textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(bottomEnd = 25.dp, bottomStart = 25.dp))
                    .background(statusColor)
                    .padding(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            CalculatorGrid(
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen()
}