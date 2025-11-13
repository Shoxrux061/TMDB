package uz.shoxrux.feature_media_details.presentation.screens.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import uz.shoxrux.core.R
import uz.shoxrux.core.ui.components.ErrorScreen
import uz.shoxrux.core.ui.components.LoadingScreen
import uz.shoxrux.core.ui.components.SmallAppButton
import uz.shoxrux.core.ui.components.rememberScreenWidthDp
import uz.shoxrux.feature_media_details.domain.models.movies.MovieUi
import uz.shoxrux.feature_media_details.domain.models.movies.content.MovieContentUi
import uz.shoxrux.feature_media_details.domain.models.movies.content.video.MovieVideoUi
import uz.shoxrux.feature_media_details.domain.models.movies.details.MovieDetailsUi
import uz.shoxrux.feature_media_details.domain.models.movies.person.PersonUi
import uz.shoxrux.feature_media_details.presentation.ui.ColorOrange

@Composable
fun DetailsScreen(viewModel: DetailsScreenViewModel) {

    val uiState = viewModel.uiState.collectAsState().value

    if (uiState.isLoading) {
        LoadingScreen()
    } else if (uiState.error != null) {
        ErrorScreen(uiState.error.toString()) {}
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {

            if (uiState.movieDetails != null) {
                item {
                    MovieDetailsContent(movieUiState = uiState.movieDetails)
                }
            }
            if (uiState.contentUi != null) {
                item {
                    ContentTabs(contentUi = uiState.contentUi)
                }
            }
            if (uiState.movieCredits != null) {
                item {
                    CreditsContent(uiState.movieCredits.cast.orEmpty())
                }
            }

            if (uiState.similarMovies.isNotEmpty() || uiState.recommendedMovies.isNotEmpty()) {
                item {
                    OtherMoviesTabs(
                        similar = uiState.similarMovies,
                        recommendation = uiState.recommendedMovies
                    )
                }
            }

            if (uiState.reviews.isNotEmpty()) {
                item {
                    Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                        ReviewsButton()
                    }
                }
            }

            item {
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun CreditsContent(cast: List<PersonUi>) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Text(
            text = "Cast",
            style = MaterialTheme.typography.titleMedium
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(cast.size) {
                PersonItem(profilePath = cast[it].profilePath ?: "", name = cast[it].name)
            }
        }

        SmallAppButton(
            onClick = {},
            text = "Full Cast & Crew"
        )

    }

}

@Composable
fun PersonItem(
    profilePath: String,
    name: String
) {
    Column {
        Card(
            modifier = Modifier
                .height(120.dp)
                .width(80.dp),
            border = BorderStroke(
                color = Color.White,
                width = 1.dp
            ),
            shape = RoundedCornerShape(0.dp)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = profilePath,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                placeholder = painterResource(R.drawable.ic_person),
                error = painterResource(R.drawable.ic_person)
            )
        }

        Spacer(Modifier.height(10.dp))

        Text(
            modifier = Modifier
                .widthIn(max = 70.dp)
                .heightIn(min = 30.dp),
            overflow = TextOverflow.Ellipsis,
            text = name,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 2,
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun MovieDetailsContent(movieUiState: MovieDetailsUi) {

    val colors = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(rememberScreenWidthDp().dp)
    ) {

        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            model = movieUiState.backdropUrl,
            contentDescription = null
        )

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            colors.background.copy(0.5f),
                            colors.background.copy(alpha = 0.9f),
                            colors.background
                        )
                    )
                )
        )

        Column(modifier = Modifier.fillMaxWidth()) {

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp, bottom = 40.dp),
                text = movieUiState.title,
                style = typography.titleMedium
            )

            Row(
                modifier = Modifier.padding(horizontal = 30.dp)
            ) {

                Card(
                    modifier = Modifier
                        .height(200.dp)
                        .width(120.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    AsyncImage(
                        placeholder = painterResource(R.drawable.ic_no_image),
                        modifier = Modifier.fillMaxSize(),
                        model = movieUiState.posterUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                }

                Spacer(Modifier.width(30.dp))

                Column {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(Color.Transparent),
                            shape = CircleShape,
                            modifier = Modifier.clip(CircleShape),
                            border = BorderStroke(width = 2.dp, color = Color(0xFFFF9800))
                        ) {
                            Text(
                                modifier = Modifier.padding(6.dp),
                                text = movieUiState.rating,
                                style = typography.bodyMedium
                            )
                        }

                        Spacer(Modifier.width(10.dp))

                        Card(
                            colors = CardDefaults.cardColors(Color.Transparent),
                            border = BorderStroke(width = 2.dp, color = Color(0xFF00FF0B)),
                            shape = RoundedCornerShape(0.dp)
                        ) {
                            Text(
                                modifier = Modifier.padding(6.dp),
                                text = movieUiState.spokenLanguage,
                                style = typography.bodyMedium
                            )
                        }
                    }

                    Spacer(Modifier.height(10.dp))


                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            painter = painterResource(R.drawable.ic_time),
                            contentDescription = null
                        )

                        Spacer(Modifier.width(10.dp))

                        Text(
                            text = movieUiState.runtimeMinutes.toString(),
                            style = typography.bodyMedium
                        )

                    }

                    Spacer(Modifier.height(10.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            painter = painterResource(R.drawable.ic_calendar),
                            contentDescription = null
                        )

                        Spacer(Modifier.width(10.dp))

                        Text(
                            text = movieUiState.releaseDate,
                            style = typography.bodyMedium
                        )

                    }

                    Spacer(Modifier.height(10.dp))

                    Row {

                        Text(
                            text = "Status:",
                            style = typography.bodyMedium,
                        )
                        Spacer(Modifier.width(10.dp))

                        Text(
                            text = if (movieUiState.isRealised) "Realised" else "Not Realised",
                            style = typography.bodyMedium,
                            color = if (movieUiState.isRealised) Color.Green else Color.Red
                        )

                    }

                    Spacer(Modifier.height(10.dp))

                    Text(
                        text = "Original Title: ",
                        style = typography.bodyMedium
                    )

                    Spacer(Modifier.height(10.dp))

                    Text(
                        text = movieUiState.originalTitle,
                        style = typography.bodyMedium.copy(
                            fontStyle = FontStyle.Italic
                        )
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {

                items(movieUiState.genres.size) {
                    GenreItem(name = movieUiState.genres[it].name)
                }

            }

            Spacer(Modifier.height(20.dp))

            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = "Overview",
                style = typography.titleMedium
            )

            Spacer(Modifier.height(10.dp))

            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = movieUiState.overview,
                style = typography.bodyMedium.copy(
                    fontStyle = FontStyle.Italic
                )
            )

            Spacer(Modifier.height(20.dp))

        }
    }
}

@Composable
fun OtherMoviesTabs(similar: List<MovieUi>, recommendation: List<MovieUi>) {

    val colors = MaterialTheme.colorScheme

    val tabs = buildList {
        if (similar.isNotEmpty()) add("Similar")
        if (recommendation.isNotEmpty()) add("Recommendation")
    }

    val tabState = remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        TabRow(
            modifier = Modifier
                .fillMaxWidth(),
            selectedTabIndex = tabState.intValue,
            containerColor = colors.background,
            contentColor = colors.onBackground,
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = tabState.intValue == index,
                    selectedContentColor = colors.onBackground,
                    unselectedContentColor = colors.onBackground,
                    onClick = { tabState.intValue = index },
                    text = {
                        Text(title)
                    }
                )

            }
        }

        when (tabState.intValue) {

            0 -> {
                MovieList(similar)
            }

            1 -> {
                MovieList(recommendation)
            }

        }

        SmallAppButton(
            onClick = {

            },
            text = "See all"
        )
    }
}

@Composable
fun MovieList(movies: List<MovieUi>) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies.size) {

            MovieItem(
                name = movies[it].title,
                rating = movies[it].popularity,
                imageUrl = movies[it].posterPath,
                onItemClicked = {

                }
            )
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
                    placeholder = painterResource(R.drawable.ic_no_image),
                    error = painterResource(R.drawable.ic_no_image)
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

@Composable
fun GenreItem(name: String) {

    Card(
        modifier = Modifier.padding(end = 10.dp),
        shape = RoundedCornerShape(30.dp),
        border = BorderStroke(width = 1.dp, color = Color.Gray),
        colors = CardDefaults.cardColors(Color.Gray.copy(alpha = 0.4f))
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = name,
            style = MaterialTheme.typography.bodyMedium
        )
    }

}

@Composable
fun ContentTabs(contentUi: MovieContentUi) {

    val colors = MaterialTheme.colorScheme

    val tabs = listOf("Videos", "Posters", "Backdrops")

    val tabState = remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        TabRow(
            modifier = Modifier
                .fillMaxWidth(),
            selectedTabIndex = tabState.intValue,
            containerColor = colors.background,
            contentColor = colors.onBackground,
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = tabState.intValue == index,
                    selectedContentColor = colors.onBackground,
                    unselectedContentColor = colors.onBackground,
                    onClick = { tabState.intValue = index },
                    text = {
                        Text(title)
                    }
                )

            }
        }

        when (tabState.intValue) {

            0 -> {
                VideosRow(contentUi.videos)
            }

            1 -> {
                PostersRow(contentUi.posters)
            }

            2 -> {
                BackdropsRow(contentUi.backdrops)
            }

        }

        SmallAppButton(
            onClick = {},
            text = "See all content"
        )

    }
}

@Composable
fun PostersRow(
    posters: List<String?>
) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(posters.size) { index ->

            Card(
                modifier = Modifier
                    .height(180.dp)
                    .width(120.dp),
                shape = RoundedCornerShape(8.dp)
            ) {

                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    model = posters[index],
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun BackdropsRow(backdrops: List<String?>) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(backdrops.size) { index ->

            Card(
                modifier = Modifier
                    .height(180.dp)
                    .aspectRatio(2f),
                shape = RoundedCornerShape(8.dp)
            ) {

                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    model = backdrops[index],
                    contentDescription = null,
                    placeholder = painterResource(R.drawable.ic_no_image),
                    error = painterResource(R.drawable.ic_no_image)
                )
            }
        }
    }
}

@Composable
fun VideosRow(videos: List<MovieVideoUi>) {

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        items(videos.size) {
            VideoItem(
                imageUrl = videos[it].imageUrl
            )
        }

    }

}

@Composable
fun VideoItem(imageUrl: String) {

    Card(
        modifier = Modifier
            .height(180.dp)
            .aspectRatio(2f),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            color = Color.Red,
            width = 1.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                model = imageUrl,
                contentDescription = null,
                placeholder = painterResource(R.drawable.ic_no_image),
                error = painterResource(R.drawable.ic_no_image)
            )

            Icon(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(R.drawable.ic_youtube_play),
                contentDescription = null,
                tint = Color.Red
            )

        }
    }
}

@Preview
@Composable
fun ReviewsButton(
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .clickable {

            },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {


            Row(
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Reviews",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Spacer(Modifier.weight(1f))

                Icon(
                    painter = painterResource(R.drawable.ic_comment),
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = null
                )

            }
        }
    }
}