package com.shoppingapp.ui.screens.home.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.shoppingapp.R
import com.shoppingapp.ui.components.AppScaffold
import com.shoppingapp.ui.components.CategoryItem
import com.shoppingapp.ui.components.ProductItem
import com.shoppingapp.ui.components.SectionHeader
import com.shoppingapp.ui.screens.CategoryItemState
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.screens.home.Category
import com.shoppingapp.ui.screens.home.HomeViewModel
import com.shoppingapp.ui.screens.home.Product
import com.shoppingapp.ui.screens.home.categories.CategoryContent
import com.shoppingapp.ui.theme.Dimens

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    mainViewModel: MainViewModel,
    navController: NavHostController
){
    val scrollState = rememberScrollState()
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = Int.MAX_VALUE / 2
    )

    val featureProducts = listOf(
        Product("https://i.pinimg.com/736x/cc/d0/45/ccd045f7f7bb35f44db81f392dbdadc3.jpg", "LongSleeve Dress", "$45.00"),
        Product("https://i.pinimg.com/736x/f5/5e/21/f55e2120662ebc66c1417e9e58e77eb8.jpg", "SportWear Dress", "$38.00"),
        Product("https://i.pinimg.com/1200x/e7/63/28/e7632869203cde90bfcb396926de34c0.jpg", "SweatPants", "$69.50"),
        Product("https://i.pinimg.com/1200x/e7/2e/50/e72e5042976414812c6fae2c5a051ad5.jpg", "ShortSleeve Dress", "$35.00")
    )

    val recommendedProducts = listOf(
        Product("https://i.pinimg.com/736x/cc/d0/45/ccd045f7f7bb35f44db81f392dbdadc3.jpg", "LongSleeve Dress", "$45.00"),
        Product("https://i.pinimg.com/736x/f5/5e/21/f55e2120662ebc66c1417e9e58e77eb8.jpg", "SportWear Dress", "$38.00"),
        Product("https://i.pinimg.com/1200x/e7/63/28/e7632869203cde90bfcb396926de34c0.jpg", "SweatPants", "$69.50"),
        Product("https://i.pinimg.com/1200x/e7/2e/50/e72e5042976414812c6fae2c5a051ad5.jpg", "ShortSleeve Dress", "$35.00")
    )

    val categories = listOf(
        Category(R.drawable.male, "Male", CategoryItemState.MALE),
        Category(R.drawable.female, "Female", CategoryItemState.FEMALE),
        Category(R.drawable.coffee_maker, "Accessories", CategoryItemState.ACCESSORIES),
        Category(R.drawable.grass, "Groceries", CategoryItemState.GROCERIES)
    )
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    AppScaffold(
        title = stringResource(id = R.string.home_page),
        mainViewModel = mainViewModel,
        navController = navController
    ) { innerPadding ->

        Column(modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(state = scrollState)
                .padding(horizontal = Dimens.PaddingLarge),
            verticalArrangement = Arrangement.spacedBy(Dimens.PaddingHuge)
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                items(categories) { category ->
                    CategoryItem(
                        icon = category.icon,
                        label = category.label,
                        category = category.type,
                        selectedCategory = selectedCategory,
                        onClick = { viewModel.selectCategory(it) }
                    )
                }
            }

            when (selectedCategory) {
                CategoryItemState.MALE -> CategoryContent(
                    banner = "male_banner_url",
                    featureProducts = featureProducts,
                    recommendedProducts = recommendedProducts,
                    listState = listState
                )

                CategoryItemState.FEMALE -> CategoryContent(
                    banner = "female_banner_url",
                    featureProducts = featureProducts,
                    recommendedProducts = recommendedProducts,
                    listState = listState
                )

                CategoryItemState.ACCESSORIES -> CategoryContent(
                    banner = "accessories_banner_url",
                    featureProducts = featureProducts,
                    recommendedProducts = recommendedProducts,
                    listState = listState
                )

                CategoryItemState.GROCERIES -> CategoryContent(
                    banner = "grocery_banner_url",
                    featureProducts = featureProducts,
                    recommendedProducts = recommendedProducts,
                    listState = listState
                )
            }
            SectionHeader("Feature Products")
            LazyRow(modifier = Modifier.fillMaxWidth(),
                state = listState,
                horizontalArrangement = Arrangement.spacedBy(Dimens.ImageGapLargest)
            ) {
                items(Int.MAX_VALUE) { index ->
                    val item = featureProducts[index % featureProducts.size]
                    ProductItem(
                        productImageLink = item.image,
                        productName = item.name,
                        productPrice = item.price
                    )
                }
            }
        }
    }
}