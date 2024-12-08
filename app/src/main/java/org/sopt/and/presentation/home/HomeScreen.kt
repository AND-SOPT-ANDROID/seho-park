package org.sopt.and.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.sopt.and.R
import org.sopt.and.presentation.home.components.HomeBannerPager
import org.sopt.and.presentation.home.components.HomeBottomCoupon
import org.sopt.and.presentation.home.components.HomeTopBar
import org.sopt.and.presentation.home.components.RecommendList
import org.sopt.and.presentation.home.components.Top20List
import org.sopt.and.ui.theme.Grey100

@Composable
fun HomeScreen(
    innerPadding: PaddingValues
) {
    val homeViewModel = viewModel<HomeViewModel>()
    val homeUiState by homeViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Grey100)
            .padding(innerPadding)
    ) {
        HomeTopBar(genres = homeUiState.genres)

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                HomeBannerPager(homeUiState.banners)
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                RecommendList(
                    title = stringResource(R.string.home_picks_of_editor_title),
                    items = homeUiState.recommends
                )
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            item {
                Top20List(homeUiState.rankers)
            }
        }

        HomeBottomCoupon()
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    Scaffold { innerPadding ->
        HomeScreen(innerPadding = innerPadding)
    }
}