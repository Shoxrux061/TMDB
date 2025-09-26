package uz.shoxrux.feature_media_details.presentation.screens.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import uz.shoxrux.core.ui.components.ErrorScreen
import uz.shoxrux.core.ui.components.LoadingScreen

@Composable
fun DetailsScreen(viewModel: DetailsScreenViewModel) {

    val uiState = viewModel.uiState.collectAsState().value

    if (uiState.isLoading) {
        LoadingScreen()
    } else if (uiState.error != null) {
        ErrorScreen(uiState.error.toString()) {

        }
    } else {
        Text(uiState.movieDetails.toString())
    }


}