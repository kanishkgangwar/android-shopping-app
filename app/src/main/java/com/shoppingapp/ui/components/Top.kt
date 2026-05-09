package com.shoppingapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.shoppingapp.ui.screens.ScreenState
import com.shoppingapp.ui.theme.Dimens

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Top(
    title: String,
    navController: NavHostController,
    currentRoute: String,
    onBurgerClick: () -> Unit,
    contentState: ScreenState? = null,
    onBackClick: (() -> Unit)? = null
) {
    val stateBack = contentState == ScreenState.BACK

    CenterAlignedTopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(
                onClick = { if (stateBack) onBackClick?.invoke() else onBurgerClick() }
            ) {
                Icon(imageVector = if (stateBack) Icons.AutoMirrored.Filled.KeyboardArrowLeft else Icons.Default.Menu,
                    contentDescription = ""
                )
            }
        },
        actions = {
            Icon(Icons.Default.Notifications,
                contentDescription = "",
                modifier = Modifier
                    .padding(end = Dimens.PaddingSmall)
                    .size(Dimens.IconLarge)
                    .clickable { navController.navigate("notification") })
        }
    )
}