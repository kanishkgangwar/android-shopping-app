package com.shoppingapp.ui.screens.checkout.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.shoppingapp.R
import com.shoppingapp.ui.components.AppScaffold
import com.shoppingapp.ui.screens.CheckOutState
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.screens.ScreenState
import com.shoppingapp.ui.screens.checkout.CheckOutViewModel
import com.shoppingapp.ui.screens.checkout.content.AddressCheckOutContent
import com.shoppingapp.ui.screens.checkout.content.CompleteCheckOutContent
import com.shoppingapp.ui.screens.checkout.content.PaymentCheckOutContent
import com.shoppingapp.ui.theme.Dimens

@Composable
fun CheckOutScreen(
    viewModel: CheckOutViewModel,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val scrollState = rememberScrollState()
    var checkoutState by remember { mutableStateOf(CheckOutState.ADDRESS) }

    AppScaffold(
        title = stringResource(id = R.string.checkout_page),
        mainViewModel = mainViewModel,
        navController = navController,
        contentState = ScreenState.BACK,
        onBackClick = { navController.navigateUp() }
    ) { innerPadding ->

        Column(modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(state = scrollState)
        ) {
            Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Dimens.PaddingLarge),
                verticalArrangement = Arrangement.spacedBy(Dimens.PaddingHuge)
            ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Icon(painter = painterResource(id = R.drawable.location),
                        contentDescription = "")
                    Icon(painter = painterResource(id = R.drawable.payment),
                        contentDescription = "")
                    Icon(painter = painterResource(id = R.drawable.check_circle),
                        contentDescription = "")
                }

                // Main content
                when (checkoutState) {
                    CheckOutState.ADDRESS -> {
                        AddressCheckOutContent { checkoutState = CheckOutState.PAYMENT }
                    }
                    CheckOutState.PAYMENT -> {
                        PaymentCheckOutContent(viewModel, mainViewModel, navController) {
                            checkoutState = CheckOutState.COMPLETE
                        }
                    }
                    CheckOutState.COMPLETE -> {
                        CompleteCheckOutContent { navController.navigate("home") }
                    }
                }
            }
        }
    }
}