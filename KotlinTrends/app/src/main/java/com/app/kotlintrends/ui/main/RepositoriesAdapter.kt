package com.app.kotlintrends.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.kotlintrends.R
import com.app.kotlintrends.data.model.RepositoriesListResponse
import com.app.kotlintrends.databinding.ItemRepositoryBinding
import com.app.kotlintrends.utils.openBrowser
import kotlinx.android.synthetic.main.item_repository.view.*


/**
 * Created by Emad Mohamed Oun on 21/7/2019.
 * Rytalo
 * emad.3oon@gmail.com
 */

class RepositoriesAdapter constructor(
    private val context: Context, private val repositoriesList:
    ArrayList<RepositoriesListResponse>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var repositoryFilterList = ArrayList<RepositoriesListResponse>()

    init {
        repositoryFilterList = repositoriesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemRepositoryBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_repository, parent, false
        )
        return ViewHolderHash(binding)
    }

    override fun getItemCount(): Int = repositoryFilterList.size

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        (holder as ViewHolderHash).bind(repositoryFilterList[position])


    inner class ViewHolderHash(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.M)
        fun bind(repositoryResponse: RepositoriesListResponse) {
            binding.repositoryData = repositoryResponse
            binding.executePendingBindings()
            fillViewWithData(itemView, repositoryResponse)
            listenerOnView(itemView, repositoryResponse)
        }

        private fun fillViewWithData(itemView: View, repositoryResponse: RepositoriesListResponse) {
            if (repositoryResponse.fork) {
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorGreen))
            } else {
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite))
            }
            itemView.repo_name_tv.text = repositoryResponse.name
            itemView.repo_description_tv.text = repositoryResponse.description
            itemView.repo_owner_tv.text = repositoryResponse.fullName
        }

        private fun listenerOnView(itemView: View, repositoryResponse: RepositoriesListResponse) {
            itemView.setOnClickListener {
                (context as MainActivity).openBrowser(repositoryResponse.htmlUrl)
            }
        }
    }

    fun addAll(items: List<RepositoriesListResponse>) {
        repositoryFilterList.size
        repositoryFilterList.addAll(items)
        notifyItemRangeInserted(itemCount, items.size - 1)
    }

    override fun getFilter(): Filter? {
        return object : Filter() {
            @SuppressLint("DefaultLocale")
            override fun performFiltering(charSearch: CharSequence?): FilterResults {
                if (charSearch!!.isEmpty()) {
                    repositoryFilterList = repositoriesList
                } else {
                    val resultList = ArrayList<RepositoriesListResponse>()
                    val filterPattern = charSearch.toString().toLowerCase().trim { it <= ' ' }

                    for (row in repositoriesList) {
                        if (row.name.toLowerCase().startsWith(filterPattern)) {
                            resultList.add(row)
                        }
                    }
                    repositoryFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = repositoryFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                repositoryFilterList = results?.values as ArrayList<RepositoriesListResponse>
                notifyDataSetChanged()
            }

        }
    }

}
