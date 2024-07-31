package uz.isystem.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import uz.isystem.presentation.R
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.main.home.HomeViewModel

class MainScreen : BaseFragment(R.layout.screen_main) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {

        sendRequest()
        observe()
    }

    private fun observe() {
        viewModel.successResponse.observe(viewLifecycleOwner) {

            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()

        }

        viewModel.errorResponse.observe(viewLifecycleOwner) {

            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()

        }
    }

    private fun sendRequest() {
        viewModel.getTopRatedList("ru")
    }

}