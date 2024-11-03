package uz.isystem.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.isystem.domain.models.content.MultiContentModel
import uz.isystem.domain.models.content.images.Backdrop
import uz.isystem.domain.models.content.images.Poster
import uz.isystem.domain.models.movie.movie_detail.Result
import uz.isystem.presentation.databinding.ItemContentParentBinding

class ContentParentAdapter(
) : RecyclerView.Adapter<ContentParentAdapter.ContentViewHolder>() {

    private var multiContentModel: MultiContentModel? = null
    private var itemCount = 0

    companion object {
        private const val TYPE_VIDEOS = 0
        private const val TYPE_POSTERS = 1
        private const val TYPE_BACKDROPS = 2
    }

    fun setData(data: MultiContentModel) {
        this.multiContentModel = data
        notifyDataSetChanged()
    }

    inner class ContentViewHolder(private val binding: ItemContentParentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindPosters(posters: List<Poster>?) {
            posters?.let {
                itemCount++
                val adapter = PosterAdapter()
                binding.root.adapter = adapter
                adapter.setData(it)
            }
        }

        fun bindBackdrops(backdrops: List<Backdrop>?) {
            backdrops?.let {
                itemCount++
                val adapter = BackdropAdapter()
                binding.root.adapter = adapter
                adapter.setData(it)
            }
        }

        fun bindVideos(videos: List<Result>?) {
            videos?.let {
                itemCount++
                val adapter = VideoAdapter()
                binding.root.adapter = adapter
                adapter.setData(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val binding =
            ItemContentParentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        when (position) {
            TYPE_POSTERS -> holder.bindPosters(multiContentModel?.posters)
            TYPE_BACKDROPS -> holder.bindBackdrops(multiContentModel?.backdrops)
            TYPE_VIDEOS -> holder.bindVideos(multiContentModel?.videos)
        }
    }

    override fun getItemCount(): Int = 3

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_POSTERS
            1 -> TYPE_BACKDROPS
            2 -> TYPE_VIDEOS
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}
