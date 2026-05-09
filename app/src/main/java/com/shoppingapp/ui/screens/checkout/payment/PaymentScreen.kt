package com.shoppingapp.ui.screens.checkout.payment

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shoppingapp.R
import com.shoppingapp.data.local.entity.CreditCardEntity
import com.shoppingapp.ui.components.AppScaffold
import com.shoppingapp.ui.components.CreditCardUI
import com.shoppingapp.ui.components.UnderlineTextField
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.screens.ScreenState
import com.shoppingapp.ui.screens.checkout.CheckOutViewModel
import com.shoppingapp.ui.theme.CustomColor
import com.shoppingapp.ui.theme.Dimens
import java.util.Calendar

@SuppressLint("DefaultLocale")
@Composable
fun PaymentScreen(
    viewModel: CheckOutViewModel,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val savedCreditCard by viewModel.savedCreditCard.collectAsState(emptyList())
    val context = LocalContext.current

    var isNewCard by remember { mutableStateOf(false) }

    var cardHolderName by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var expires by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }

    var showExpiryPicker by remember { mutableStateOf(false) }

    val currentCal = Calendar.getInstance()
    var selectedMonth by remember { mutableStateOf(currentCal.get(Calendar.MONTH)) }
    var selectedYear by remember { mutableStateOf(currentCal.get(Calendar.YEAR)) }

    AppScaffold(
        title = stringResource(id = R.string.payment_page),
        mainViewModel = mainViewModel,
        navController = navController,
        contentState = ScreenState.BACK,
        onBackClick = { navController.navigateUp() }
    ) { innerPadding ->

        LazyColumn(modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = Dimens.PaddingLarge),
            verticalArrangement = Arrangement.spacedBy(Dimens.PaddingHuge)
        ) {
            item {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = Dimens.PaddingLarge),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Card Management",
                        fontSize = Dimens.TextLarge
                    )
                    Text(text = "Add card +",
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                        modifier = Modifier.clickable { isNewCard = true })
                }
            }

            if(savedCreditCard.isNotEmpty()) {
                items(savedCreditCard, key = { it.id }) { card ->
                    CreditCardUI(
                        card.cardHolderName,
                        card.cardNumber,
                        card.expiryDate
                    ) {
                        viewModel.deleteCreditCard(card.id)
                    }
                }
            }
            else {
                item {
                    CreditCardUI(cardHolderName, cardNumber, expires)
                }
            }

            if(isNewCard) {
                item {
                    Column(horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium)
                    ) {
                        UnderlineTextField(
                            value = cardHolderName,
                            onValueChange = { cardHolderName = it },
                            placeholderText = "Cardholder Name"
                        )
                        UnderlineTextField(
                            value = cardNumber,
                            onValueChange = { cardNumber = it },
                            placeholderText = "Card Number",
                            keyboardType = KeyboardType.Number
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium)) {
                            Column(modifier = Modifier
                                .weight(1f)
                                .padding(top = Dimens.PaddingMedium)
                                .clickable { showExpiryPicker = true }
                            ) { Text(text = expires.ifEmpty { "MM/YY" },
                                modifier = Modifier.padding(start = Dimens.PaddingMedium),
                                color = if (expires.isEmpty()) Color.Gray else Color.Black)

                                Spacer(modifier = Modifier.height(Dimens.PaddingMedium))

                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color(0xFFDDDDDD))
                                )
                            }

                            UnderlineTextField(
                                value = cvv,
                                onValueChange = { cvv = it },
                                placeholderText = "CVV",
                                keyboardType = KeyboardType.Number,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        Spacer(modifier = Modifier.height(Dimens.PaddingMedium))

                        Button(onClick = {
                            viewModel.addCreditCard(CreditCardEntity(cardHolderName = cardHolderName, cardNumber = cardNumber, expiryDate = expires, cvv = cvv))
                            isNewCard = false
                            Toast.makeText(context, "Card Added", Toast.LENGTH_SHORT).show() },

                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .height(Dimens.ButtonHeightMedium),
                            colors = ButtonDefaults.buttonColors(CustomColor.PrimaryColor)
                        ) {
                            Text(text = "Add card",
                                fontSize = Dimens.TextSemi,
                                color = Color.White)
                        }
                    }
                }
            }
        }
    }

    if (showExpiryPicker) {
        AlertDialog(
            onDismissRequest = { showExpiryPicker = false },

            confirmButton = {
                TextButton(onClick = {
                    val currentMonth = currentCal.get(Calendar.MONTH)
                    val currentYear = currentCal.get(Calendar.YEAR)

                    if (selectedYear > currentYear ||
                        (selectedYear == currentYear && selectedMonth >= currentMonth)
                    ) {
                        expires = String.format(
                            "%02d/%02d",
                            selectedMonth + 1,
                            selectedYear % 100
                        )
                        showExpiryPicker = false
                    } else {
                        // ❌ invalid (you can show toast/snack bar)
                    } }
                ) { Text("OK") }
            },

            dismissButton = {
                TextButton(onClick = { showExpiryPicker = false }) {
                    Text("Cancel")
                }
            },

            title = { Text("Select Expiry Date") },

            text = {
                Column {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3)
                    ) {
                        items(12) { month ->
                            val isSelected = month == selectedMonth

                            Text(text = String.format("%02d", month + 1),
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        if (isSelected) CustomColor.PrimaryColor else Color.Transparent
                                    )
                                    .clickable { selectedMonth = month }
                                    .padding(12.dp),
                                color = if (isSelected) Color.White else Color.Black
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(Dimens.PaddingMedium))

                    val currentYear = currentCal.get(Calendar.YEAR)

                    LazyRow {
                        items(15) { i ->
                            val year = currentYear + i
                            val isSelected = year == selectedYear

                            Text(text = year.toString(),
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        if (isSelected) CustomColor.PrimaryColor else Color.Transparent
                                    )
                                    .clickable { selectedYear = year }
                                    .padding(horizontal = 16.dp, vertical = 10.dp),
                                color = if (isSelected) Color.White else Color.Black
                            )
                        }
                    }
                }
            }
        )
    }
}