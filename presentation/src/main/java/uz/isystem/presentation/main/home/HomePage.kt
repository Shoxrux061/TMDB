package uz.isystem.presentation.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import uz.isystem.domain.models.MovieListResponse
import uz.isystem.presentation.R
import uz.isystem.presentation.adapter.HomeTopAdapter
import uz.isystem.presentation.adapter.ParentAdapter
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.databinding.PageHomeBinding
import uz.isystem.utills.Constants

class HomePage : BaseFragment(R.layout.page_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val adapter by lazy { HomeTopAdapter() }
    private val multiAdapter by lazy { ParentAdapter() }
    private val multiData = ArrayList<MovieListResponse>()
    private val binding by viewBinding(PageHomeBinding::bind)
    private var dataCount = 0

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
        binding.recyclerView.adapter = multiAdapter
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
        viewModel.successPopular.observe(viewLifecycleOwner){
            multiData.add(it!!)
            multiData[dataCount].sortType = 0
            dataCount++
            checkIsComplete()
        }
        viewModel.successTopRated.observe(viewLifecycleOwner){
            multiData.add(it!!)
            multiData[dataCount].sortType = 1
            dataCount++
            checkIsComplete()
        }

        viewModel.successUpcoming.observe(viewLifecycleOwner){
            multiData.add(it!!)
            multiData[dataCount].sortType = 2
            dataCount++
            checkIsComplete()
        }
    }

    private fun checkIsComplete() {
        if(dataCount == 3){
            multiAdapter.setData(multiData)
        }
    }
}

