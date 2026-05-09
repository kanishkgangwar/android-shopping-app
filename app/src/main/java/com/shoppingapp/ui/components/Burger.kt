package com.shoppingapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.shoppingapp.R
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.theme.Dimens
import com.shoppingapp.utils.ThemeToggle

@Composable
fun Burger(
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: "home"

    val currentUser by mainViewModel.currentUser.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 68.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(id = R.drawable.profile),
                contentDescription = "",
                modifier = Modifier.size(Dimens.IconHuge))

            Spacer(modifier = Modifier.width(Dimens.PaddingLarge))

            Column {
                Text(text = currentUser?.let {
                        "${it.firstName} ${it.lastName}"
                    } ?: "Guest User",
                    fontSize = Dimens.TextLarge)

                Spacer(modifier = Modifier.height(Dimens.PaddingSmall))

                Text(text = currentUser?.email ?: "guest@example.com",
                    fontSize = Dimens.TextMedium)
            }
        }
        LazyColumn(modifier = Modifier.padding(horizontal = Dimens.PaddingMedium)) {
            item { DrawerItem(
                burgerIcon = R.drawable.home,
                burgerPageName = "Homepage",
                isSelected = currentRoute == "home",
                navController = navController,
                path = "home",
            ) }
            item { DrawerItem(
                burgerIcon = R.drawable.search,
                burgerPageName = "Discover",
                isSelected = currentRoute == "discover",
                navController = navController,
                path = "discover"
            ) }
            item { DrawerItem(
                burgerIcon = R.drawable.shopping_bag,
                burgerPageName = "My Order",
                isSelected = currentRoute == "cart",
                navController = navController,
                path = "cart"
            ) }
            item { DrawerItem(
                burgerIcon = R.drawable.person,
                burgerPageName = "My profile",
                isSelected = currentRoute == "profile",
                navController = navController,
                path = "profile"
            ) }
        }
        Text("OTHER",
            modifier = Modifier.padding(horizontal = Dimens.PaddingLargest,
                vertical = Dimens.PaddingMedium),
            fontWeight = FontWeight(500),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
            letterSpacing = 0.8.sp)

        LazyColumn(modifier = Modifier.padding(horizontal = Dimens.PaddingMedium)) {
            item { DrawerItem(
                burgerIcon = R.drawable.settings,
                burgerPageName = "Settings",
                isSelected = currentRoute == "settings",
                navController = navController,
                path = "settings"
            ) }
            item { DrawerItem(
                burgerIcon = R.drawable.email,
                burgerPageName = "Support",
                isSelected = currentRoute == "support",
                navController = navController,
                path = "home"
            ) }
            item { DrawerItem(
                burgerIcon = R.drawable.info,
                burgerPageName = "About us",
                isSelected = currentRoute == "aboutUs",
                navController = navController,
                path = "home"
            ) }
        }
    }
}