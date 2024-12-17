package org.sopt.and.core.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.WavveBg

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CloseTopBar(
    title: String,
    onCloseClicked: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = title, fontSize = 20.sp)
        },
        actions = {
            IconButton(onClick = onCloseClicked) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.close_top_bar_icon_description_close),
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