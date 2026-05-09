package com.shoppingapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.screens.ScreenState
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AppScaffold(
    title: String,
    mainViewModel: MainViewModel,
    navController: NavHostController,
    contentState: ScreenState? = null,
    onBackClick: (() -> Unit)? = null,
    isTransparent: Boolean = false,
    content: @Composable (PaddingValues) -> Unit
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: "home"

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { ModalDrawerSheet { Burger(mainViewModel, navController) } }
    ) {
        Scaffold(
            containerColor = if (isTransparent) Color.Transparent else MaterialTheme.colorScheme.background,
            topBar = { Top(title = title,
                    navController = navController,
                    currentRoute = currentRoute,
                    onBurgerClick = { scope.launch { drawerState.open() } },
                    contentState = contentState,
                    onBackClick = onBackClick ) },
            bottomBar = { Bottom(navController, currentRoute) }
        ) {
            innerPadding ->
            content(innerPadding)
        }
    }
}