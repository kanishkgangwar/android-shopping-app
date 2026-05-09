package com.shoppingapp.ui.screens.product.detail

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.shoppingapp.R
import com.shoppingapp.data.local.entity.CartItemEntity
import com.shoppingapp.ui.components.ColorSelector
import com.shoppingapp.ui.components.Review
import com.shoppingapp.ui.components.ReviewDialog
import com.shoppingapp.ui.components.SizeSelector
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.screens.product.ProductViewModel
import com.shoppingapp.ui.theme.CustomColor
import com.shoppingapp.ui.theme.CustomShape
import com.shoppingapp.ui.theme.Dimens
import kotlinx.coroutines.delay

@Composable
fun ProductDetailScreen(
    productId: Int,
    viewModel: ProductViewModel,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val scrollState = rememberScrollState()

    val product = mainViewModel.allProducts
        .collectAsState()
        .value
        .find { it.id == productId }

    if (product == null) return

    val imageHeight = 500.dp
    val overlapRatio = 0.75f

    val actualSize = product.imageUrls.size
    val pagerState = rememberPagerState(
        initialPage = Int.MAX_VALUE / 2,
        pageCount = { Int.MAX_VALUE }
    )
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            pagerState.animateScrollToPage(
                pagerState.currentPage + 1,
                animationSpec = tween(durationMillis = 800)
            )
        }
    }

    var descriptionExpanded by remember { mutableStateOf(false) }
    var reviewsExpanded by remember { mutableStateOf(false) }
    var reviewsNum by viewModel.reviewsNum

    var showDialog by remember { mutableStateOf(false) }
    var reviewsList = viewModel.reviews.value

    Box(modifier = Modifier.fillMaxSize()) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
        ) { page ->
            val actualPage = page % actualSize
            AsyncImage(
                model = product.imageUrls[actualPage],
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Row(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            val current = pagerState.currentPage % actualSize

            repeat(actualSize) { index ->
                val isSelected = current == index

                Box(modifier = Modifier
                    .padding(Dimens.PaddingMinor)
                    .size(if (isSelected) Dimens.PaddingSmall else Dimens.PaddingMinor)
                    .clip(CircleShape)
                    .background(if (isSelected) Color.White else Color.White.copy(alpha = 0.5f)))
            }
        }

        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.TopStart)
                .zIndex(10f)
                .statusBarsPadding()
                .padding(Dimens.PaddingMedium)
                .clickable { navController.popBackStack() }
        )

        Column(modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(imageHeight * overlapRatio))

            Column(modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(Color.White)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(Dimens.PaddingLarge)
            ) {
                Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Dimens.PaddingLarge),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = product.title,
                        fontSize = Dimens.TextLarge,
                        fontWeight = FontWeight(500),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    Text(product.priceText,
                        fontSize = Dimens.TextLargest,
                        fontWeight = FontWeight(500)
                    )
                }

                HorizontalDivider()

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Color")
                        Spacer(modifier = Modifier.height(Dimens.PaddingSmall))
                        ColorSelector()
                    }
                    Column {
                        Text("Size")
                        Spacer(modifier = Modifier.height(Dimens.PaddingSmall))
                        SizeSelector()
                    }
                }

                HorizontalDivider()

                Column(modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()
                ) {
                    Row(modifier = Modifier
                            .fillMaxWidth()
                            .clickable { descriptionExpanded = !descriptionExpanded },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Description",
                            fontSize = Dimens.TextLarge,
                            fontWeight = FontWeight.Medium)

                        Icon(imageVector = if (descriptionExpanded)
                                Icons.Default.KeyboardArrowDown
                            else
                                Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "")
                    }
                    if (descriptionExpanded) {
                        Spacer(modifier = Modifier.height(Dimens.PaddingSmall))

                        Text(text = product.description,
                            fontSize = 14.sp,
                            lineHeight = 20.sp
                        )
                    }
                }

                HorizontalDivider()

                Column(modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    verticalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium)
                ) {
                    Row(modifier = Modifier
                            .fillMaxWidth()
                            .clickable { reviewsExpanded = !reviewsExpanded },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Reviews",
                            fontSize = Dimens.TextLarge,
                            fontWeight = FontWeight.Medium)

                        Icon(imageVector = if (reviewsExpanded)
                            Icons.Default.KeyboardArrowDown
                        else
                            Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = "")
                    }
                    if (reviewsExpanded) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(viewModel.currentRating.value,
                                fontSize = Dimens.TextLargest)
                            Spacer(modifier = Modifier.width(Dimens.PaddingSmall))
                            Text("OUT OF 5")
                        }
                        Row(modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("$reviewsNum Reviews")

                            Row(verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable { showDialog = true }
                            ) {
                                Text("WRITE A REVIEW")
                                Spacer(modifier = Modifier.width(Dimens.PaddingSmall))
                                Icon(painter = painterResource(id = R.drawable.edit),
                                    contentDescription = "",
                                    modifier = Modifier.size(Dimens.IconSmall))
                            }
                        }
                    }
                    if (showDialog) {
                        ReviewDialog(
                            onDismiss = { showDialog = false },
                            onSubmit = { rating, comment ->
                                viewModel.addReview(Review(rating, comment))
                            }
                        )
                    }
                    Column {
                        reviewsList.forEach {
                            Card(modifier = Modifier.fillMaxWidth(),
                                border = BorderStroke(1.dp, CustomColor.PrimaryColor),
                                colors = CardDefaults.cardColors(Color.Transparent)
                            ) {
                                Column(modifier = Modifier.padding(Dimens.PaddingSmall)) {
                                    Row {
                                        Text("Rating",
                                            fontSize = Dimens.TextMedium,
                                            fontWeight = FontWeight(500))
                                        Text(it.rating)
                                    }
                                    Row {
                                        Text("Comment",
                                            fontSize = Dimens.TextMedium,
                                            fontWeight = FontWeight(500))
                                        Text(it.comment)
                                    }
                                }
                            }
                        }
                    }
                }
                HorizontalDivider()
            }

            Button(
                onClick = {
                    mainViewModel.addCartItem(CartItemEntity(product.id, 1))
                    navController.navigate("cart") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Dimens.PaddingHuge)
                    .height(Dimens.ButtonHeightLarge)
                    .clip(RoundedCornerShape(Dimens.ButtonsCornerSmall)),
                shape = CustomShape.rectangle,
                colors = ButtonDefaults.buttonColors(CustomColor.PrimaryColor)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = R.drawable.shopping_bag),
                        contentDescription = "")
                    Spacer(modifier = Modifier.width(Dimens.PaddingSmall))
                    Text("Add to Cart")
                }
            }
        }
    }
}