package uz.isystem.presentation.main.home

import android.media.MediaScannerConnection.OnScanCompletedListener
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver.OnScrollChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import uz.isystem.presentation.R
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.databinding.PageHomeBinding
import uz.isystem.utills.Constants

class HomePage : BaseFragment(R.layout.page_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val adapter by lazy { HomeAdapter() }
    private val binding by viewBinding(PageHomeBinding::bind)

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {
        setAdapter()
        sendRequest()
        observe()
        listenActions()

    }

    private fun listenActions() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                binding.backgroundPoster.load(Constants.IMAGE_URL.plus(adapter.getImage(position)))

            }
        })
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

