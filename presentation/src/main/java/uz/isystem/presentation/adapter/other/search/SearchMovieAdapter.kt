package uz.isystem.presentation.adapter.other.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isystem.domain.models.movie.movie_list.Result
import uz.isystem.presentation.databinding.ItemSearchBinding
import uz.isystem.utills.Constants

class SearchMovieAdapter : RecyclerView.Adapter<SearchMovieAdapter.ViewHolder>() {

    private val data = ArrayList<Result>()
    lateinit var onClickItem: (Int) -> Unit

    fun setData(data: List<Result>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Result) {
            binding.poster.load(Constants.IMAGE_URL.plus(data.poster_path))
            binding.rating.text = data.vote_average.toString()
            binding.filmName.text = data.title
            binding.filmTitle.text = data.overview
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}