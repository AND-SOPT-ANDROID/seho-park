package org.sopt.and.presentation.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.sopt.and.R

data class HomeUiState(
    @StringRes
    val genres: List<Int> = listOf(
        R.string.genre_new_classic,
        R.string.genre_drama,
        R.string.genre_entertainment,
        R.string.genre_movie,
        R.string.genre_animation,
        R.string.genre_foreign_country_series
    ),
    @DrawableRes
    val banners: List<Int> = listOf(
        R.drawable.bee,
        R.drawable.bee,
        R.drawable.bee,
        R.drawable.bee,
    ),
    @DrawableRes
    val recommends: List<Int> = listOf(
        R.drawable.bee,
        R.drawable.bee,
        R.drawable.bee,
        R.drawable.bee,
        R.drawable.bee,
        R.drawable.bee,
        ),
    @DrawableRes
    val rankers: List<Int> = listOf(
        R.drawable.bee,
        R.drawable.bee,
        R.drawable.bee,
        R.drawable.bee,
        R.drawable.bee,
        R.drawable.bee,
        R.drawable.bee,
        R.drawable.bee,
    )
)
