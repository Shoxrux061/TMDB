package uz.isystem.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isystem.domain.models.movie.movie_detail.Genre
import uz.isystem.domain.models.movie.movie_detail.crew_details.Cast
import uz.isystem.domain.models.movie.movie_list.Result
import uz.isystem.presentation.databinding.ItemChildBinding
import uz.isystem.presentation.databinding.ItemCrewBinding
import uz.isystem.presentation.databinding.ItemGenreBinding
import uz.isystem.utills.Constants

class ActorsAdapter : RecyclerView.Adapter<ActorsAdapter.ViewHolder>() {

    private val data = ArrayList<Cast>()

    fun setData(data: List<Cast>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCrewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Cast) {
            binding.profession.text = data.character
            binding.realName.text = data.name
            binding.poster.load(Constants.IMAGE_URL.plus(data.profile_path))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCrewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}