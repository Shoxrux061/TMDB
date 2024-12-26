package uz.isystem.presentation.main

import android.os.Bundle
import android.view.View
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isystem.presentation.R
import uz.isystem.presentation.adapter.other.MainAdapter
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.databinding.ScreenMainBinding
import uz.isystem.presentation.util.Utils

class MainScreen : BaseFragment(R.layout.screen_main) {

    private val binding by viewBinding(ScreenMainBinding::bind)

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {

        setPager()
        setActions()

    }

    private fun setActions() {
        binding.searchBtn.setOnClickListener {
            Utils.navigateWithAnimations(
                findNavController(),
                MainScreenDirections.actionMainScreenToSearchScreen()
            )
        }
    }

    private fun setPager() {
        val adapter = MainAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
        binding.viewPager.isUserInputEnabled = false

        binding.bottomNavigation.setOnItemSelectedListener {

            when (it.itemId) {
                R.id.movieID -> binding.viewPager.currentItem = 0
                R.id.serialsID -> binding.viewPager.currentItem = 1
                R.id.actingID -> binding.viewPager.currentItem = 2
                else -> binding.viewPager.currentItem = 3
            }
            true

        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> binding.bottomNavigation.selectedItemId = R.id.movieID
                    1 -> binding.bottomNavigation.selectedItemId = R.id.serialsID
                    2 -> binding.bottomNavigation.selectedItemId = R.id.actingID
                    else -> binding.bottomNavigation.selectedItemId = R.id.profileID
                }
            }
        })

    }
}