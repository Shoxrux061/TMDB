package uz.shoxrux.tmdb.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import uz.shoxrux.core.ui.theme.AppTheme
import uz.shoxrux.feature_people.presentation.screens.people.PeopleScreen
import uz.shoxrux.feature_people.presentation.screens.people.PeopleViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                val viewModel = hiltViewModel<PeopleViewModel>()

                LaunchedEffect(Unit) {
                    viewModel.getPeopleList()
                }

                PeopleScreen(viewModel)

            }
        }
    }
}