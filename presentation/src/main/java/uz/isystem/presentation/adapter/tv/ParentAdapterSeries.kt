package uz.isystem.presentation.adapter.tv

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.isystem.domain.models.series.tv_series_list.SeriesResponse
import uz.isystem.presentation.R
import uz.isystem.presentation.databinding.ItemParentBinding

class ParentAdapterSeries(private val context: Context) :
    RecyclerView.Adapter<ParentAdapterSeries.ViewHolder>() {

    private val data = ArrayList<SeriesResponse>()

    lateinit var onClickItem: (Int) -> Unit

    lateinit var onClickChildItem: (Int) -> Unit


    fun setData(data: List<SeriesResponse>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemParentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val adapter = ChildAdapterSeries()

        init {
            binding.recylerView.adapter = adapter
        }

        fun bindData(data: SeriesResponse) {
            adapter.setData(data.results)
            when (data.sortType) {
                0 -> binding.sortType.text = context.getString(R.string.airing_today)
                1 -> binding.sortType.text = context.getString(R.string.popular)
                2 -> binding.sortType.text = context.getString(R.string.top_rated)
                3 -> binding.sortType.text = context.getString(R.string.on_thr_air)
            }
            binding.btnSeeAll.setOnClickListener {
                onClickItem.invoke(data.sortType)
            }
            adapter.onClickItem = {
                onClickChildItem.invoke(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemParentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}