package com.shoppingapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.shoppingapp.R
import com.shoppingapp.ui.theme.Dimens

@Composable
fun Bottom(
    navController: NavHostController,
    currentRoute: String
) {
    BottomAppBar(modifier = Modifier.height(Dimens.BottomBarHeight)) {

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(painter = painterResource(id = R.drawable.home),
                    contentDescription = "",
                    modifier = Modifier
                        .size(Dimens.IconMedium)
                        .alpha(
                            if(currentRoute == "home") 1f
                            else 0.6f
                        ))
            }
            IconButton(onClick = { navController.navigate("discover") }) {
                Icon(painter = painterResource(id = R.drawable.search),
                    contentDescription = "",
                    modifier = Modifier
                        .size(Dimens.IconMedium)
                        .alpha(
                            if(currentRoute == "discover") 1f
                            else 0.6f
                        ))
            }
            IconButton(onClick = { navController.navigate("cart") }) {
                Icon(painter = painterResource(id = R.drawable.shopping_bag),
                    contentDescription = "",
                    modifier = Modifier
                        .size(Dimens.IconMedium)
                        .alpha(
                            if(currentRoute == "cart") 1f
                            else 0.6f
                        ))
            }
            IconButton(onClick = { navController.navigate("profile") }) {
                Icon(painter = painterResource(id = R.drawable.person),
                    contentDescription = "",
                    modifier = Modifier
                        .size(Dimens.IconMedium)
                        .alpha(
                            if(currentRoute == "profile") 1f
                            else 0.6f
                        ))
            }
        }
    }
}