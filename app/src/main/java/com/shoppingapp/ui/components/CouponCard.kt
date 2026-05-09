package com.shoppingapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shoppingapp.ui.theme.CustomColor
import com.shoppingapp.ui.theme.Dimens

@Composable
fun CouponCard(
    discount: String,
    title: String,
    desc: String,
    code: String,
    expiry: String
) {
    Row(modifier = Modifier
            .fillMaxWidth()
            .ticketCutBackground(Color(0xFFF5F5F5))
            .padding(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(CustomColor.PrimaryColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = discount,
                color = CustomColor.AlternateColor,
                fontSize = Dimens.TextLargest,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(title, fontWeight = FontWeight.SemiBold, fontSize = Dimens.TextMedium)

            Text(
                desc,
                color = Color.Gray,
                fontSize = 13.sp
            )

            Text(
                text = "Code: $code",
                fontWeight = FontWeight.Medium
            )
        }

        Canvas(
            modifier = Modifier
                .height(70.dp)
                .width(2.dp)
        ) {
            drawLine(
                color = Color.Black,
                start = Offset(0f, 0f),
                end = Offset(0f, size.height),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
            )
        }

        Spacer(modifier = Modifier.width(Dimens.PaddingSmall))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Exp.", color = CustomColor.PrimaryColor, fontSize = Dimens.TextLarge)
            Text(expiry, fontWeight = FontWeight.Medium)
        }
    }

}

fun Modifier.ticketCutBackground(
    color: Color
): Modifier = this.drawBehind {

    // Draw main background
    drawRoundRect(
        color = color,
        cornerRadius = CornerRadius(20.dp.toPx(), 20.dp.toPx())
    )

    val radius = 8.dp.toPx()
    val gap = 12.dp.toPx()

    var y = radius

    while (y < size.height) {
        drawCircle(
            color = Color.White,
            radius = radius,
            center = Offset(0f, y)
        )
        y += radius * 2 + gap
    }
}