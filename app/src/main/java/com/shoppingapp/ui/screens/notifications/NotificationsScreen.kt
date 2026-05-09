package com.shoppingapp.ui.screens.notifications

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shoppingapp.R
import com.shoppingapp.ui.components.AppScaffold
import com.shoppingapp.ui.components.NotificationCard
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.screens.ScreenState
import com.shoppingapp.ui.theme.Dimens

@Composable
fun NotificationsScreen(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val scrollState = rememberScrollState()

    AppScaffold(
        title = stringResource(id = R.string.home_page),
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
                NotificationCard(
                    title = "Good morning! Get 20% Voucher",
                    desc = "Summer sale up to 20% off. Limited voucher. Get now!! 😜"
                )

                NotificationCard(
                    title = "Special offer just for you",
                    desc = "New Autumn Collection 30% off"
                )

                NotificationCard(
                    title = "Holiday sale 50%",
                    desc = "Tap here to get 50% voucher."
                )
                Spacer(modifier = Modifier.height(Dimens.PaddingHuge))
            }
        }
    }
}