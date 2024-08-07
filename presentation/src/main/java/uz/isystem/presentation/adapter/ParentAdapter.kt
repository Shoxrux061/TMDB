package uz.isystem.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.isystem.domain.models.MovieListResponse
import uz.isystem.presentation.R
import uz.isystem.presentation.databinding.ItemParentBinding
import uz.isystem.presentation.databinding.ItemTopHomeBinding
import uz.isystem.utills.Constants

class ParentAdapter(private val context: Context) : RecyclerView.Adapter<ParentAdapter.ViewHolder>() {

    private val data = ArrayList<MovieListResponse>()

    lateinit var onClickItem: (Int) -> Unit

    lateinit var onClickChildItem: (Int) -> Unit


    fun setData(data: List<MovieListResponse>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val binding: ItemParentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val adapter = ChildAdapter()

        init {
            binding.recylerView.adapter = adapter
        }

        fun bindData(data: MovieListResponse) {
            adapter.setData(data.results)
            when (data.sortType) {
                1 -> binding.sortType.text = context.getString(R.string.popular)
                2 -> binding.sortType.text = context.getString(R.string.top_rated)
                3 -> binding.sortType.text = context.getString(R.string.upcoming)
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