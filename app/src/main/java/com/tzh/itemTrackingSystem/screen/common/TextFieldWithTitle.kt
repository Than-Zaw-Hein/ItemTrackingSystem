package com.tzh.itemTrackingSystem.screen.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tzh.itemTrackingSystem.ui.theme.RFIDTextColor

@Composable
fun TextFieldWithTitle(title: String, value: String, textChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .scale(1f, .75f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val style = MaterialTheme.typography.bodyMedium.copy(
            textAlign = TextAlign.Justify, fontFamily = FontFamily.Serif, color = RFIDTextColor, fontWeight = FontWeight.Bold
        )
        Text(
            text = title, modifier = Modifier.weight(.3f), style = style
        )

        OutlinedTextField(
            value = value,
            onValueChange = {
                textChange(it)
            },
            shape = RectangleShape,
            modifier = Modifier.weight(.8f),
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
        )
    }
}

