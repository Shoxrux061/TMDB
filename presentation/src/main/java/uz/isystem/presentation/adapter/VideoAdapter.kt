package uz.isystem.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isystem.domain.models.content.MultiContentModel
import uz.isystem.domain.models.content.images.Backdrop
import uz.isystem.domain.models.content.images.Poster
import uz.isystem.domain.models.movie.movie_detail.Result
import uz.isystem.presentation.databinding.ItemBackdropBinding
import uz.isystem.presentation.databinding.ItemContentParentBinding
import uz.isystem.presentation.databinding.ItemPosterBinding
import uz.isystem.utills.Constants

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    private val data = ArrayList<Result>()
    lateinit var onClickItem: (Int) -> Unit

    fun setData(data: List<Result>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemBackdropBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Result) {
            binding.backdrop.load(Constants.YOUTUBE_IMAGE_URL.plus(Constants.QUALITY.plus(data.key)))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBackdropBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (data.size > 10) {
            10
        } else data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}