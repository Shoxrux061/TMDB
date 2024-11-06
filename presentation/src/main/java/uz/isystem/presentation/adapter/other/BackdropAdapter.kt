package uz.isystem.presentation.adapter.other

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.isystem.domain.models.content.images.Backdrop
import uz.isystem.presentation.databinding.ItemBackdropBinding
import uz.isystem.utills.Constants

class BackdropAdapter : RecyclerView.Adapter<BackdropAdapter.ViewHolder>() {

    private val data = ArrayList<Backdrop>()
    lateinit var onClickItem: (Int) -> Unit

    fun setData(data: List<Backdrop>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemBackdropBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Backdrop) {
            binding.backdrop.load(Constants.IMAGE_URL.plus(data.file_path))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBackdropBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {

        return if (data.size>10){
            10
        } else data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}