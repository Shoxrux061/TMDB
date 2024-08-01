package uz.isystem.presentation.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isystem.presentation.R
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.databinding.PageHomeBinding

class HomePage : BaseFragment(R.layout.page_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val adapter by lazy { HomeAdapter() }
    private val binding by viewBinding(PageHomeBinding::bind)

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {
        setAdapter()
        sendRequest()
        observe()

    }

    private fun setAdapter() {
        binding.viewPager.adapter = adapter
    }

    private fun sendRequest() {
        viewModel.getNowPlaying("eng")
        viewModel.getTopRatedList("eng")
        viewModel.getPopular("eng")
        viewModel.getUpcoming("eng")
    }

    private fun observe() {

        viewModel.successNowPLaying.observe(viewLifecycleOwner) {
            adapter.setData(it!!.results)
        }

    }
}

