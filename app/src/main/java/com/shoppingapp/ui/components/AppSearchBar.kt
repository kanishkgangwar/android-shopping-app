package com.shoppingapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shoppingapp.R
import com.shoppingapp.ui.theme.Dimens

@Composable
fun AppSearchBar(
    navController: NavHostController,
    query: String = "",
    onQueryChange: (String) -> Unit = {},
    onClick: (() -> Unit)? = null,
    onSearch: (() -> Unit)? = null
) {
    val focusManager = LocalFocusManager.current

    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(modifier = Modifier.weight(1f),
            tonalElevation = 4.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            TextField(
                value = query,
                onValueChange = onQueryChange,
                readOnly = onClick != null,
                enabled = true,
                singleLine = true,
                placeholder = { Text("Search") },
                leadingIcon = { Icon(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    focusManager.clearFocus()
                    if (onSearch != null) { onSearch() }
                }),
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { if (it.isFocused && onClick != null) { onClick() } }
            )
        }
        Surface(modifier = Modifier.size(Dimens.GeneralSize),
            tonalElevation = 4.dp,
            shape = RoundedCornerShape(16.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(painter = painterResource(id = R.drawable.tune),
                    contentDescription = null,
                    modifier = Modifier.size(Dimens.IconMedium)
                )
            }
        }
    }
}