package uz.isystem.presentation.detail.people_details

import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.google.android.material.tabs.TabLayout
import uz.isystem.domain.models.people.details.PeopleCreditsModel
import uz.isystem.domain.models.people.details.PeopleDetailsResponse
import uz.isystem.domain.models.people.details.ids.ExternalIdItem
import uz.isystem.domain.models.people.details.ids.ExternalIdsResponse
import uz.isystem.domain.models.people.details.ids.Platform
import uz.isystem.presentation.R
import uz.isystem.presentation.adapter.people.PeopleCreditsAdapter
import uz.isystem.presentation.adapter.people.PeopleIdsAdapter
import uz.isystem.presentation.adapter.people.PeopleImagesAdapter
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.databinding.ScreenPeopleDetailBinding
import uz.isystem.utills.Constants

class PeopleDetailScreen : BaseFragment(R.layout.screen_people_detail) {
    private val binding by viewBinding(ScreenPeopleDetailBinding::bind)
    private val viewModel: PeopleDetailsViewModel by viewModels()
    private val peopleImagesAdapter by lazy { PeopleImagesAdapter() }
    private val args: PeopleDetailScreenArgs by navArgs()
    private var dataCount = 0
    private var creditsData = PeopleCreditsModel()
    private val creditsAdapter by lazy { PeopleCreditsAdapter() }
    private val idAdapter by lazy {
        PeopleIdsAdapter(requireContext())
    }

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {

        setAdapter()
        sendRequest()
        observe()
        listenActions()

    }

    private fun setTabs(dataSize: Int) {
        for (i in 0 until dataSize) {
            val tab = binding.tabs.newTab().apply {
                text =
                    when (i) {
                        0 -> "Movies"
                        else -> "Serials"
                    }
            }
            binding.tabs.addTab(tab)
        }
    }

    private fun observe() {
        viewModel.successDetail.observe(viewLifecycleOwner) {
            loadDataToView(it)
        }
        viewModel.successImages.observe(viewLifecycleOwner) {
            peopleImagesAdapter.setData(it!!.profiles!!)
        }

        viewModel.successMovie.observe(viewLifecycleOwner) {
            creditsData.movies = it?.cast!!
            dataCount++
            checkIsFull()
        }
        viewModel.successTv.observe(viewLifecycleOwner) {
            creditsData.tv = it?.cast!!
            dataCount++
            checkIsFull()
        }
        viewModel.errorMovie.observe(viewLifecycleOwner) {
            dataCount++
            checkIsFull()
        }
        viewModel.errorTv.observe(viewLifecycleOwner) {
            dataCount++
            checkIsFull()
        }

        viewModel.successId.observe(viewLifecycleOwner) {
            Log.d("Tag12", "observe: $it")
            setIds(it)
        }
    }

    private fun setIds(data: ExternalIdsResponse?) {

        val idList = mutableListOf<ExternalIdItem>()

        data?.instagram_id?.let {
            idList.add(ExternalIdItem(Platform.Instagram, id = data.instagram_id))
        }
        data?.imdb_id?.let {
            idList.add(ExternalIdItem(platform = Platform.IMDB, id = data.imdb_id))
        }
        data?.tiktok_id?.let {
            idList.add(ExternalIdItem(platform = Platform.TikTok, id = data.tiktok_id))
        }
        data?.facebook_id?.let {
            idList.add(ExternalIdItem(Platform.Facebook, id = data.facebook_id))
        }
        data?.twitter_id?.let {
            idList.add(ExternalIdItem(Platform.X, id = data.twitter_id))
        }
        data?.youtube_id?.let {
            idList.add(ExternalIdItem(Platform.YouTube, id = data.youtube_id))
        }
        data?.wikidata_id?.let {
            idList.add(ExternalIdItem(Platform.Wiki, id = data.wikidata_id))
        }
        idAdapter.setData(idList)
    }

    private fun checkIsFull() {
        if (dataCount == 2) {
            var dataSize = 0
            if (creditsData.tv != null) {
                dataSize++
            }
            if (creditsData.movies != null) {
                dataSize++
            }
            setTabs(dataSize)
            Log.d("TAGData", "checkIsFull: $creditsData")
            creditsAdapter.setData(creditsData)
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
        viewModel.getPeopleImages(args.id)
        viewModel.getPeopleMovie(args.id)
        viewModel.getPeopleTv(args.id)
        viewModel.getPeopleIds(args.id)
    }

    private fun setAdapter() {
        binding.profilesRecycler.adapter = peopleImagesAdapter
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.adapter = creditsAdapter
        binding.personLinkRecyclerView.adapter = idAdapter
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
            TransitionManager.beginDelayedTransition(binding.root)
        }

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> binding.viewPager.currentItem = 0
                    1 -> binding.viewPager.currentItem = 1
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
    }
}
