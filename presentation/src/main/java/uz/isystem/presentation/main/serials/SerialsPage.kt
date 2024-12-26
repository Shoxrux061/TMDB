package uz.isystem.presentation.main.serials

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import uz.isystem.domain.models.series.tv_series_list.SeriesResponse
import uz.isystem.presentation.R
import uz.isystem.presentation.adapter.tv.ParentAdapterSeries
import uz.isystem.presentation.adapter.tv.SeriesTopAdapter
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.base.HorizontalMarginItemDecoration
import uz.isystem.presentation.databinding.PageSerialsBinding
import uz.isystem.presentation.main.MainScreenDirections
import uz.isystem.presentation.util.Utils
import uz.isystem.utills.Constants

class SerialsPage : BaseFragment(R.layout.page_serials) {

    private val binding by viewBinding(PageSerialsBinding::bind)
    private val viewModel: SeriesViewModel by viewModels()
    private var isFirst = false
    private val adapterSeries by lazy { ParentAdapterSeries(requireContext()) }
    private val multiData = ArrayList<SeriesResponse>()
    private var countData = 0
    private val adapterTop by lazy { SeriesTopAdapter() }

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {

        if (!isFirst) {
            sendRequest()
            observe()
            isFirst = true
        }
        setAdapter()
        listenActions()
        setupCarousel()

    }

    private fun listenActions() {
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)

                binding.backgroundPoster.load(Constants.IMAGE_URL.plus(adapterTop.getImage(position)))

            }
        })

        adapterTop.onClickItem = {
            Utils.navigateWithAnimations(
                findNavController(),
                MainScreenDirections.actionMainScreenToDetailScreen(type = 2, id = it)
            )
        }
        adapterSeries.onClickItem = {
            Utils.navigateWithAnimations(
                findNavController(),
                MainScreenDirections.actionMainScreenToDetailScreen(type = 2, id = it)
            )
        }
        adapterSeries.onClickChildItem = {
            Utils.navigateWithAnimations(
                findNavController(),
                MainScreenDirections.actionMainScreenToDetailScreen(type = 2, id = it)
            )
        }
    }

    private fun setAdapter() {
        binding.viewPager.adapter = adapterTop
        binding.multiRecycler.adapter = adapterSeries
        binding.multiRecycler.layoutManager = LinearLayoutManager(context)
        binding.multiRecycler.isNestedScrollingEnabled = false
        binding.dotsIndicator.attachTo(binding.viewPager)
    }

    private fun observe() {
        viewModel.successToday.observe(viewLifecycleOwner) {
            it!!.sortType = 0
            multiData.add(it)
            countData++
            checkIsFull()
        }
        viewModel.successPopular.observe(viewLifecycleOwner) {
            it!!.sortType = 1
            multiData.add(it)
            countData++
            checkIsFull()
        }
        viewModel.successTrending.observe(viewLifecycleOwner) {
            adapterTop.setData(it!!.results)
        }
        viewModel.successTopRated.observe(viewLifecycleOwner) {
            it!!.sortType = 2
            multiData.add(it)
            countData++
            checkIsFull()
        }

        viewModel.successOnTheAir.observe(viewLifecycleOwner) {
            it!!.sortType = 3
            multiData.add(it)
            countData++
            checkIsFull()
        }
    }

    private fun checkIsFull() {
        if (countData >= 4) {
            adapterSeries.setData(multiData)
        }
    }

    private fun sendRequest() {
        viewModel.getAiringToday()
        viewModel.getTrending()
        viewModel.getPopular()
        viewModel.getOnTheAir()
        viewModel.getTopRatedList()
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


}