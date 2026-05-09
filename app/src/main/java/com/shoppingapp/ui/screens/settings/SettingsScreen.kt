package com.shoppingapp.ui.screens.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.shoppingapp.R
import com.shoppingapp.ui.components.AppScaffold
import com.shoppingapp.ui.screens.MainViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    AppScaffold(
        title = stringResource(id = R.string.settings_page),
        mainViewModel = mainViewModel,
        navController = navController
    ) {

    }
}