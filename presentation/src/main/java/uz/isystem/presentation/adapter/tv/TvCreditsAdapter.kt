package uz.isystem.presentation.adapter.tv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isystem.domain.models.people.details.credits.tv.Cast
import uz.isystem.presentation.databinding.ItemChildBinding
import uz.isystem.utills.Constants

class TvCreditsAdapter : RecyclerView.Adapter<TvCreditsAdapter.ViewHolder>() {

    private val data = ArrayList<Cast>()

    fun setData(data: List<Cast>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemChildBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Cast) {
            binding.rating.text = data.vote_average.toString()
            binding.title.text = data.name
            binding.poster.load(Constants.IMAGE_URL.plus(data.poster_path))
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