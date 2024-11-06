package uz.isystem.presentation.adapter.tv

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isystem.domain.models.series.tv_series_list.SerialsResult
import uz.isystem.presentation.databinding.ItemTopHomeBinding
import uz.isystem.utills.Constants

class SeriesTopAdapter : RecyclerView.Adapter<SeriesTopAdapter.ViewHolder>() {

    private val data = ArrayList<SerialsResult>()

    lateinit var onClickItem: (Int) -> Unit

    fun setData(data: List<SerialsResult>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun getImage(position: Int): String {
        return data[position].poster_path
    }


    inner class ViewHolder(private val binding: ItemTopHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: SerialsResult) {
            binding.poster.load(Constants.IMAGE_URL.plus(data.backdrop_path))
            binding.title.text = data.name
            binding.root.setOnClickListener {
                Log.d("TAGID", "bindData: $data")
                onClickItem.invoke(data.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTopHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}