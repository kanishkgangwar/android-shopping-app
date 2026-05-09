package com.shoppingapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import com.shoppingapp.ui.theme.CustomColor

@Composable
fun UnderlineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    modifier: Modifier = Modifier,
    readOnly: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(text = placeholderText,
                color = Color.Gray
            )
        },
        readOnly = readOnly,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = modifier
            .fillMaxWidth()
            .then(
                if(onClick != null ) {
                    Modifier.clickable { onClick() }
                } else Modifier
            ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,

            focusedIndicatorColor = Color(0xFFCCCCCC),
            unfocusedIndicatorColor = Color(0xFFDDDDDD),

            cursorColor = CustomColor.PrimaryColor
        )
    )
}