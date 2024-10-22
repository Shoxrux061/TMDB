package uz.isystem.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isystem.domain.models.content.MultiContentModel
import uz.isystem.domain.models.content.images.Poster
import uz.isystem.presentation.databinding.ItemContentParentBinding
import uz.isystem.presentation.databinding.ItemPosterBinding
import uz.isystem.utills.Constants

class PosterAdapter : RecyclerView.Adapter<PosterAdapter.ViewHolder>() {

    private val data = ArrayList<Poster>()
    lateinit var onClickItem: (Int) -> Unit

    fun setData(data: List<Poster>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemPosterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Poster) {
            binding.poster.load(Constants.IMAGE_URL.plus(data.file_path))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPosterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return if (data.size>10){
            10
        } else data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}