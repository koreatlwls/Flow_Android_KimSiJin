package com.example.flow.ui.searchrecord

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flow.databinding.ItemSearchRecordBinding
import com.example.flow.model.SearchRecord

class SearchRecordAdapter(
    private val onItemClick: (String) -> Unit
) : ListAdapter<SearchRecord, SearchRecordAdapter.ViewHolder>(diffUtil) {

    class ViewHolder(private val binding: ItemSearchRecordBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(searchRecord: SearchRecord) {
            binding.searchRecord = searchRecord
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSearchRecordBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
        holder.itemView.setOnClickListener {
            onItemClick.invoke(currentList[holder.absoluteAdapterPosition].query)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SearchRecord>() {
            override fun areItemsTheSame(oldItem: SearchRecord, newItem: SearchRecord): Boolean {
                return oldItem.query == newItem.query
            }

            override fun areContentsTheSame(oldItem: SearchRecord, newItem: SearchRecord): Boolean {
                return oldItem == newItem
            }

        }
    }

}