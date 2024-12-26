package uz.isystem.presentation.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isystem.presentation.R
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.databinding.ScreenSearchBinding


class SearchScreen : BaseFragment(R.layout.screen_search) {

    private val binding by viewBinding(ScreenSearchBinding::bind)
    private val viewModel: SearchViewModel by viewModels()

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {

        observe()
        setActions()

    }

    private fun setActions() {
        binding.searchEdt.addTextChangedListener {

            val floatingButtonAnim =
                AnimationUtils.loadAnimation(context, me.ibrahimsn.lib.R.anim.abc_popup_enter)
            val floatingButtonAnimReverse =
                AnimationUtils.loadAnimation(context, me.ibrahimsn.lib.R.anim.abc_popup_exit)

            if (binding.searchEdt.text.isNotBlank()) {
                binding.floatSearch.animation = floatingButtonAnim
                binding.floatSearch.visibility = View.VISIBLE
            } else {
                binding.floatSearch.animation = floatingButtonAnimReverse
                binding.floatSearch.visibility = View.GONE
            }
        }
    }

    private fun observe() {
        viewModel.successMovie.observe(viewLifecycleOwner) {
            Log.d("TAGSearch", "observe: $it")
        }
    }
}
