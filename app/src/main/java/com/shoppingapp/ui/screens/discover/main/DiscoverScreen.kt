package com.shoppingapp.ui.screens.discover.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.shoppingapp.R
import com.shoppingapp.ui.components.AppScaffold
import com.shoppingapp.ui.components.AppSearchBar
import com.shoppingapp.ui.components.BannerImage
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.screens.ScreenState
import com.shoppingapp.ui.screens.discover.DiscoverViewModel
import com.shoppingapp.ui.screens.discover.search.SearchScreen
import com.shoppingapp.ui.theme.Dimens

@Composable
fun DiscoverScreen(
    viewModel: DiscoverViewModel,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val query by mainViewModel.query.collectAsState()
    val scrollState = rememberScrollState()
    var contentState by remember { mutableStateOf(ScreenState.MENU) }

    AppScaffold(
        title = stringResource(id = if (contentState == ScreenState.MENU) R.string.discover_page else R.string.search_page),
        mainViewModel = mainViewModel,
        navController = navController,
        contentState = contentState,
        onBackClick = { contentState = ScreenState.MENU }
    ) { innerPadding ->

        Column(modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(state = scrollState),
        ) {
            Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Dimens.PaddingMedium),
                verticalArrangement = Arrangement.spacedBy(Dimens.PaddingLarge),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (contentState) {
                    ScreenState.MENU -> {
                        AppSearchBar(
                            navController = navController,
                            onClick = { contentState = ScreenState.BACK }
                        )

                        BannerImage("https://i.pinimg.com/736x/d6/e3/e5/d6e3e5b684955d789a046cccf8249bdb.jpg")
                        BannerImage("https://i.pinimg.com/736x/cd/e4/97/cde497357b6f0e55eb3feb2f2393d6fa.jpg")
                        BannerImage("https://i.pinimg.com/736x/44/73/f7/4473f72e4dfb0cdb70f7f6f67c08bcc6.jpg")
                        BannerImage("https://i.pinimg.com/736x/83/56/c9/8356c97b0acacfcd669a7d790cb3ae4d.jpg")
                    }
                    ScreenState.BACK -> {
                        AppSearchBar(
                            navController = navController,
                            query = query,
                            onQueryChange = { mainViewModel.updateQuery(it) },
                            onSearch = {
                                mainViewModel.filterBySearch(query)
                                viewModel.addQuery(query)
                                navController.navigate("product") }
                        )
                        SearchScreen(
                            viewModel = viewModel,
                            navController = navController,
                            onQuerySelected = { selectedQuery ->
                                mainViewModel.updateQuery(selectedQuery)

                                mainViewModel.filterBySearch(selectedQuery)
                                navController.navigate("product")
                            },
                        )
                    }
                }
            }
        }
    }
}