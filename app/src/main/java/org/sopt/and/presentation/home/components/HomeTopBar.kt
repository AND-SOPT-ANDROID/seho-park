package org.sopt.and.presentation.home.components

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.Grey200
import org.sopt.and.ui.theme.White100

@Composable
fun HomeTopBar(
    @StringRes genres: List<Int>
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.wavve_logo),
                contentDescription = "",
                modifier = Modifier
                    .height(60.dp)
                    .width(100.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                painter = painterResource(R.drawable.ic_cast_24),
                contentDescription = "",
                modifier = Modifier.size(30.dp),
                tint = White100
            )

            Spacer(modifier = Modifier.size(16.dp))

            Icon(
                painter = painterResource(R.drawable.ic_live_tv_24),
                contentDescription = "",
                modifier = Modifier.size(30.dp),
                tint = White100
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(
                items = genres,
                key = { it }
            ) { genre ->
                Text(
                    text = stringResource(genre),
                    color = Grey200,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun HomeTopBarPreview() {
    HomeTopBar(genres = listOf(R.string.home_top20_title))
}

