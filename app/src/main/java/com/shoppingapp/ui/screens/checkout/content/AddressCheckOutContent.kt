package com.shoppingapp.ui.screens.checkout.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import com.shoppingapp.ui.components.UnderlineTextField
import com.shoppingapp.ui.theme.CustomColor
import com.shoppingapp.ui.theme.Dimens

@Composable
fun AddressCheckOutContent(onContinue: () -> Unit) {

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var street by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var zip by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }

    var couponCode by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    var isChecked by remember { mutableStateOf(false) }

    Column {
        Text("STEP 1",
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f))
        Text("Shipping",
            fontSize = Dimens.TextLarge)
    }
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium)
    ) {
        UnderlineTextField(
            value = firstName,
            onValueChange = { firstName = it },
            placeholderText = "First name *"
        )

        UnderlineTextField(
            value = lastName,
            onValueChange = { lastName = it },
            placeholderText = "Last name *"
        )

        UnderlineTextField(
            value = country,
            onValueChange = { country = it },
            placeholderText = "Country *"
        )

        UnderlineTextField(
            value = street,
            onValueChange = { street = it },
            placeholderText = "Street name *"
        )

        UnderlineTextField(
            value = city,
            onValueChange = { city = it },
            placeholderText = "City *"
        )

        UnderlineTextField(
            value = state,
            onValueChange = { state = it },
            placeholderText = "State / Province"
        )

        UnderlineTextField(
            value = zip,
            onValueChange = { zip = it },
            placeholderText = "Zip-code *",
            keyboardType = KeyboardType.Number
        )

        UnderlineTextField(
            value = phone,
            onValueChange = { phone = it },
            placeholderText = "Phone number *",
            keyboardType = KeyboardType.Phone
        )
    }
    Column(verticalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium)) {
        Text("Coupon code",
            fontSize = Dimens.TextLarge)

        Box(modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.TextFieldHeightLarge)
            .background(
                color = CustomColor.AlternateColor,
                shape = RoundedCornerShape(Dimens.CardsCornerSmall)
            )
            .padding(Dimens.PaddingMedium)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                BasicTextField(
                    value = couponCode,
                    onValueChange = { couponCode = it },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .onFocusChanged {
                            isFocused = it.isFocused
                        },
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(color = Color.Black),
                    cursorBrush = SolidColor(CustomColor.PrimaryColor),
                    decorationBox = { innerTextField ->
                        Box(contentAlignment = Alignment.CenterStart) {
                            if (!isFocused) {
                                Text(text = "Have a code? type it here...",
                                    color = Color.Gray
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                Text(text = "Validate",
                    color = Color.Gray,
                    modifier = Modifier
                        .clickable {  }
                        .padding(start = Dimens.PaddingMedium)
                )
            }
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium)) {
        Text("Billing Address",
            fontSize = Dimens.TextLarge)

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
            Text("Copy address data from shipping")
        }
    }

    Button(onClick = { onContinue() },
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimens.ButtonHeightMedium),
        colors = ButtonDefaults.buttonColors(CustomColor.PrimaryColor)
    ) {
        Text(text = "Continue to payment",
            fontSize = Dimens.TextSemi,
            color = Color.White)
    }
}