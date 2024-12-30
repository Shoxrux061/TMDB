package uz.isystem.presentation.main.profile

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isystem.presentation.R
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.databinding.PageProfileBinding
import uz.isystem.presentation.main.profile.dialog.LoginDialog

class ProfilePage : BaseFragment(R.layout.page_profile) {

    private val binding by viewBinding(PageProfileBinding::bind)
    private var dialog: LoginDialog? = null

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {

        setDialog()
        setActions()

    }

    private fun setDialog() {
        dialog = LoginDialog()

    }

    private fun setActions() {
        binding.btnLogin.setOnClickListener {
            dialog?.show(childFragmentManager, "Login Dialog")
        }

    }
}

