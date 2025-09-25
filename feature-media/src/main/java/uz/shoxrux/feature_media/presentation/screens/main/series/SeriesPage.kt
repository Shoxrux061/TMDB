package uz.shoxrux.feature_media.presentation.screens.main.series

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import uz.shoxrux.core.ui.components.ErrorScreen
import uz.shoxrux.core.ui.components.LoadingScreen
import uz.shoxrux.feature_media.presentation.screens.main.movies.MovieDivider
import uz.shoxrux.feature_media.presentation.screens.main.movies.MovieItem
import uz.shoxrux.feature_media.presentation.screens.main.movies.TopBar
import uz.shoxrux.feature_media.presentation.screens.main.series.state.SeriesBundle

@Composable
fun SeriesPage(
    viewModel: SeriesPageViewModel
) {

    val uiState = viewModel.uiState.collectAsState().value

    if (uiState.isLoading) {
        LoadingScreen()
    } else if (uiState.error != null) {
        ErrorScreen(uiState.error, onRetryClick = {
            viewModel.getSeries()
        })
    } else {

        Column {
            LazyColumn {
                item { SeriesContent(uiState.series) }
            }
        }
    }

}

@Composable
fun SeriesContent(
    seriesBundle: SeriesBundle
) {

    val colors = MaterialTheme.colorScheme

    val pagerState = rememberPagerState { seriesBundle.trending.size }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background)
    ) {

        MovieDivider(
            onAllSeeClicked = {},
            title = "Trending"
        )

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {

            if (seriesBundle.trending.isNotEmpty()) {

                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    model = seriesBundle.trending[pagerState.currentPage].posterPath
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
                            model = seriesBundle.trending[page].backdropPath,
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
                0 -> "Popular" to seriesBundle.popular
                1 -> "Airing Today" to seriesBundle.airingToday
                2 -> "Top Rated" to seriesBundle.topRated
                else -> "On The Air" to seriesBundle.onTheAir
            }
            if (movies.isNotEmpty()) {

                MovieDivider(
                    onAllSeeClicked = {},
                    title = title
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(movies, key = { it.id }) { serial ->
                        MovieItem(
                            name = serial.name,
                            rating = serial.voteAverage,
                            imageUrl = serial.posterPath,
                            onItemClicked = {}
                        )
                    }
                }
            }
        }
    }

}