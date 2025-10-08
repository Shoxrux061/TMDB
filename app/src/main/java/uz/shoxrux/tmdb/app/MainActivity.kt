package uz.shoxrux.tmdb.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import uz.shoxrux.core.ui.theme.AppTheme
import uz.shoxrux.feature_media_details.presentation.screens.details.DetailsScreen
import uz.shoxrux.feature_media_details.presentation.screens.details.DetailsScreenViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {

                val viewModel: DetailsScreenViewModel = hiltViewModel()

                LaunchedEffect(Unit) {
                    viewModel.getMovieById(634649)
                }

                Scaffold { paddingValues ->

                    Box(
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        DetailsScreen(viewModel)
                    }

                }
            }
        }
    }
}