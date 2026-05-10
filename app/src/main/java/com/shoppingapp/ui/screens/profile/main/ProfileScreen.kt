package com.shoppingapp.ui.screens.profile.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shoppingapp.R
import com.shoppingapp.ui.components.AppScaffold
import com.shoppingapp.ui.components.ProfileOptionItem
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.theme.Dimens

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val menuItems = listOf(
        MenuItem(R.drawable.location, "Address", "address"),
        MenuItem(R.drawable.payment, "Payment method", "payment"),
        MenuItem(R.drawable.confirmation_number, "Voucher", "voucher"),
        MenuItem(R.drawable.favorite, "My Wishlist", "wishlist"),
        MenuItem(R.drawable.star, "Rate this app", ""),
        MenuItem(R.drawable.logout, "Log out", "signup")
    )

    val currentUser by mainViewModel.currentUser.collectAsState()

    AppScaffold(
        title = stringResource(id = R.string.profile_page),
        mainViewModel = mainViewModel,
        navController = navController
    ) { innerPadding ->

        LazyColumn(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(horizontal = Dimens.PaddingLarge),
            verticalArrangement = Arrangement.spacedBy(Dimens.PaddingHuge),
        ) {
            item {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(painter = painterResource(id = R.drawable.profile),
                            contentDescription = "",
                            modifier = Modifier.size(90.dp))

                        Spacer(modifier = Modifier.width(Dimens.PaddingLarge))

                        Column {
                            Text(text = currentUser?.let {
                                    "${it.firstName} ${it.lastName}"
                                } ?: "Guest User")

                            Spacer(modifier = Modifier.height(Dimens.PaddingSmall))

                            Text(text = currentUser?.email ?: "guest@example.com")
                        }
                    }
                    Icon(painter = painterResource(id = R.drawable.settings),
                        contentDescription = "",
                        modifier = Modifier.clickable { navController.navigate("profileSettings") })
                }
            }
            item {
                Card(modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(modifier = Modifier.padding(vertical = 8.dp)) {
                        menuItems.forEachIndexed { index, item ->

                            ProfileOptionItem(
                                icon = item.icon,
                                title = item.title,
                                path = item.path,
                                navController = navController
                            )

                            if (index != menuItems.lastIndex) {
                                HorizontalDivider(
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    thickness = 0.8.dp,
                                    color = Color.LightGray.copy(alpha = 0.6f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}