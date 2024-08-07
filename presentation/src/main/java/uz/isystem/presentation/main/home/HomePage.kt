package uz.isystem.presentation.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import uz.isystem.domain.models.MovieListResponse
import uz.isystem.presentation.R
import uz.isystem.presentation.adapter.HomeTopAdapter
import uz.isystem.presentation.adapter.ParentAdapter
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.base.HorizontalMarginItemDecoration
import uz.isystem.presentation.databinding.PageHomeBinding
import uz.isystem.presentation.main.MainScreen
import uz.isystem.presentation.main.MainScreenDirections
import uz.isystem.utills.Constants

class HomePage : BaseFragment(R.layout.page_home) {

    private val viewModel: HomeViewModel by viewModels()
    private val adapter by lazy { HomeTopAdapter() }
    private val multiAdapter by lazy { ParentAdapter(requireContext()) }
    private var isFirst = false
    private val multiData = ArrayList<MovieListResponse>()
    private val binding by viewBinding(PageHomeBinding::bind)
    private var dataCount = 0

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {
        if(!isFirst){
            sendRequest()
            isFirst = true
        }
        setAdapter()
        setupCarousel()
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

        adapter.onClickItem = {
            changeScreen(MainScreenDirections.actionMainScreenToDetailScreen())
        }
        multiAdapter.onClickItem = {

        }
        multiAdapter.onClickChildItem = {

        }

    }

    private fun setupCarousel() {

        binding.viewPager.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
        }
        binding.viewPager.setPageTransformer(pageTransformer)
        val itemDecoration = HorizontalMarginItemDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.viewPager.addItemDecoration(itemDecoration)
    }


    private fun setAdapter() {
        binding.viewPager.adapter = adapter
        binding.multiRecycler.layoutManager = LinearLayoutManager(context)
        binding.multiRecycler.isNestedScrollingEnabled = false
        binding.multiRecycler.adapter = multiAdapter
        binding.dotsIndicator.attachTo(binding.viewPager)
    }

    private fun sendRequest() {
        viewModel.getNowPlaying("ru")
        viewModel.getTopRatedList("ru")
        viewModel.getPopular("ru")
        viewModel.getUpcoming("ru")
    }

    private fun observe() {

        viewModel.successNowPLaying.observe(viewLifecycleOwner) {
            it!!.sortType = 0
            adapter.setData(it.results)
        }
        viewModel.successPopular.observe(viewLifecycleOwner) {
            it!!.sortType = 1
            multiData.add(it)
            dataCount++
            checkIsFull()
        }
        viewModel.successTopRated.observe(viewLifecycleOwner) {
            it!!.sortType = 2
            multiData.add(it)
            dataCount++
            checkIsFull()
        }
        viewModel.successUpcoming.observe(viewLifecycleOwner) {
            it!!.sortType = 3
            multiData.add(it)
            dataCount++
            checkIsFull()
        }

    }

    private fun checkIsFull() {
        if (dataCount >= 3) {
            multiAdapter.setData(multiData)
        }
    }

    private fun changeScreen(navDirections: NavDirections) {
        val navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.alpha_in)
            .setExitAnim(R.anim.alpha_out)
            .setPopEnterAnim(R.anim.alpha_pop_in)
            .setPopExitAnim(R.anim.alpha_pop_out)
            .build()
        findNavController().navigate(navDirections, navOptions)
    }

}

