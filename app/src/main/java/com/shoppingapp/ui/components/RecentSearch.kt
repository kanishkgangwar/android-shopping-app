package com.shoppingapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.shoppingapp.R
import com.shoppingapp.ui.theme.Dimens

@Composable
fun RecentSearch(
    search: String,
    onQueryClick: (() -> Unit)? = null,
    onIconClick: () -> Unit
) {
    Card {
        Row(modifier = Modifier.padding(Dimens.PaddingSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(search,
                modifier = Modifier.clickable {
                    if (onQueryClick != null) { onQueryClick() }
                })
            Spacer(modifier = Modifier.width(Dimens.PaddingMedium))
            Icon(painter = painterResource(id = R.drawable.close),
                contentDescription = "",
                modifier = Modifier
                    .size(Dimens.IconSmall)
                    .clickable { onIconClick() })
        }
    }
}