package uz.isystem.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isystem.domain.models.movie.movie_detail.Genre
import uz.isystem.domain.models.movie.movie_list.Result
import uz.isystem.presentation.databinding.ItemChildBinding
import uz.isystem.presentation.databinding.ItemGenreBinding
import uz.isystem.utills.Constants

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    private val data = ArrayList<Genre>()

    fun setData(data: List<Genre>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemGenreBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Genre) {
            binding.text.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}