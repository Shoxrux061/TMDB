package uz.isystem.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isystem.domain.models.MovieListResponse
import uz.isystem.presentation.databinding.ItemParentBinding
import uz.isystem.presentation.databinding.ItemTopHomeBinding
import uz.isystem.utills.Constants

class ParentAdapter : RecyclerView.Adapter<ParentAdapter.ViewHolder>() {

    private val data = ArrayList<MovieListResponse>()

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