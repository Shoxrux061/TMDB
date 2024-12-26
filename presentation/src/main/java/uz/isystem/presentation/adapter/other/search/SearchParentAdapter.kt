package uz.isystem.presentation.adapter.other.search

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.isystem.domain.models.movie.movie_list.MovieListResponse
import uz.isystem.domain.models.other.SearchModel
import uz.isystem.domain.models.people.people_list.PeopleListResponse
import uz.isystem.domain.models.series.tv_series_list.SeriesResponse
import uz.isystem.presentation.databinding.ItemSearchRecyclerBinding

class SearchParentAdapter(val context: Context) :
    RecyclerView.Adapter<SearchParentAdapter.SearchViewHolder>() {

    private var data: SearchModel? = null
    lateinit var onClickItem: (Int) -> Unit

    companion object {
        private const val TYPE_MOVIE = 0
        private const val TYPE_SERIAL = 1
        private const val TYPE_PEOPLE = 2
    }

    fun setData(data: SearchModel) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class SearchViewHolder(private val binding: ItemSearchRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindMovie(data: MovieListResponse?) {
            data?.let {
                val adapter = SearchMovieAdapter()
                binding.root.layoutManager = LinearLayoutManager(context)
                binding.root.adapter = adapter
                adapter.setData(it.results)
            }
        }

        fun bindSerial(data: SeriesResponse?) {
            data?.let {
                val adapter = SearchSerialAdapter()
                binding.root.layoutManager = LinearLayoutManager(context)
                binding.root.adapter = adapter
                adapter.setData(it.results)
            }
        }

        fun bindPeople(data: PeopleListResponse?) {
            data?.let {
                val adapter = SearchPeopleAdapter()
                binding.root.layoutManager = GridLayoutManager(context, 3)
                binding.root.layoutParams.width = LayoutParams.WRAP_CONTENT
                binding.root.adapter = adapter
                adapter.setData(it.results)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {

        val binding =
            ItemSearchRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        when (position) {
            TYPE_MOVIE -> holder.bindMovie(data?.movieData)
            TYPE_SERIAL -> holder.bindSerial(data?.serialData)
            TYPE_PEOPLE -> holder.bindPeople(data?.peopleData)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> TYPE_MOVIE
            1 -> TYPE_SERIAL
            2 -> TYPE_PEOPLE
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}