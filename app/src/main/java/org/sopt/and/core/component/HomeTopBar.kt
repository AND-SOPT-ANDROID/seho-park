package org.sopt.and.core.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import org.sopt.and.R
import org.sopt.and.ui.theme.WavveBg


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    onLiveButtonClick: () -> Unit
) {
    TopAppBar(
        title = {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.wavve_logo),
                contentDescription = stringResource(R.string.back_button_top_bar_icon_description_logo),
                tint = White
            )
        },
        actions = {
            IconButton(onClick = onLiveButtonClick) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_cast_24),
                    contentDescription = stringResource(R.string.common_top_bar_icon_description_live),
                    tint = White
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = WavveBg,
            titleContentColor = White
        )
    )
}