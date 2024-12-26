package uz.isystem.presentation.search

import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import uz.isystem.domain.models.other.SearchModel
import uz.isystem.presentation.R
import uz.isystem.presentation.adapter.other.search.SearchParentAdapter
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.databinding.ScreenSearchBinding

class SearchScreen : BaseFragment(R.layout.screen_search) {

    private val binding by viewBinding(ScreenSearchBinding::bind)
    private val viewModel: SearchViewModel by viewModels()
    private val adapter by lazy { SearchParentAdapter(requireContext()) }
    private val searchMultiData = SearchModel()

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {
        setAdapter()
        setFilter()
        setTabs()
        observe()
        setActions()
    }

    private fun setAdapter() {
        binding.viewPager.adapter = adapter
    }

    private fun setTabs() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Movies"
                1 -> tab.text = "Serials"
                2 -> tab.text = "Peoples"
            }
        }.attach()
    }

    private fun setActions() {
        binding.searchEdt.addTextChangedListener {
            setAnimation()
        }

        binding.floatSearch.setOnClickListener {
            if (binding.searchEdt.text.isNotBlank()) {
                viewModel.searchMovie(q = binding.searchEdt.text.toString())
                binding.floatSearch.visibility = View.GONE
                val floatingButtonAnimReverse =
                    AnimationUtils.loadAnimation(context, me.ibrahimsn.lib.R.anim.abc_popup_exit)
                binding.floatSearch.animation = floatingButtonAnimReverse
            }
        }

    }

    private fun setAnimation() {
        val floatingButtonAnim =
            AnimationUtils.loadAnimation(context, me.ibrahimsn.lib.R.anim.abc_popup_enter)
        val floatingButtonAnimReverse =
            AnimationUtils.loadAnimation(context, me.ibrahimsn.lib.R.anim.abc_popup_exit)


        if (binding.searchEdt.text.isNotBlank()) {
            binding.floatSearch.animation = floatingButtonAnim
            binding.floatSearch.visibility = View.VISIBLE
            binding.textPlaceHolder.visibility = View.GONE
        } else {
            binding.floatSearch.animation = floatingButtonAnimReverse
            binding.floatSearch.visibility = View.GONE
        }
    }

    private fun observe() {
        viewModel.successMovie.observe(viewLifecycleOwner) {
            searchMultiData.movieData = it
            adapter.setData(searchMultiData)
        }
    }

    private fun setFilter() {

        val filter = InputFilter { source, _, _, _, dStart, _ ->
            if (dStart == 0 && source.startsWith(" ")) "" else null
        }
        binding.searchEdt.filters = arrayOf(filter)
    }
}
