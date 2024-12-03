package uz.isystem.presentation.adapter.people

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginEnd
import androidx.recyclerview.widget.RecyclerView
import uz.isystem.domain.models.people.details.ids.ExternalIdItem
import uz.isystem.domain.models.people.details.ids.Platform
import uz.isystem.presentation.R
import uz.isystem.presentation.databinding.ItemPersonLinksBinding


class PeopleIdsAdapter(private val context: Context) :
    RecyclerView.Adapter<PeopleIdsAdapter.ViewHolder>() {

    private val data = ArrayList<ExternalIdItem>()

    lateinit var onClickItem: (Int) -> Unit

    fun setData(data: List<ExternalIdItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemPersonLinksBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: ExternalIdItem) {
            when (data.platform) {
                Platform.Facebook -> {
                    binding.cardColor.setCardBackgroundColor(context.getColor(R.color.facebook_color))
                    binding.image.setImageResource(R.drawable.ic_facebook)
                }

                Platform.TikTok -> {
                    binding.cardColor.setCardBackgroundColor(context.getColor(R.color.tik_tok_color))
                    binding.image.setImageResource(R.drawable.ic_tiktok)
                }

                Platform.Wiki -> {
                    binding.cardColor.setCardBackgroundColor(context.getColor(R.color.wiki_color))
                    binding.image.setImageResource(R.drawable.ic_wiki)
                }

                Platform.HomePage -> {
                    binding.cardColor.setCardBackgroundColor(context.getColor(R.color.home_page_color))
                    binding.image.setImageResource(R.drawable.ic_website)
                }

                Platform.IMDB -> {
                    binding.cardColor.setCardBackgroundColor(context.getColor(R.color.imdb_color))
                    binding.image.setImageResource(R.drawable.ic_imdb)
                }

                Platform.YouTube -> {
                    binding.cardColor.setCardBackgroundColor(context.getColor(R.color.youtube_color))
                    binding.image.setImageResource(R.drawable.ic_youtube)
                }

                Platform.X -> {
                    binding.cardColor.setCardBackgroundColor(context.getColor(R.color.twitter_color))
                    binding.image.setImageResource(R.drawable.ic_x_logo)
                }

                Platform.Instagram -> {
                    binding.cardColor.setCardBackgroundColor(context.getColor(R.color.instagram_color))
                    binding.image.setImageResource(R.drawable.ic_instagram)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPersonLinksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}