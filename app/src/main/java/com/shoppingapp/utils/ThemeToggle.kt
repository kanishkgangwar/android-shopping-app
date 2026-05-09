package com.shoppingapp.utils

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shoppingapp.R

@SuppressLint("UseOfNonLambdaOffsetOverload")
@Composable
fun ThemeToggle(
    isLightMode: Boolean,
    onToggle: (Boolean) -> Unit
) {
    val offset by animateDpAsState(
        targetValue = if (isLightMode) 0.dp else 70.dp,
        label = ""
    )

    Box(modifier = Modifier
            .width(150.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(50))
            .background(
                if (isLightMode) Color(0xFFE0E0E0) else Color(0xFF333333)
            )
            .clickable { onToggle(!isLightMode) }
            .padding(4.dp)
    ) {
        Box(modifier = Modifier
                .offset(x = offset)
                .width(75.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(50))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(painter = painterResource(
                        id = if (isLightMode) R.drawable.light_mode else R.drawable.dark_mode
                    ),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(text = if (isLightMode) "Light" else "Dark",
                    fontSize = 12.sp)
            }
        }
    }
}