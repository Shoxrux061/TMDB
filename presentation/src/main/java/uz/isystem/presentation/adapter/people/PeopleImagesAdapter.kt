package uz.isystem.presentation.adapter.people

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isystem.domain.models.people.details.images.Profile
import uz.isystem.presentation.databinding.ItemActorProfileBinding
import uz.isystem.utills.Constants

class PeopleImagesAdapter : RecyclerView.Adapter<PeopleImagesAdapter.ViewHolder>() {

    private val data = ArrayList<Profile>()

    fun setData(data: List<Profile>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()

    }

    inner class ViewHolder(private val binding: ItemActorProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Profile) {
            binding.poster.load(Constants.IMAGE_URL.plus(data.file_path))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemActorProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}