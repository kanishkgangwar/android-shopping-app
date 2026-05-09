package com.shoppingapp.ui.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shoppingapp.R
import com.shoppingapp.ui.components.AppScaffold
import com.shoppingapp.ui.components.CartItemCard
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.screens.ScreenState
import com.shoppingapp.ui.theme.CustomColor
import com.shoppingapp.ui.theme.Dimens

@Composable
fun CartScreen(
    viewModel: CartViewModel,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val scrollState = rememberScrollState()

    val cartItems by mainViewModel.allCartItem.collectAsState(emptyList())
    val allProducts by mainViewModel.allProducts.collectAsState(emptyList())

    val productMap = allProducts.associateBy { it.id }

    val subtotal = cartItems.sumOf { item ->
        val product = productMap[item.productId]
        (product?.price ?: 0) * item.quantity
    }

    AppScaffold(
        title = stringResource(id = R.string.cart_page),
        mainViewModel = mainViewModel,
        navController = navController,
        contentState = ScreenState.BACK,
        onBackClick = { navController.navigateUp() }
    ) { innerPadding ->

        if (subtotal == 0 || cartItems.isEmpty()) {
            Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Your cart feels lonely 🛒",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        } else {
            Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(scrollState)
                    .padding(horizontal = Dimens.PaddingLarge),
                verticalArrangement = Arrangement.spacedBy(Dimens.PaddingHuge)
            ) {
                cartItems.forEach { item ->
                    val product = allProducts.find { it.id == item.productId }

                    if (product != null) {
                        CartItemCard(product, item.quantity, mainViewModel)
                    }
                }

                Card(modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(6.dp),
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

                        Button(onClick = { navController.navigate("checkout") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(Dimens.ButtonHeightMedium),
                            colors = ButtonDefaults.buttonColors(CustomColor.PrimaryColor)
                        ) {
                            Text(text = "Proceed to Checkout",
                                fontSize = Dimens.TextSemi,
                                color = Color.White)
                        }
                    }
                }
            }
        }
    }
}