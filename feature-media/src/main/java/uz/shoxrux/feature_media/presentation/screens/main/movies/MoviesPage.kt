package uz.shoxrux.feature_media.presentation.screens.main.movies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uz.shoxrux.core.ui.components.ErrorScreen
import uz.shoxrux.core.ui.components.LoadingScreen
import uz.shoxrux.feature_media.R
import uz.shoxrux.feature_media.presentation.screens.main.movies.state.MoviesBundle
import uz.shoxrux.feature_media.presentation.screens.main.ui.ColorOrange

@Composable
fun MoviesPage(viewModel: MoviesPageViewModel) {

    val uiState = viewModel.uiState.value

    if (uiState.isLoading) {
        LoadingScreen()
    } else if (uiState.error != null) {
        ErrorScreen(errorText = uiState.error) {

        }
    } else {
        LazyColumn {
            item {
                MoviesContent(uiState.movies)
            }
        }
    }

}

@Composable
fun MoviesContent(
    moviesBundle: MoviesBundle
) {

    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    val pagerState = rememberPagerState { moviesBundle.trending.size }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                modifier = Modifier.size(40.dp),
                onClick = {

                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                    tint = colors.onBackground
                )
            }

            Spacer(Modifier.weight(1f))

            Icon(
                modifier = Modifier.size(60.dp),
                painter = painterResource(R.drawable.ic_app_logo),
                tint = colors.primary,
                contentDescription = null
            )

            Spacer(Modifier.weight(1f))

            IconButton(
                modifier = Modifier.size(40.dp),
                onClick = {

                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_settings),
                    contentDescription = null,
                    tint = colors.onBackground
                )
            }

        }

        Text(
            modifier = Modifier
                .padding(start = 16.dp, top = 20.dp, bottom = 10.dp),
            text = "Trending Movies",
            style = typography.titleMedium
        )

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            if (moviesBundle.trending.isNotEmpty()) {

                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    model = moviesBundle.trending[pagerState.currentPage].posterPath
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    colors.background,
                                    colors.primary.copy(alpha = 0.2f),
                                    colors.background
                                )
                            )
                        )
                )

                HorizontalPager(
                    modifier = Modifier.align(Alignment.Center),
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = 48.dp),
                    pageSpacing = 16.dp
                ) { page ->

                    Card {
                        AsyncImage(
                            model = moviesBundle.trending[page].backdropPath,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(170.dp),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }

        repeat(4) { index ->
            val (title, movies) = when (index) {
                0 -> "Popular" to moviesBundle.popular
                1 -> "Now Playing" to moviesBundle.nowPlaying
                2 -> "Top Rated" to moviesBundle.topRated
                else -> "Upcoming" to moviesBundle.upcoming
            }
            if (movies.isNotEmpty()) {

                Text(
                    modifier = Modifier.padding(top = 20.dp, start = 16.dp, bottom = 16.dp),
                    text = title,
                    style = typography.titleMedium
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(movies, key = { it.id }) { movie ->
                        MovieItem(
                            name = movie.title,
                            rating = movie.voteAverage,
                            imageUrl = movie.posterPath,
                            onItemClicked = {}
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MovieItem(
    name: String,
    rating: String,
    imageUrl: String,
    onItemClicked: () -> Unit
) {

    val typography = MaterialTheme.typography

    Column(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .clickable {
                onItemClicked.invoke()
            }
    ) {
        Card(
            modifier = Modifier
                .width(120.dp)
                .height(180.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(uz.shoxrux.core.R.drawable.ic_error)
                )

                RatingComponent(rating = rating, modifier = Modifier.align(Alignment.TopEnd))

            }
        }

        Text(
            maxLines = 2,
            minLines = 2,
            overflow = TextOverflow.Ellipsis,
            text = name,
            style = typography.bodySmall,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .width(110.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun RatingComponent(modifier: Modifier = Modifier, rating: String = "0.0") {

    Card(
        modifier = modifier.padding(vertical = 8.dp, horizontal = 10.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = 0.5f)),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null,
                tint = ColorOrange
            )

            Text(
                color = ColorOrange,
                text = rating,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}