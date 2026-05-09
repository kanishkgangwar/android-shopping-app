package com.shoppingapp.ui.screens.checkout.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shoppingapp.R
import com.shoppingapp.ui.components.CreditCardUI
import com.shoppingapp.ui.components.PaymentMethod
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.screens.PaymentType
import com.shoppingapp.ui.screens.checkout.CheckOutViewModel
import com.shoppingapp.ui.theme.CustomColor
import com.shoppingapp.ui.theme.Dimens

@Composable
fun PaymentCheckOutContent(
    viewModel: CheckOutViewModel,
    mainViewModel: MainViewModel,
    navController: NavHostController,
    onContinue: () -> Unit
) {
    val subtotal by mainViewModel.subtotal.collectAsState()
    var selectedPayment by remember { mutableStateOf(PaymentType.CASH) }
    val savedCreditCard by viewModel.savedCreditCard.collectAsState(emptyList())

    Column {
        Text("STEP 1",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f))
        Text("Payment",
            fontSize = Dimens.TextLarge)
    }

    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium)
    ) {
        PaymentMethod(
            icon = R.drawable.credit_card,
            title = "Cash",
            isSelected = selectedPayment == PaymentType.CASH,
            onClick = { selectedPayment = PaymentType.CASH },
            modifier = Modifier.weight(1f)
        )
        PaymentMethod(
            icon = R.drawable.credit_card,
            title = "Credit Card",
            isSelected = selectedPayment == PaymentType.CARD,
            onClick = { selectedPayment = PaymentType.CARD },
            modifier = Modifier.weight(1f)
        )
    }

    if(selectedPayment == PaymentType.CARD) {
        Column(verticalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium)) {
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Choose your card",
                    fontSize = Dimens.TextLarge
                )
                Text(text = "Add new +",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                    modifier = Modifier.clickable { navController.navigate("payment") })
            }
            if (savedCreditCard.isNotEmpty()) {
                savedCreditCard.forEach { card ->
                    CreditCardUI(
                        card.cardHolderName,
                        card.cardNumber,
                        card.expiryDate
                    ) {
                        viewModel.deleteCreditCard(card.id)
                    }
                }
            }
        }
    }

    Card(elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(Dimens.PaddingLarge),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimens.PaddingLarge)
        ) {

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Shipping")
                Text("FreeShip")
            }

            HorizontalDivider()

            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Subtotal")
                Text("$ $subtotal")
            }

            Button(onClick = { onContinue() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.ButtonHeightMedium),
                colors = ButtonDefaults.buttonColors(CustomColor.PrimaryColor)
            ) {
                Text(text = "Place my order",
                    fontSize = Dimens.TextSemi,
                    color = Color.White)
            }
        }
    }
}