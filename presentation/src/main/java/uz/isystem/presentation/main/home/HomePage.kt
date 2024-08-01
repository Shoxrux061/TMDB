package uz.isystem.presentation.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import uz.isystem.presentation.R
import uz.isystem.presentation.base.BaseFragment

class HomePage : BaseFragment(R.layout.page_home) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {

        sendRequest()
        observe()

    }

    private fun observe() {

    }

    private fun sendRequest() {
        viewModel.getNowPlaying("eng")
        viewModel.getTopRatedList("eng")
        viewModel.getPopular("eng")
        viewModel.getUpcoming("eng")
    }
}

