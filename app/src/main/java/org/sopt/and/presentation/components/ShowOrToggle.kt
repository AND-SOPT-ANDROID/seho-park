package org.sopt.and.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.theme.White100

@Composable
fun ShowOrHideToggle(
    isVisible: Boolean,
    onVisibilityChange: () -> Unit
) {
    Text(
        text = stringResource(id = if (isVisible) R.string.hide_password_button else R.string.show_password_button),
        color = White100,
        modifier = Modifier
            .padding(end = 12.dp)
            .clickable(onClick = onVisibilityChange)
    )
}