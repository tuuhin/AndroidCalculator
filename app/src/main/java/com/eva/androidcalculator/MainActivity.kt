package com.eva.androidcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import com.eva.androidcalculator.presentation.CalculatorScreen
import com.eva.androidcalculator.presentation.CalculatorViewModel
import com.eva.androidcalculator.ui.theme.AndroidCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidCalculatorTheme {

                val viewModel by viewModels<CalculatorViewModel>()
                val expression by viewModel.expression.collectAsStateWithLifecycle()
                val result by viewModel.result.collectAsStateWithLifecycle()
                val isError by viewModel.isResultError.collectAsStateWithLifecycle()

                CalculatorScreen(
                    expression = expression,
                    result = result,
                    isError = isError,
                    onCalculatorEvent = viewModel::onCalculatorEvent
                )
            }
        }
    }
}

