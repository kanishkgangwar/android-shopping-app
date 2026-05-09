package com.shoppingapp.ui.screens.checkout.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.shoppingapp.R
import com.shoppingapp.ui.theme.CustomColor
import com.shoppingapp.ui.theme.Dimens

@Composable
fun CompleteCheckOutContent(onContinue: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(60.dp)
    ) {
        Text("Order Completed",
            fontSize = Dimens.TextLarge,
            modifier = Modifier.align(Alignment.Start))

        Icon(painter = painterResource(id = R.drawable.order_complete),
            contentDescription = "",
            modifier = Modifier.size(120.dp))

        Text(text = "Thank you for your purchase. You can view your order in ‘My Orders’ section.",
            textAlign = TextAlign.Center)

        Button(onClick = { onContinue() },
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimens.ButtonHeightMedium),
            colors = ButtonDefaults.buttonColors(CustomColor.PrimaryColor)
        ) {
            Text(text = "Continue shopping",
                fontSize = Dimens.TextSemi,
                color = Color.White)
        }
    }
}