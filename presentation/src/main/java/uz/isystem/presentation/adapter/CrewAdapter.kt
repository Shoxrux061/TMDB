package uz.isystem.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.isystem.domain.models.movie_detail.Genre
import uz.isystem.domain.models.movie_detail.crew_details.PeopleDetailResponse
import uz.isystem.presentation.databinding.ItemCrewBinding
import uz.isystem.presentation.databinding.ItemGenreBinding

class CrewAdapter : RecyclerView.Adapter<CrewAdapter.ViewHolder>() {

    private val data = ArrayList<PeopleDetailResponse>()

    fun setData(data: List<PeopleDetailResponse>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCrewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: PeopleDetailResponse) {

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