package com.marvel.characters.ui.customviews.toogleview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marvel.characters.databinding.StringItemBinding

class NameListAdapter: ListAdapter<String, NameListAdapter.NameViewHolder>(NameDiffUtilCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val binding = StringItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return NameViewHolder(binding)
    }


    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NameViewHolder(
        private val binding: StringItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(name: String){
            binding.tvName.text = name
        }

    }

    class NameDiffUtilCallBack : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }
}