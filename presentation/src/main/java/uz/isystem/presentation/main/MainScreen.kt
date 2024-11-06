package uz.isystem.presentation.main

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isystem.presentation.R
import uz.isystem.presentation.adapter.other.MainAdapter
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.databinding.ScreenMainBinding

class MainScreen : BaseFragment(R.layout.screen_main) {

    private val binding by viewBinding(ScreenMainBinding::bind)

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {

        setPager()

    }

    private fun setPager() {
        val adapter = MainAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false

        binding.bottomNavigation.onItemSelected = { item ->

            when (item) {
                0 -> binding.viewPager.currentItem = 0
                1 -> binding.viewPager.currentItem = 1
                2 -> binding.viewPager.currentItem = 2
                3 -> binding.viewPager.currentItem = 3
            }
        }
    }
}