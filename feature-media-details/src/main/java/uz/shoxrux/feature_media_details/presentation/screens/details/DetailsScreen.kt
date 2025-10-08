package uz.shoxrux.feature_media_details.presentation.screens.details

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import uz.shoxrux.core.R
import uz.shoxrux.core.ui.components.ErrorScreen
import uz.shoxrux.core.ui.components.LoadingScreen
import uz.shoxrux.core.ui.components.rememberScreenWidthDp
import uz.shoxrux.feature_media_details.domain.models.movies.content.MovieContentUi
import uz.shoxrux.feature_media_details.domain.models.movies.details.MovieDetailsUi

@Composable
fun DetailsScreen(viewModel: DetailsScreenViewModel) {

    val uiState = viewModel.uiState.collectAsState().value

    if (uiState.isLoading) {
        LoadingScreen()
    } else if (uiState.error != null) {
        ErrorScreen(uiState.error.toString()) {

        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            item {
                if (uiState.movieDetails != null) {
                    MovieDetailsContent(movieUiState = uiState.movieDetails)
                    Spacer(Modifier.height(20.dp))
                }
                if (uiState.contentUi != null) {
                    Log.d("TAGContent", "DetailsScreen:${uiState.contentUi.posters} ")
                    ContentTabs(contentUi = uiState.contentUi)
                    Spacer(Modifier.height(20.dp))
                }
            }
        }
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
                        placeholder = painterResource(R.drawable.ic_error),
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
    val typography = MaterialTheme.typography

    val tabs = listOf("Posters", "Backdrops", "Videos")

    val tabState = remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        TabRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
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
        Spacer(Modifier.height(20.dp))

        when (tabState.intValue) {

            0 -> {
                PostersRow(contentUi.posters)
            }

            1 -> {

            }

            2 -> {

            }

        }

    }
}

@Composable
fun PostersRow(
    posters: List<String?>
) {

    LazyRow(
        modifier = Modifier.padding(horizontal = 16.dp),
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