package uz.isystem.presentation.adapter.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isystem.domain.models.movie.movie_detail.crew_details.Cast
import uz.isystem.domain.models.people.details.PeopleCreditsModel
import uz.isystem.presentation.adapter.movie.MovieCreditsAdapter
import uz.isystem.presentation.adapter.other.SimilarAdapter
import uz.isystem.presentation.adapter.tv.TvCreditsAdapter
import uz.isystem.presentation.databinding.ItemCreditsBinding
import uz.isystem.presentation.databinding.ItemCrewBinding
import uz.isystem.utills.Constants

class PeopleCreditsAdapter : RecyclerView.Adapter<PeopleCreditsAdapter.ViewHolder>() {

    private var data: PeopleCreditsModel? = null

    fun setData(data: PeopleCreditsModel) {
        this.data = data
        notifyDataSetChanged()
    }

    companion object {
        private const val TYPE_MOVIES = 0
        private const val TYPE_TV = 1
    }

    inner class ViewHolder(private val binding: ItemCreditsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: List<uz.isystem.domain.models.people.details.credits.movie.Cast>?) {
            data?.let {
                val adapter = MovieCreditsAdapter()
                binding.recyclerView.adapter = adapter
                adapter.setData(data)
            }
        }

        fun bindSeries(data: List<uz.isystem.domain.models.people.details.credits.tv.Cast>?) {
            data?.let {
                val adapter = TvCreditsAdapter()
                binding.recyclerView.adapter = adapter
                adapter.setData(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCreditsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = 2

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when (position) {
            TYPE_MOVIES -> holder.bindData(data?.movies)
            TYPE_TV -> holder.bindSeries(data?.tv)
        }
    }
}