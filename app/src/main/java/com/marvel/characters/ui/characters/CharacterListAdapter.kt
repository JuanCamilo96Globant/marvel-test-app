package com.marvel.characters.ui.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marvel.characters.data.model.Character
import com.marvel.characters.databinding.CharacterItemBinding
import com.squareup.picasso.Picasso

class CharacterListAdapter(
    private val onItemClickListener: View.OnClickListener
) :
    ListAdapter<Character,CharacterListAdapter.CharacterViewHolder>(CharacterDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.tag = getItem(position).id
        holder.itemView.setOnClickListener(onItemClickListener)
    }

    class CharacterViewHolder(
        private val binding: CharacterItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character){
            Picasso.get().load(character.photo).into(binding.ivCharacterPhoto)
            binding.tvCharacterName.text = character.name
            binding.tvEventsValue.text = character.events.toString()
            binding.tvStoriesValue.text = character.stories.toString()
            binding.tvSeriesValue.text = character.series.toString()
            binding.tvComicsValue.text = character.comics.toString()
        }

    }

    class CharacterDiffUtilCallBack : DiffUtil.ItemCallback<Character>(){
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

    }
}