package com.shoppingapp.ui.screens.address

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.shoppingapp.R
import com.shoppingapp.data.local.entity.DeliveryAddressEntity
import com.shoppingapp.ui.components.AddressCard
import com.shoppingapp.ui.components.AppScaffold
import com.shoppingapp.ui.components.UnderlineTextField
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.screens.ScreenState
import com.shoppingapp.ui.theme.CustomColor
import com.shoppingapp.ui.theme.Dimens

@Composable
fun AddressScreen(
    viewModel: AddressViewModel,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    var selectedAddressId by remember { mutableStateOf<Long?>(null) }
    var editingId by remember { mutableStateOf<Long?>(null) }

    var isEdit by remember { mutableStateOf(false) }
    var newAddress by remember { mutableStateOf(false) }

    var title by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    val savedAddress by viewModel.savedAddress.collectAsState(emptyList())

    LaunchedEffect(savedAddress) {
        if (savedAddress.isNotEmpty() && selectedAddressId == null) {
            selectedAddressId = savedAddress.first().id
        }
    }

    AppScaffold(
        title = stringResource(id = R.string.delivery_address_page),
        mainViewModel = mainViewModel,
        navController = navController,
        contentState = ScreenState.BACK,
        onBackClick = { navController.navigateUp() }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = Dimens.PaddingLarge)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Dimens.PaddingLargest)
        ) {
            savedAddress.forEach {
                AddressCard(
                    title = it.placeTitle,
                    address = it.placeAddress,
                    isSelected = selectedAddressId == it.id,
                    icon = R.drawable.home,
                    onSelect = { selectedAddressId = it.id },
                    onEdit = {
                        isEdit = true
                        title = it.placeTitle
                        address = it.placeAddress
                        editingId = it.id
                    },
                    onDelete = { viewModel.deleteAddressById(it.id) }
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            Button(onClick = { newAddress = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Dimens.PaddingHuge)
                    .height(Dimens.ButtonHeightMedium),
                colors = ButtonDefaults.buttonColors(CustomColor.PrimaryColor)
            ) {
                Text(text = if(savedAddress.isEmpty()) "Add address" else "Add new address",
                    fontSize = Dimens.TextSemi,
                    color = Color.White)
            }
        }
    }

    if (newAddress) {
        AlertDialog(
            onDismissRequest = { newAddress = false },
            title = { Text("Add new address") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium)) {
                    UnderlineTextField(
                        value = title,
                        onValueChange = { title = it },
                        placeholderText = "Enter place title"
                    )
                    UnderlineTextField(
                        value = address,
                        onValueChange = { address = it },
                        placeholderText = "Enter Address"
                    )
                    Spacer(modifier = Modifier.height(Dimens.PaddingSmall))
                }
            },
            dismissButton = {
                Button(onClick = { newAddress = false },
                    colors = ButtonDefaults.buttonColors(CustomColor.PrimaryColor)
                ) {
                    Text(text = "Cancel",
                        color = Color.White)
                }
            },
            confirmButton = {
                Button(onClick = {
                        viewModel.addNewAddress(DeliveryAddressEntity(placeTitle = title, placeAddress = address))
                        newAddress = false },
                    colors = ButtonDefaults.buttonColors(CustomColor.PrimaryColor)
                ) {
                    Text(text = "Confirm",
                        color = Color.White)
                }
            }
        )
    }

    if (isEdit) {
        AlertDialog(
            onDismissRequest = { isEdit = false },
            title = { Text("Add new address") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium)) {
                    UnderlineTextField(
                        value = title,
                        onValueChange = { title = it },
                        placeholderText = "Enter place title"
                    )
                    UnderlineTextField(
                        value = address,
                        onValueChange = { address = it },
                        placeholderText = "Enter Address"
                    )
                    Spacer(modifier = Modifier.height(Dimens.PaddingSmall))
                }
            },
            dismissButton = {
                Button(onClick = { isEdit = false },
                    colors = ButtonDefaults.buttonColors(CustomColor.PrimaryColor)
                ) {
                    Text(text = "Cancel",
                        color = Color.White)
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        editingId?.let {
                            viewModel.updateAddress(it, title, address)
                        }
                        isEdit = false },
                    colors = ButtonDefaults.buttonColors(CustomColor.PrimaryColor)
                ) {
                    Text(text = "Confirm",
                        color = Color.White)
                }
            }
        )
    }
}