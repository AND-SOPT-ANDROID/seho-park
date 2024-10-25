package org.sopt.and.home

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R

val Posters = List(9) { R.drawable.bee }
val categories = listOf("뉴클래식", "드라마", "예능", "영화", "애니", "해외시리즈")
val textColor = Color.White
val categoryColor = Color.Gray
val posterSize = Modifier.size(120.dp, 180.dp)
val roundedCorner = RoundedCornerShape(3.dp)

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(Color.Black),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        item {
            CategoryRow(categories = categories)
        }
        item {
            Section(title = R.string.recommendation_label) {
                PosterRow(moviePosters = Posters, itemComposable = { poster, _ -> RecommendPosterItem(poster) })
            }
        }
        item {
            Section(title = R.string.ranking_label) {
                PosterRow(moviePosters = Posters, itemComposable = { poster, rank -> rank?.let {
                    TopPosterItem(poster,
                        it
                    )
                } })
            }
        }
    }
}
@Composable
fun CategoryRow(categories: List<String>) {
    LazyRow(
        modifier = Modifier.padding(top = 10.dp).fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(categories.size) { index ->
            Text(text = categories[index], color = categoryColor, fontSize = 18.sp)
        }
    }
}

@Composable
fun Section(@StringRes title: Int, content: @Composable () -> Unit) {
    Column(modifier = Modifier.padding(start = 15.dp)) {
        Text(
            text = stringResource(id = title),
            color = textColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.W600
        )
        content()
    }
}

@Composable
fun PosterRow(moviePosters: List<Int>, itemComposable: @Composable (Int, Int?) -> Unit) {
    LazyRow(
        contentPadding = PaddingValues(15.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(moviePosters.size) { index ->
            itemComposable(moviePosters[index], index + 1)
        }
    }
}

@Composable
fun RecommendPosterItem(posterItem: Int) {
    Image(
        painter = painterResource(id = posterItem),
        contentDescription = "영화 포스터",
        modifier = posterSize.clip(roundedCorner),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun TopPosterItem(posterItem: Int, rank: Int) {
    Box(
        modifier = Modifier.size(170.dp, 255.dp)
    ) {
        Image(
            painter = painterResource(id = posterItem),
            contentDescription = "영화 포스터",
            modifier = Modifier.size(150.dp, 225.dp).clip(roundedCorner),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "$rank",
            modifier = Modifier.align(Alignment.BottomStart).padding(start = 10.dp),
            color = textColor,
            fontSize = 40.sp,
            fontWeight = FontWeight.W800,
            fontStyle = FontStyle.Italic
        )
    }
}