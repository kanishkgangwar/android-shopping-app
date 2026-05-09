package com.shoppingapp.ui.screens.product.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.shoppingapp.R
import com.shoppingapp.ui.components.AppScaffold
import com.shoppingapp.ui.components.AppSearchBar
import com.shoppingapp.ui.components.FilterButton
import com.shoppingapp.ui.components.ProductCard
import com.shoppingapp.ui.components.ProductUiModel
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.theme.Dimens

@Composable
fun ProductListScreen(
    products: List<ProductUiModel>,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val numProducts = products.size
    val query by mainViewModel.query.collectAsState()

    AppScaffold(
        title = stringResource(id = R.string.productList_page),
        mainViewModel = mainViewModel,
        navController = navController,
        onBackClick = { navController.navigateUp() }
    ) { innerPadding ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            Column(modifier = Modifier.padding(Dimens.PaddingMedium),
                verticalArrangement = Arrangement.spacedBy(Dimens.PaddingLarge)
            ) {
                
                AppSearchBar(
                    navController = navController,
                    query = query,
                    onQueryChange = { mainViewModel.updateQuery(it) },
                    onSearch = {
                        mainViewModel.filterBySearch(query)
                        navController.navigate("product")
                    },
                )
                
                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Found",
                            fontSize = Dimens.TextLarge)
                        Text("$numProducts Results",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                    FilterButton {
                        // TODO: filter UI
                    }
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingLarge)
                ) {
                    items(products) { product ->
                        ProductCard(product) { navController.navigate("productDetail/${product.id}") }
                    }
                }
            }
        }
    }
}