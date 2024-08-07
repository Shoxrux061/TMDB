package uz.isystem.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.isystem.presentation.main.home.HomePage
import uz.isystem.presentation.main.people.PeoplePage
import uz.isystem.presentation.main.profile.ProfilePage
import uz.isystem.presentation.main.serials.SearialsPage

class MainAdapter(fragmentManager: FragmentManager, ls: Lifecycle) :
    FragmentStateAdapter(fragmentManager, ls) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> HomePage()
        1 -> SearialsPage()
        2 -> PeoplePage()
        else -> ProfilePage()
    }
}