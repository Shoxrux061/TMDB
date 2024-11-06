package uz.isystem.presentation.adapter.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isystem.domain.models.movie.movie_detail.Result
import uz.isystem.presentation.databinding.ItemYoutubeBinding
import uz.isystem.utills.Constants

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    private val data = ArrayList<Result>()
    lateinit var onClickItem: (Int) -> Unit

    fun setData(data: List<Result>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemYoutubeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Result) {
            binding.image.load(
                Constants.YOUTUBE_IMAGE_URL.plus(data.key).plus(Constants.QUALITY)
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemYoutubeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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