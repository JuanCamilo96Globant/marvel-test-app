package com.marvel.characters.ui.characters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marvel.characters.R
import com.marvel.characters.databinding.CharacterCardABinding
import com.marvel.characters.databinding.CharacterItemBinding
import com.marvel.characters.model.Character
import com.squareup.picasso.Picasso

class CharacterRecyclerViewAdapter(
    var characters: List<Character>?,
    private val onItemClickListener: View.OnClickListener,
) :
    RecyclerView.Adapter<CharacterRecyclerViewAdapter.CharacterViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharacterCardABinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        characters?.get(position)?.let {
            holder.bind(it)
            holder.itemView.setOnClickListener(onItemClickListener)
        }
    }

    override fun getItemCount(): Int {
        return characters?.size ?: 0
    }

    class CharacterViewHolder(
        private val binding: CharacterCardABinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character){
            Picasso.get().load(with(character.thumbnail){
                "$path.$extension"
            }).into(binding.ivCharacterPhoto)
            binding.tvCharacterName.text = character.name
            binding.tvEventsValue.text = character.events.items.size.toString() ?: "0"
            binding.tvStoriesValue.text = character.stories.items.size.toString() ?: "0"
            binding.tvSeriesValue.text = character.series.items.size.toString() ?: "0"
            binding.tvComicsValue.text = character.comics.items.size.toString() ?: "0"
        }

    }
}