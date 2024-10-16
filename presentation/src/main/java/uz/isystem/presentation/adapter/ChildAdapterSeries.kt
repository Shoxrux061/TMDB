package uz.isystem.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isystem.domain.models.series.tv_series_list.SerialsResult
import uz.isystem.presentation.databinding.ItemChildBinding
import uz.isystem.utills.Constants

class ChildAdapterSeries : RecyclerView.Adapter<ChildAdapterSeries.ViewHolder>() {

    private val data = ArrayList<SerialsResult>()
    lateinit var onClickItem: (Int) -> Unit

    fun setData(data: List<SerialsResult>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemChildBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: SerialsResult) {
            binding.poster.load(Constants.IMAGE_URL.plus(data.poster_path))
            binding.title.text = data.name
            binding.rating.text = data.vote_average.toString()
            binding.root.setOnClickListener {
                onClickItem.invoke(data.id)
            }
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