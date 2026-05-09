package com.shoppingapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.screens.address.AddressScreen
import com.shoppingapp.ui.screens.address.AddressViewModel
import com.shoppingapp.ui.screens.cart.CartScreen
import com.shoppingapp.ui.screens.cart.CartViewModel
import com.shoppingapp.ui.screens.checkout.CheckOutViewModel
import com.shoppingapp.ui.screens.checkout.main.CheckOutScreen
import com.shoppingapp.ui.screens.checkout.payment.PaymentScreen
import com.shoppingapp.ui.screens.discover.DiscoverViewModel
import com.shoppingapp.ui.screens.discover.main.DiscoverScreen
import com.shoppingapp.ui.screens.discover.search.SearchScreen
import com.shoppingapp.ui.screens.entry.EntryViewModel
import com.shoppingapp.ui.screens.entry.login.LoginScreen
import com.shoppingapp.ui.screens.entry.signup.SignUpScreen
import com.shoppingapp.ui.screens.home.main.HomeScreen
import com.shoppingapp.ui.screens.home.HomeViewModel
import com.shoppingapp.ui.screens.notifications.NotificationsScreen
import com.shoppingapp.ui.screens.product.ProductViewModel
import com.shoppingapp.ui.screens.product.detail.ProductDetailScreen
import com.shoppingapp.ui.screens.product.main.ProductListScreen
import com.shoppingapp.ui.screens.profile.main.ProfileScreen
import com.shoppingapp.ui.screens.profile.main.ProfileViewModel
import com.shoppingapp.ui.screens.profile.settings.ProfileSettingsScreen
import com.shoppingapp.ui.screens.profile.settings.ProfileSettingsViewModel
import com.shoppingapp.ui.screens.settings.SettingsScreen
import com.shoppingapp.ui.screens.settings.SettingsViewModel
import com.shoppingapp.ui.screens.voucher.VoucherScreen
import com.shoppingapp.ui.screens.wishlist.WishListScreen
import com.shoppingapp.ui.screens.wishlist.WishListViewModel
import com.shoppingapp.ui.theme.ShoppingAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_NO
        )
        enableEdgeToEdge()
        setContent {
            ShoppingAppTheme {
                val navController = rememberNavController()
                AppNavHost(navController)
            }
        }
    }
}

@SuppressLint(
    "UnrememberedGetBackStackEntry",
    "ContextCastToActivity",
    "ViewModelConstructorInComposable"
)
@Composable
fun AppNavHost(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = "signup"
    ) {
        composable("signup") {
            val viewModel: EntryViewModel = hiltViewModel()
            val mainViewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
            SignUpScreen(viewModel, mainViewModel, navController)
        }
        composable("login") {
            val viewModel: EntryViewModel = hiltViewModel()
            val mainViewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
            LoginScreen(viewModel, mainViewModel, navController)
        }
        composable("home") {
            val mainViewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(viewModel, mainViewModel, navController)
        }
        composable("wishlist") {
            val mainViewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
            val viewModel: WishListViewModel = hiltViewModel()
            WishListScreen(viewModel, mainViewModel, navController)
        }
        composable("discover") {
            val mainViewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
            val viewModel: DiscoverViewModel = hiltViewModel()
            DiscoverScreen(viewModel, mainViewModel, navController)
        }
        composable("search") {
            val viewModel: DiscoverViewModel = hiltViewModel()
            SearchScreen(viewModel, navController)
        }
        composable("profile") {
            val mainViewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
            val viewModel: ProfileViewModel = hiltViewModel()
            ProfileScreen(viewModel, mainViewModel, navController)
        }
        composable("product") {
            val mainViewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
            val products by mainViewModel.filteredProducts.collectAsState()
            ProductListScreen(products, mainViewModel, navController)
        }
        composable("productDetail/{productId}") { backStackEntry ->
            val viewModel: ProductViewModel = hiltViewModel()
            val mainViewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
            val productId = backStackEntry.arguments?.getString("productId")?.toInt()
            ProductDetailScreen(productId!!, viewModel, mainViewModel, navController)
        }
        composable("profileSettings") {
            val mainViewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
            val viewModel: ProfileSettingsViewModel = hiltViewModel()
            ProfileSettingsScreen(viewModel, mainViewModel, navController)
        }
        composable("settings") {
            val mainViewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
            val viewModel: SettingsViewModel = hiltViewModel()
            SettingsScreen(viewModel, mainViewModel, navController)
        }
        composable("cart") {
            val mainViewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
            val viewModel: CartViewModel = hiltViewModel()
            CartScreen(viewModel, mainViewModel, navController)
        }
        composable("checkout") {
            val viewModel: CheckOutViewModel = hiltViewModel()
            val mainViewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
            CheckOutScreen(viewModel, mainViewModel, navController)
        }
        composable("notification") {
            val mainViewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
            NotificationsScreen(mainViewModel, navController)
        }
        composable("voucher") {
            val mainViewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
            VoucherScreen(mainViewModel, navController)
        }
        composable("address") {
            val viewModel: AddressViewModel = hiltViewModel()
            val mainViewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
            AddressScreen(viewModel, mainViewModel, navController)
        }
        composable("payment") {
            val viewModel: CheckOutViewModel = hiltViewModel()
            val mainViewModel: MainViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
            PaymentScreen(viewModel, mainViewModel, navController)
        }
    }
}