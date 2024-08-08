package uz.isystem.presentation.detail

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isystem.presentation.R
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.databinding.ScreenDetailBinding

class DetailScreen : BaseFragment(R.layout.screen_detail) {

    private val binding by viewBinding(ScreenDetailBinding::bind)

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {



    }
}
