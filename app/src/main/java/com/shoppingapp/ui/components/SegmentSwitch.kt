package com.shoppingapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.shoppingapp.ui.theme.Dimens

@Composable
fun SegmentSwitch(
    options: List<String>,
    selectedIndex: Int,
    onSelected: (Int) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme

    Row(modifier = Modifier
        .fillMaxWidth()
        .border(2.dp, colorScheme.outline)
    ) {
        options.forEachIndexed { index, text ->
            val isSelected = selectedIndex == index

            val bgColor = if (isSelected) colorScheme.primary else Color.Transparent
            val textColor = if (isSelected) colorScheme.onPrimary else colorScheme.onSurface

            Box(modifier = Modifier
                    .weight(1f)
                    .background(bgColor)
                    .clickable { onSelected(index) }
                    .padding(vertical = Dimens.PaddingSmall),
                contentAlignment = Alignment.Center
            ) {
                Text(text = text,
                    color = textColor)
            }
        }
    }
}