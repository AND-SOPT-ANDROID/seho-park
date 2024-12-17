package org.sopt.and.presentation.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.W900
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.domain.entity.HomeCommonContent
import org.sopt.and.ui.theme.White100

@Composable
fun Top20List(@DrawableRes rankers: HomeCommonContent) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.home_top20_title),
            color = White100,
            fontWeight = W900
        )

        Spacer(Modifier.height(10.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                count = rankers.size,
                key = { it }
            ) { index ->
                RankedItem(index, rankers)
            }
        }
    }
}

@Composable
fun RankedItem(
    index: Int,
    @DrawableRes rankers: List<Int>
) {
    Box(
        Modifier.height(240.dp)
    ) {
        Image(
            painter = painterResource(rankers[index]),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(220.dp)
                .clip(shape = RoundedCornerShape(15.dp))
                .align(Alignment.TopStart)
        )

        Text(
            text = stringResource(R.string.home_rank_of_item, index + 1),
            fontSize = 50.sp,
            fontWeight = W900,
            color = White100,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp)
        )
    }
}

@Preview
@Composable
fun Top20ListPreview() {
    Top20List(listOf())
}