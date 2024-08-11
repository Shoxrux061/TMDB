package uz.isystem.presentation.main.people

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isystem.presentation.R
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.databinding.PagePeopleBinding

class PeoplePage : BaseFragment(R.layout.page_people){

    private val binding by viewBinding(PagePeopleBinding::bind)
    private val viewModel : PeopleViewModel by viewModels()
    private var isFirst = false

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {

        if(!isFirst){
            sendRequest()
            observe()
        }

    }

    private fun sendRequest() {
        viewModel.getPeopleList()
    }
    private fun observe() {
        viewModel.success.observe(viewLifecycleOwner){
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
        viewModel.error.observe(viewLifecycleOwner){
            Toast.makeText(context, "False", Toast.LENGTH_SHORT).show()
        }
    }

}
