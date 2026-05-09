package com.shoppingapp.ui.screens.discover.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.shoppingapp.R
import com.shoppingapp.ui.components.RecentSearch
import com.shoppingapp.ui.screens.discover.DiscoverViewModel
import com.shoppingapp.ui.theme.Dimens

@Composable
fun SearchScreen(
    viewModel: DiscoverViewModel,
    navController: NavHostController,
    onQuerySelected: ((String) -> Unit)? = null
) {
    val allQuery = viewModel.allQuery.collectAsState(emptyList())

    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Recent Searches",
            fontWeight = FontWeight(500),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
        Icon(painter = painterResource(id = R.drawable.delete),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            modifier = Modifier.clickable { viewModel.deleteAllQueries() })
    }
    FlowRow(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingSmall),
        verticalArrangement = Arrangement.spacedBy(Dimens.PaddingSmall)
    ) {
        allQuery.value.forEach {
            RecentSearch(
                search = it.query,
                onQueryClick = {
                    if (onQuerySelected != null) { onQuerySelected(it.query) }
                },
                onIconClick = { viewModel.deleteQueryById(it.id) })
        }
    }
}