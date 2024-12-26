package uz.isystem.presentation.util

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import uz.isystem.presentation.R

object Utils {

    fun navigateWithAnimations(
        navController: NavController,
        navDirections: NavDirections
    ) {
        val navOptions = NavOptions.Builder()

            .setEnterAnim(R.anim.alpha_in)
            .setExitAnim(R.anim.alpha_out)
            .setPopEnterAnim(R.anim.alpha_in)
            .setPopExitAnim(R.anim.alpha_out)
            .build()
        navController.navigate(navDirections, navOptions)
    }

}