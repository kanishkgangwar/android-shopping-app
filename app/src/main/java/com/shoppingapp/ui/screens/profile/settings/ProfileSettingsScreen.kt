package com.shoppingapp.ui.screens.profile.settings

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shoppingapp.R
import com.shoppingapp.ui.components.AppScaffold
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.theme.CustomColor
import com.shoppingapp.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileSettingsScreen(
    viewModel: ProfileSettingsViewModel,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    var firstName by viewModel::firstName
    var lastName by viewModel::lastName
    var email by viewModel::email
    var phone by viewModel::phone

    val gender = mainViewModel.gender
    var expanded by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf(gender.first()) }

    val currentUser by mainViewModel.currentUser.collectAsState()

    LaunchedEffect(currentUser) {
        currentUser?.let { user ->
            viewModel.loadUser(user)
        }
    }

    AppScaffold(
        title = stringResource(id = R.string.profileSettings_page),
        mainViewModel = mainViewModel,
        navController = navController
    ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding)) {
            Spacer(modifier = Modifier.height(Dimens.PaddingLarge))
            Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = Dimens.PaddingLarge),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimens.PaddingHuge)
            ) {
                Icon(painter = painterResource(id = R.drawable.profile),
                    contentDescription = "",
                    modifier = Modifier
                        .size(Dimens.IconHuge),
                    tint = Color.Black
                )

                Column(verticalArrangement = Arrangement.spacedBy(Dimens.PaddingLarge)) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        TextField(
                            value = firstName,
                            onValueChange = { firstName = it },
                            label = { Text("First Name") },
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Gray,
                                unfocusedIndicatorColor = Color.LightGray,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            )
                        )
                        Spacer(modifier = Modifier.width(Dimens.PaddingMedium))
                        TextField(
                            value = lastName,
                            onValueChange = { lastName = it },
                            label = { Text("Last Name") },
                            singleLine = true,
                            modifier = Modifier.weight(1f),
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Gray,
                                unfocusedIndicatorColor = Color.LightGray,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            )
                        )
                    }
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Gray,
                            unfocusedIndicatorColor = Color.LightGray,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        )
                    )
                    Row {
                        ExposedDropdownMenuBox(
                            expanded = expanded,
                            onExpandedChange = { expanded = !expanded },
                            modifier = Modifier.weight(0.4f)
                        ) {
                            TextField(
                                value = selectedGender,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text("Gender") },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                                },
                                modifier = Modifier.menuAnchor(),
                                colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = Color.Gray,
                                    unfocusedIndicatorColor = Color.LightGray,
                                    focusedContainerColor = Color.Transparent,
                                    unfocusedContainerColor = Color.Transparent
                                )
                            )

                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false }
                            ) {
                                gender.forEach { option ->
                                    DropdownMenuItem(
                                        text = { Text(option) },
                                        onClick = {
                                            selectedGender = option
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.width(Dimens.PaddingMedium))
                        TextField(
                            value = phone,
                            onValueChange = { phone = it },
                            label = { Text("Phone") },
                            singleLine = true,
                            modifier = Modifier.weight(0.7f),
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Gray,
                                unfocusedIndicatorColor = Color.LightGray,
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent
                            )
                        )
                    }
                }
                Button(onClick = {
                    currentUser?.let { user ->

                    viewModel.saveProfile(
                        currentUser = user,
                        mainViewModel = mainViewModel
                    )

                    navController.navigate("home")
                } },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = Dimens.PaddingHuge)
                        .clip(RoundedCornerShape(Dimens.ButtonsCornerSmall))
                ) {
                    Text("Save change",
                        modifier = Modifier.padding(Dimens.PaddingSmall))
                }
            }
        }
    }
}