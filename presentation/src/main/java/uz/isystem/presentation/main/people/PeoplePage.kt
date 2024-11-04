package uz.isystem.presentation.main.people

import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.isystem.presentation.R
import uz.isystem.presentation.adapter.PeopleAdapter
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.databinding.PagePeopleBinding
import uz.isystem.presentation.main.MainScreenDirections

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
            changeScreen(MainScreenDirections.actionMainScreenToPeopleDetailScreen(id = it))
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

    private fun changeScreen(navDirections: NavDirections) {
        val navOptions = NavOptions.Builder()
            .setEnterAnim(R.anim.alpha_in)
            .setExitAnim(R.anim.alpha_out)
            .setPopEnterAnim(R.anim.alpha_pop_in)
            .setPopExitAnim(R.anim.alpha_pop_out)
            .build()
        findNavController().navigate(navDirections, navOptions)
    }

}
