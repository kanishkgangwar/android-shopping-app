package com.shoppingapp.ui.screens.voucher

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.shoppingapp.R
import com.shoppingapp.ui.components.AppScaffold
import com.shoppingapp.ui.components.CouponCard
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.screens.ScreenState
import com.shoppingapp.ui.theme.Dimens

@Composable
fun VoucherScreen(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val scrollState = rememberScrollState()

    AppScaffold(
        title = stringResource(id = R.string.voucher_page),
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
                verticalArrangement = Arrangement.spacedBy(Dimens.PaddingLarge)
            ) {
                CouponCard(
                    discount = "50%",
                    title = "Black Friday",
                    desc = "Sale off 50%",
                    code = "fridaysale",
                    expiry = "20\nDec",
                )

                CouponCard(
                    discount = "30%",
                    title = "Holiday Sale",
                    desc = "Sale off 30%",
                    code = "holiday30",
                    expiry = "22\nDec",
                )

                CouponCard(
                    discount = "20%",
                    title = "First order",
                    desc = "20% off your first order",
                    code = "welcome",
                    expiry = "28\nDec",
                )
            }
        }
    }
}