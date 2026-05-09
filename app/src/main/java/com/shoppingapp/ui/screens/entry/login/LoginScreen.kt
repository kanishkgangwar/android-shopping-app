package com.shoppingapp.ui.screens.entry.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shoppingapp.R
import com.shoppingapp.ui.components.SectionHeader
import com.shoppingapp.ui.components.UnderlineTextField
import com.shoppingapp.ui.screens.MainViewModel
import com.shoppingapp.ui.screens.entry.EntryViewModel
import com.shoppingapp.ui.theme.Dimens

@Composable
fun LoginScreen(
    viewModel: EntryViewModel,
    mainViewModel: MainViewModel,
    navController: NavHostController
) {
    val scrollState = rememberScrollState()

    var email by viewModel::email
    var password by viewModel::password

    val error by viewModel.error.collectAsState()
    val authSuccess by viewModel.authSuccess.collectAsState()

    LaunchedEffect(authSuccess) {
        if (authSuccess) navController.navigate("home")
    }

    Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
            .padding(top = 78.dp, start = Dimens.PaddingLarge, end = Dimens.PaddingLarge),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(Dimens.PaddingLarge)
    ) {
        SectionHeader("Log into your account")

        Spacer(modifier = Modifier.height(Dimens.PaddingLarge))

        UnderlineTextField(
            value = email,
            onValueChange = { email = it },
            placeholderText = "Email address"
        )
        UnderlineTextField(
            value = password,
            onValueChange = { password = it },
            placeholderText = "Password"
        )

        error?.let {
            Text(text = it,
                color = Color.Red
            )
        }

        Button(onClick = { viewModel.loginUser(mainViewModel) },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSurface),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = Dimens.PaddingLarge)
                .clip(RoundedCornerShape(Dimens.ButtonsCornerSmall))
        ) { Text(text = "LOGIN",
                modifier = Modifier
                    .padding(Dimens.PaddingSmall)) }

        Text(text = "or log in with",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(id = R.drawable.facebook_foreground),
                contentDescription = "",
                modifier = Modifier.size(Dimens.IconLargest)
            )
            Icon(painter = painterResource(id = R.drawable.apple_foreground),
                contentDescription = "",
                modifier = Modifier.size(Dimens.IconLargest)
            )
            Icon(painter = painterResource(id = R.drawable.google_foreground),
                contentDescription = "",
                modifier = Modifier.size(Dimens.IconLargest)
            )
        }

        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Don't have an account?")

            Spacer(modifier = Modifier.width(Dimens.PaddingSmall))

            Text(text = "Sign Up",
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    navController.navigate("signup")
                })
        }
    }
}