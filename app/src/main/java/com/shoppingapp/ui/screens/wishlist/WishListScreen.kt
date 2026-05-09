package com.shoppingapp.ui.screens.wishlist

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
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.screens.ScreenState
import com.shoppingapp.ui.theme.Dimens

@Composable
fun WishListScreen(
    viewModel: WishListViewModel,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val scrollState = rememberScrollState()

    AppScaffold(
        title = stringResource(id = R.string.wishList_page),
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

            }
        }
    }
}