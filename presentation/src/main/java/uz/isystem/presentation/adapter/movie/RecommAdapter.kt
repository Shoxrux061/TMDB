package uz.isystem.presentation.adapter.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isystem.domain.models.movie.movie_detail.rec.RecommResult
import uz.isystem.presentation.databinding.ItemChildBinding
import uz.isystem.utills.Constants

class RecommAdapter : RecyclerView.Adapter<RecommAdapter.ViewHolder>() {

    private val data = ArrayList<RecommResult>()

    fun setData(data: List<RecommResult>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemChildBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: RecommResult) {
            binding.poster.load(Constants.IMAGE_URL.plus(data.poster_path))
            binding.title.text = data.title
            binding.rating.text = data.vote_average.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}