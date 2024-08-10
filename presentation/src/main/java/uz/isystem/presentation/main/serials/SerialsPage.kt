package uz.isystem.presentation.main.serials

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isystem.domain.models.tv_series_list.SeriesResponse
import uz.isystem.presentation.R
import uz.isystem.presentation.adapter.ParentAdapterSeries
import uz.isystem.presentation.adapter.SeriesTopAdapter
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.base.HorizontalMarginItemDecoration
import uz.isystem.presentation.databinding.PageSerialsBinding

class SerialsPage : BaseFragment(R.layout.page_serials) {

    private val binding by viewBinding(PageSerialsBinding::bind)
    private val viewModel: SeriesViewModel by viewModels()
    private var isFirst = false
    private val adapterSeries by lazy { ParentAdapterSeries(requireContext()) }
    private val multiData = ArrayList<SeriesResponse>()
    private val countData = 0
    private val adapterTop by lazy { SeriesTopAdapter() }

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {

        if (!isFirst) {
            sendRequest()
            observe()
            isFirst = true
        }
        setAdapter()
        setupCarousel()

    }

    private fun setAdapter() {
        binding.viewPager.adapter = adapterTop
        binding.multiRecycler.adapter = adapterSeries
    }

    private fun observe() {
        viewModel.successToday.observe(viewLifecycleOwner) {
            adapterTop.setData(it!!.results)
        }
    }

    private fun sendRequest() {
        viewModel.getAiringToday()
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