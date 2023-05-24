package com.eva.androidcalculator.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eva.androidcalculator.R

@Composable
fun RoundedDash(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(24.dp)
            .width(30.dp)
            .padding(vertical = 10.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.rounded_dash),
            contentDescription = "Small Dash",
            colorFilter = ColorFilter.tint(
                MaterialTheme.colorScheme.onTertiaryContainer
            )
        )
    }
}