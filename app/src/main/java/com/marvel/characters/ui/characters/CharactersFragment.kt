package com.marvel.characters.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvel.characters.R
import com.marvel.characters.databinding.FragmentCharactersBinding
import com.marvel.characters.model.BaseData
import com.marvel.characters.model.BaseResponse
import com.marvel.characters.model.Character
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment() {

    private val charactersViewModel by viewModel<CharactersViewModel>()
    private lateinit var binding: FragmentCharactersBinding
    private var adapter: CharacterRecyclerViewAdapter? = null
    private var itemDetailFragmentContainer: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCharactersBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemDetailFragmentContainer = view.findViewById(R.id.item_detail_nav_container)
        addObservers()
        charactersViewModel.getCharacters()
    }

    val onItemClickListener = View.OnClickListener { itemView ->
        /*val item = itemView.tag as PlaceholderContent.PlaceholderItem
        val bundle = Bundle()
        bundle.putString(
            CharacterDetailFragment.ARG_ITEM_ID,
            item.id
        )
        if (itemDetailFragmentContainer != null) {
            itemDetailFragmentContainer!!.findNavController()
                .navigate(R.id.fragment_item_detail, bundle)
        } else {
            itemView.findNavController().navigate(R.id.show_character_detail, bundle)
        }*/
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        onItemClickListener: View.OnClickListener,
        characters: List<Character>?
    ) {
        if (adapter == null) {
            adapter = CharacterRecyclerViewAdapter(
                characters,
                onItemClickListener
            )
            recyclerView.layoutManager =  LinearLayoutManager(context)
            recyclerView.adapter = adapter
        } else {
            adapter?.characters = characters
            adapter?.notifyDataSetChanged()
        }
    }

    private fun addObservers() {

        val charactersObserver =
            Observer<BaseResponse<BaseData<Character>>?> { characterResponse ->
                binding.marvelCharactersRecyclerview?.let {
                    setupRecyclerView(
                        it,
                        onItemClickListener,
                        characterResponse.data.results
                    )
                }
            }
        charactersViewModel.characters.observe(viewLifecycleOwner, charactersObserver)

    }
}