package org.sopt.and.presentation.home

import org.sopt.and.core.ContentType
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.sopt.and.R
import org.sopt.and.presentation.home.components.HomeBannerPager
import org.sopt.and.presentation.home.components.RecommendList
import org.sopt.and.presentation.home.components.Top20List


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    onContentTypeSelected: (ContentType) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val homeState by viewModel.uiState.collectAsStateWithLifecycle()
    val mainPagerState = rememberPagerState(initialPage = Int.MAX_VALUE / 2) {
        Int.MAX_VALUE // 페이지 수가 무한대
    }
    LaunchedEffect(Unit) {
        viewModel.getDummyHomeContent()
    }
    LazyColumn(
        modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            HomeBannerPager(homeState.mainContents)
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            RecommendList(
                title = stringResource(R.string.home_picks_of_editor_title),
                items = homeState.commonContents
            )
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            Top20List(homeState.rankingContents)
        }
    }
}


@Composable
fun AutoScrollEffect(pagerState: PagerState) {
    LaunchedEffect(pagerState.currentPage) {
        while (true) {
            delay(3000)
            withContext(NonCancellable) {
                pagerState.animateScrollToPage(
                    page = pagerState.currentPage + 1,
                    animationSpec = spring(stiffness = Spring.StiffnessLow)
                )
            }
        }
    }
}