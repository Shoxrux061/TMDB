package uz.isystem.presentation.detail.film_details

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import uz.isystem.domain.models.content.MultiContentModel
import uz.isystem.domain.models.content.images.Backdrop
import uz.isystem.domain.models.content.images.Poster
import uz.isystem.domain.models.movie.movie_detail.DetailResponse
import uz.isystem.domain.models.series.series_details.SeriesDetailResponse
import uz.isystem.presentation.R
import uz.isystem.presentation.adapter.ActorsAdapter
import uz.isystem.presentation.adapter.ContentParentAdapter
import uz.isystem.presentation.adapter.GenreAdapter
import uz.isystem.presentation.adapter.RecommAdapter
import uz.isystem.presentation.adapter.SimilarAdapter
import uz.isystem.presentation.base.BaseFragment
import uz.isystem.presentation.databinding.ScreenDetailBinding
import uz.isystem.utills.Constants

class DetailScreen : BaseFragment(R.layout.screen_detail) {

    private val adapterGenre by lazy { GenreAdapter() }
    private val binding by viewBinding(ScreenDetailBinding::bind)
    private val viewModel: DetailViewModel by viewModels()
    private val args: DetailScreenArgs by navArgs()
    private var isFirst = false
    private val adapterSimilar by lazy { SimilarAdapter() }
    private val adapterCast by lazy { ActorsAdapter() }
    private val adapterRecomm by lazy { RecommAdapter() }
    private var contentMultiData = MultiContentModel(
        posters = null,
        backdrops = null,
        videos = null
    )
    private val contentAdapter by lazy { ContentParentAdapter() }
    private var isFull = 0

    override fun onBaseViewCreated(view: View, savedInstanceState: Bundle?) {

        if (!isFirst) {
            setAdapter()

            when (args.type) {
                1 -> {
                    sendRequest()
                    observe()
                }

                2 -> {
                    sendRequestSerial()
                    observeSerial()
                }
            }
        }

    }

    private fun observeSerial() {
        viewModel.successDetailsSerial.observe(viewLifecycleOwner) {
            loadDataToViewSerial(it!!)
            Log.d("serial_status", "observeSerial: $it")
        }
    }

    private fun loadDataToViewSerial(data: SeriesDetailResponse) {
        binding.poster.load(Constants.IMAGE_URL.plus(data.posterPath))
        binding.rate.text = data.voteAverage.toString()
        binding.overView.text = data.overview
        binding.lang.text = data.originalLanguage
        binding.movieTitle.text = data.tagline
    }


    private fun sendRequestSerial() {
        viewModel.getSerial(args.id)
    }

    private fun setAdapter() {
        binding.genreRecycler.adapter = adapterGenre
        binding.actorsRecycler.adapter = adapterCast
        binding.similarRecycler.adapter = adapterSimilar
        binding.recommRecycler.adapter = adapterRecomm
        binding.contentList.adapter = contentAdapter
        binding.contentList.isUserInputEnabled = false
    }

    private fun observe() {
        viewModel.successDetail.observe(viewLifecycleOwner) {
            loadDataToView(it!!)
        }
        viewModel.successPeople.observe(viewLifecycleOwner) {
            adapterCast.setData(it!!.cast)
        }
        viewModel.successSimilar.observe(viewLifecycleOwner) {
            adapterSimilar.setData(it!!.results)
        }
        viewModel.successRecomm.observe(viewLifecycleOwner) {
            adapterRecomm.setData(it!!.results)
        }
        viewModel.successTrailer.observe(viewLifecycleOwner) {
            contentMultiData.videos = it?.results
            isFull++
            checkIsFull()
        }
        viewModel.errorTrailer.observe(viewLifecycleOwner) {
            isFull++
            checkIsFull()
        }
        viewModel.successImages.observe(viewLifecycleOwner) {
            Log.d("TAGData", "observe: $it")
            contentMultiData.posters = it?.posters
            contentMultiData.backdrops = it?.backdrops
            isFull++
            checkIsFull()
        }
        viewModel.errorImage.observe(viewLifecycleOwner) {
            isFull++
            checkIsFull()
        }
    }

    private fun checkIsFull() {
        if (isFull == 2) {
            val data = ArrayList<MultiContentModel>()
            data.add(contentMultiData)
            contentAdapter.setData(data[0])
        }
    }

    private fun loadDataToView(data: DetailResponse) {
        binding.seasonsInf.visibility = View.GONE
        binding.seasonText.visibility = View.GONE
        binding.lang.text = data.original_language.uppercase()
        binding.movieTitle.text = data.title
        binding.originalTitleText.text = data.original_title
        binding.poster.load(Constants.IMAGE_URL.plus(data.poster_path))
        binding.backgroundPoster.load(Constants.IMAGE_URL.plus(data.backdrop_path))
        binding.rate.text = data.vote_average.toString()
        binding.overview1.text = data.tagline
        binding.overview2.text = data.overview
        adapterGenre.setData(data.genres!!)
        val hours = data.runtime / 60
        val minutes = data.runtime % 60
        val filmDuration =
            "$hours${getString(R.string.hours)} $minutes${getString(R.string.minutes)}"
        binding.durationText.text = filmDuration
        binding.dateText.text = data.release_date
        if (data.status == "Released") {
            binding.statusText.setTextColor(Color.GREEN)
            binding.statusText.text = getString(R.string.released)
        } else {
            binding.statusText.setTextColor(Color.RED)
            binding.statusText.text = getString(R.string.notRealised)
        }
    }

    private fun sendRequest() {
        viewModel.getMovie(args.id)
        viewModel.getTrailer(args.id)
        viewModel.getMovieCast(args.id)
        viewModel.getSimilar(args.id)
        viewModel.getRecomm(args.id)
        viewModel.getImages(args.id)
    }
}