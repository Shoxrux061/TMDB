package uz.isystem.presentation.detail.people_details

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isystem.presentation.R
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.databinding.ScreenPeopleDetailBinding

class PeopleDetailScreen : BaseFragment(R.layout.screen_people_detail){
    private val binding by viewBinding(ScreenPeopleDetailBinding::bind)
    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}
