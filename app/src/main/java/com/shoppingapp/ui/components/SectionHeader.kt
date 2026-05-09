package com.shoppingapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.shoppingapp.ui.theme.Dimens

@Composable
fun SectionHeader(
    title: String,
    onlyTitle: Boolean = false
) {
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title,
            fontSize = Dimens.TextLargest,
            fontWeight = FontWeight(500),
            letterSpacing = 1.1.sp)

        if (onlyTitle) Text(text = "Show all",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
    }
}