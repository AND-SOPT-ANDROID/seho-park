package org.sopt.and.presentation.home.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.Grey200
import org.sopt.and.ui.theme.White100

@Composable
fun HomeBannerPager(@DrawableRes banners: List<Int>) {
    val pagerState = rememberPagerState(pageCount = { banners.size })

    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 10.dp),
        pageSpacing = 10.dp
    ) { page ->
        HomeBannerPage(
            index = page,
            banners = banners
        )
    }
}

@Composable
fun HomeBannerPage(
    index: Int,
    @DrawableRes banners: List<Int>
) {
    Box(
        Modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(16.dp))
            .border(1.dp, Grey200, shape = RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = painterResource(banners[index]),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        HomeBannerIndicator(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(6.dp),
            index = index,
            totalPage = banners.size
        )
    }
}

@Composable
fun HomeBannerIndicator(
    modifier: Modifier,
    index: Int,
    totalPage: Int
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(Color.Black)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = White100,
                        fontSize = 11.sp
                    )
                ) {
                    append(
                        stringResource(
                            R.string.home_banner_indicator_front, index + 1
                        )
                    )
                }
                withStyle(
                    style = SpanStyle(
                        color = Grey200,
                        fontSize = 11.sp
                    )
                ) {
                    append(
                        stringResource(
                            R.string.home_banner_indicator_back,
                            totalPage
                        )
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun HomeBannerPagerPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HomeBannerPager(listOf())
    }
}