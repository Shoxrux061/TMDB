package uz.isystem.presentation.adapter.other.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isystem.domain.models.series.tv_series_list.SerialsResult
import uz.isystem.presentation.databinding.ItemSearchBinding
import uz.isystem.utills.Constants

class SearchSerialAdapter : RecyclerView.Adapter<SearchSerialAdapter.ViewHolder>() {

    private val data = ArrayList<SerialsResult>()
    lateinit var onClickItem: (Int) -> Unit

    fun setData(data: List<SerialsResult>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: SerialsResult) {
            binding.poster.load(Constants.IMAGE_URL.plus(data.poster_path))
            binding.rating.text = data.vote_average.toString()
            binding.filmName.text = data.original_name
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