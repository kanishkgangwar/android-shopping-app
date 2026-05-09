package com.shoppingapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.shoppingapp.R
import com.shoppingapp.ui.theme.Dimens

@Composable
fun AddressCard(
    title: String,
    address: String,
    isSelected: Boolean,
    icon: Int,
    onSelect: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() },
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Row(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium)
        ) {
            RadioButton(
                selected = isSelected,
                onClick = { onSelect() }
            )

            Icon(painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(Dimens.IconLarge))

            Column(modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Dimens.PaddingSmall)
            ) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "SEND TO",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray)

                        Spacer(modifier = Modifier.height(Dimens.PaddingMinor))

                        Text(text = title,
                            style = MaterialTheme.typography.titleMedium)
                    }
                    Spacer(modifier = Modifier.height(Dimens.PaddingMinor))

                    Row {
                        Text(text = "Edit",
                            color = Color.Red,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.clickable { onEdit() }
                        )
                        Spacer(modifier = Modifier.width(Dimens.PaddingSmall))

                        Icon(painter = painterResource(id = R.drawable.delete),
                            contentDescription = "",
                            modifier = Modifier
                                .size(Dimens.IconSemi)
                                .clickable { onDelete() })
                    }
                }

                Text(text = address,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray)
            }
        }
    }
}