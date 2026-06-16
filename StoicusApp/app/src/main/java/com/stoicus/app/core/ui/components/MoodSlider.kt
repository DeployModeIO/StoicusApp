package com.stoicus.app.core.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stoicus.app.core.ui.theme.*

@Composable
fun MoodSlider(
    label: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    emoji: String = "😊"
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$emoji $label",
                style = MaterialTheme.typography.bodyLarge,
                color = TextWhite
            )
            Text(
                text = "${value.toInt()}/10",
                style = MaterialTheme.typography.bodyMedium,
                color = TextGrey
            )
        }
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 1f..10f,
            steps = 8,
            colors = SliderDefaults.colors(
                thumbColor = Bronze,
                activeTrackColor = Bronze,
                inactiveTrackColor = StoneLight
            )
        )
    }
}