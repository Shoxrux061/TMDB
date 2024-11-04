package uz.isystem.presentation.detail.people_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import uz.isystem.domain.models.people.details.PeopleDetailsResponse
import uz.isystem.presentation.R
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.databinding.ScreenPeopleDetailBinding
import uz.isystem.utills.Constants

class PeopleDetailScreen : BaseFragment(R.layout.screen_people_detail) {
    private val binding by viewBinding(ScreenPeopleDetailBinding::bind)
    private val viewModel: PeopleDetailsViewModel by viewModels()
    private val args: PeopleDetailScreenArgs by navArgs()
    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("TAGID", "onBaseViewCreated: ${args.id}")
        setAdapter()
        sendRequest()
        observe()
        listenActions()

    }

    private fun observe() {
        viewModel.successDetail.observe(viewLifecycleOwner) {
            loadDataToView(it)
        }
    }

    private fun loadDataToView(data: PeopleDetailsResponse?) {
        if (!data?.biography.isNullOrBlank()) {
            binding.textBio.text = data?.biography
        } else {
            binding.cardBio.visibility = View.GONE
            binding.textBio.visibility = View.GONE
        }
        binding.personName.text = data?.name
        binding.poster.load(Constants.IMAGE_URL.plus(data?.profile_path)) {
            placeholder(R.drawable.ic_acting)
        }
        if (data?.known_for_department == null) {
            binding.popFor.visibility = View.GONE
            binding.popForText.visibility = View.GONE
        } else {
            binding.popFor.text = data.known_for_department
        }
        when (data?.gender) {

            1 -> binding.gender.text = getString(R.string.female)
            2 -> binding.gender.text = getString(R.string.male)
            null -> {
                binding.gender.visibility = View.GONE
                binding.genderText.visibility = View.GONE
            }

            else -> binding.gender.text = getString(R.string.unknown)
        }

        if (data?.birthday != null && data.deathday != null) {
            binding.dateOfBirthText.text = getString(R.string.years_of_life)
            binding.dateOfBirth.text = data.birthday.plus(" ${data.deathday.toString()}")
        } else if (data?.birthday == null) {
            binding.dateOfBirth.visibility = View.GONE
            binding.dateOfBirthText.visibility = View.GONE
        } else {
            binding.dateOfBirth.text = data.birthday
        }

        if (data?.place_of_birth != null) {
            binding.placeOfBirth.text = data.place_of_birth
        } else {
            binding.placeOfBirth.visibility = View.GONE
            binding.placeOfBirthText.visibility = View.GONE
        }
    }

    private fun sendRequest() {
        viewModel.getPeopleDetails(args.id)
    }

    private fun setAdapter() {

    }

    private fun listenActions() {
        binding.cardBio.setOnClickListener {

            if (binding.textBio.contentDescription == "collapsed") {
                binding.textBio.contentDescription = "expanded"
                binding.textBio.maxLines = Int.MAX_VALUE
            } else {
                binding.textBio.contentDescription = "collapsed"
                binding.textBio.maxLines = 1
            }
            TransitionManager.beginDelayedTransition(binding.cardBio)
        }
    }
}
