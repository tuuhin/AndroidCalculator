package com.eva.androidcalculator.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.eva.androidcalculator.presentation.composables.CalculatorExtraButtons
import com.eva.androidcalculator.presentation.composables.CalculatorGrid
import com.eva.androidcalculator.presentation.composables.CalculatorResultDisplay
import com.eva.androidcalculator.presentation.util.CalculatorEvents
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(
    expression: String,
    result: String,
    isError: Boolean,
    onCalculatorEvent: (CalculatorEvents) -> Unit,
    modifier: Modifier = Modifier,
    darkTheme: Boolean = isSystemInDarkTheme(),
    statusColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
    onStatusColor: Color = MaterialTheme.colorScheme.onTertiaryContainer,
    density: Density = LocalDensity.current
) {
    val systemUiController = rememberSystemUiController()

    var isExtraOptionsVisible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(darkTheme, systemUiController) {
        systemUiController.setStatusBarColor(color = statusColor, darkIcons = !darkTheme)

    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Calculator") },
                    actions = {
                        IconButton(
                            onClick = { isExtraOptionsVisible = !isExtraOptionsVisible }) {
                            Icon(
                                imageVector = Icons.Outlined.MoreVert,
                                contentDescription = "Extra functions"
                            )
                        }
                    }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = statusColor,
                        titleContentColor = onStatusColor,
                        actionIconContentColor = onStatusColor
                    )
                )
            }
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
            ) {
                CalculatorResultDisplay(
                    expression = expression,
                    isError = isError,
                    textColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    result = result,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(RoundedCornerShape(bottomEnd = 25.dp, bottomStart = 25.dp))
                        .background(statusColor)
                )
                AnimatedVisibility(
                    visible = isExtraOptionsVisible,
                    enter = slideInVertically {
                        with(density) { 40.dp.roundToPx() }
                    } + fadeIn(),
                    exit = slideOutVertically {
                        with(density) { 40.dp.roundToPx() }
                    } + fadeOut()
                ) {
                    CalculatorExtraButtons(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .animateContentSize(),
                        onTap = onCalculatorEvent
                    )
                }
                CalculatorGrid(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    onTap = onCalculatorEvent
                )
            }
        }
    }
}

@Preview
@Composable
fun CalculatorScreenPreview() {
    CalculatorScreen(
        expression = "",
        result = "",
        onCalculatorEvent = {},
        isError = false
    )
}