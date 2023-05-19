package com.eva.androidcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.eva.androidcalculator.presentation.CalculatorScreen
import com.eva.androidcalculator.ui.theme.AndroidCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidCalculatorTheme {
                CalculatorScreen()
            }
        }
    }
}

