package com.shoppingapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CreditCardUI(
    cardHolderName: String,
    cardNumber: String,
    expires: String,
    onDelete: (() -> Unit?)? = null
) {
    var deleteCard by remember { mutableStateOf(false) }

    Box(modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFFFB347),
                        Color(0xFFFF8C00)
                    )
                )
            )
            .clickable { if(onDelete != null) deleteCard = true }
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawCircle(
                color = Color(0x33FFFFFF),
                radius = size.width * 0.6f,
                center = Offset(size.width * 0.7f, size.height * 0.2f)
            )

            drawCircle(
                color = Color(0x55FFFFFF),
                radius = 40f,
                center = Offset(size.width * 0.2f, size.height * 0.3f)
            )

            drawCircle(
                color = Color(0x55FFFFFF),
                radius = 20f,
                center = Offset(size.width * 0.6f, size.height * 0.1f)
            )
        }

        Column(modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Row {
                    Box(modifier = Modifier
                            .size(30.dp)
                            .background(Color.Red, CircleShape)
                    )
                    Box(modifier = Modifier
                            .size(30.dp)
                            .offset(x = (-10).dp)
                            .background(Color(0xFFFFA500), CircleShape)
                    )
                }
            }

            Text(text = formatCardNumberMasked(cardNumber),
                color = Color.White,
                fontSize = 18.sp,
                letterSpacing = 4.sp
            )

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = "CARDHOLDER NAME",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 10.sp
                    )
                    Text(text = cardHolderName,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(text = "VALID TILL",
                        color = Color.White.copy(alpha = 0.7f),
                        fontSize = 10.sp
                    )
                    Text(text = expires,
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }

    if (deleteCard) {
        AlertDialog(onDismissRequest = { deleteCard = false },
            title = { Text("Delete Card") },
            confirmButton = {
                TextButton(onClick = { if (onDelete != null) onDelete() }) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { deleteCard = false }) {
                    Text("Cancel")
                }
            })
    }
}