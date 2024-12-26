package uz.isystem.presentation.main.people

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isystem.presentation.R
import uz.isystem.presentation.adapter.people.PeopleAdapter
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.databinding.PagePeopleBinding
import uz.isystem.presentation.main.MainScreenDirections
import uz.isystem.presentation.util.Utils

class PeoplePage : BaseFragment(R.layout.page_people) {

    private val binding by viewBinding(PagePeopleBinding::bind)
    private val viewModel: PeopleViewModel by viewModels()
    private val adapter by lazy { PeopleAdapter() }
    private var isFirst = false

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {

        setAdapter()
        if (!isFirst) {
            sendRequest()
            observe()
        }
        listenActions()

    }

    private fun setAdapter() {
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.adapter = adapter
    }

    private fun listenActions() {
        adapter.onClickItem = {
            Utils.navigateWithAnimations(
                findNavController(),
                MainScreenDirections.actionMainScreenToPeopleDetailScreen(it)
            )
        }
    }

    private fun sendRequest() {
        viewModel.getPeopleList()
    }

    private fun observe() {
        viewModel.success.observe(viewLifecycleOwner) {
            adapter.setData(it!!.results)
        }
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, "False", Toast.LENGTH_SHORT).show()
        }
    }


}
