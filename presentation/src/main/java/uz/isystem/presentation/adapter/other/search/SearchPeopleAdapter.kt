package uz.isystem.presentation.adapter.other.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isystem.domain.models.people.people_list.PeopleResult
import uz.isystem.presentation.databinding.ItemPeopleBinding
import uz.isystem.utills.Constants

class SearchPeopleAdapter : RecyclerView.Adapter<SearchPeopleAdapter.ViewHolder>() {

    private val data = ArrayList<PeopleResult>()
    lateinit var onClickItem: (Int) -> Unit

    fun setData(data: List<PeopleResult>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemPeopleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: PeopleResult) {
            binding.poster.load(Constants.IMAGE_URL.plus(data.profile_path))
            binding.postName.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPeopleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}