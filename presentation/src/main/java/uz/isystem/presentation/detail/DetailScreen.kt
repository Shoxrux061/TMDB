package uz.isystem.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import uz.isystem.domain.models.movie_detail.DetailResponse
import uz.isystem.presentation.R
import uz.isystem.presentation.adapter.ActorsAdapter
import uz.isystem.presentation.adapter.GenreAdapter
import uz.isystem.presentation.adapter.TrailerAdapter
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.databinding.ScreenDetailBinding
import uz.isystem.utills.Constants

class DetailScreen : BaseFragment(R.layout.screen_detail) {

    private val adapterGenre by lazy { GenreAdapter() }
    private val binding by viewBinding(ScreenDetailBinding::bind)
    private val viewModel: DetailViewModel by viewModels()
    private val adapterTrailer by lazy { TrailerAdapter() }
    private val args: DetailScreenArgs by navArgs()
    private var isFirst = false
    private val adapterCast by lazy { ActorsAdapter() }

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {

        setAdapter()
        if (!isFirst) {
            sendRequest()
            observe()
        }
    }

    private fun setAdapter() {
        binding.genreRecycler.adapter = adapterGenre
        binding.trailerRecycler.adapter = adapterTrailer
        binding.actorsRecycler.adapter = adapterCast
    }

    private fun observe() {
        viewModel.successDetail.observe(viewLifecycleOwner) {
            loadDataToView(it!!)
        }
        viewModel.successTrailer.observe(viewLifecycleOwner) {
            adapterTrailer.setData(it!!.results)
        }
        viewModel.successPeople.observe(viewLifecycleOwner){
            adapterCast.setData(it!!.cast)
        }
    }

    private fun loadDataToView(data: DetailResponse) {
        binding.lang.text = data.original_language.uppercase()
        binding.movieTitle.text = data.title
        binding.originalTitleText.text = data.original_title
        binding.poster.load(Constants.IMAGE_URL.plus(data.poster_path))
        binding.backgroundPoster.load(Constants.IMAGE_URL.plus(data.backdrop_path))
        binding.rate.text = data.vote_average.toString()
        binding.overview1.text = data.tagline
        binding.overview2.text = data.overview
        adapterGenre.setData(data.genres)
    }

    private fun sendRequest() {
        viewModel.getMovie(args.id)
        viewModel.getTrailer(args.id)
        viewModel.getMovieCast(args.id)
    }
}
