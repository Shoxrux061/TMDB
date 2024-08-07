package uz.isystem.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isystem.domain.models.Result
import uz.isystem.presentation.databinding.ItemTopHomeBinding
import uz.isystem.utills.Constants

class HomeTopAdapter : RecyclerView.Adapter<HomeTopAdapter.ViewHolder>() {

    private val data = ArrayList<Result>()

    fun setData(data: List<Result>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun getImage(position: Int): String {
        return data[position].poster_path
    }


    inner class ViewHolder(private val binding: ItemTopHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Result) {
            binding.poster.load(Constants.IMAGE_URL.plus(data.backdrop_path))
            binding.title.text = data.title
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