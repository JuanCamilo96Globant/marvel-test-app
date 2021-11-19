package com.marvel.characters.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.marvel.characters.databinding.FragmentCharactersBinding
import com.marvel.characters.data.model.Character
import com.marvel.characters.ui.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment : Fragment() {

    private val charactersViewModel by viewModel<CharactersViewModel>()
    private lateinit var binding: FragmentCharactersBinding
    private var adapter: CharacterListAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCharactersBinding.inflate(layoutInflater)
        setupRecyclerView(
            binding.marvelCharactersRecyclerview,
            onItemClickListener
        )
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        charactersViewModel.getCharacters()
    }


    private val onItemClickListener = View.OnClickListener { itemView ->
        val id = itemView.tag.toString()
        val action = CharactersFragmentDirections.showCharacterDetail(id)
        itemView.findNavController().navigate(action)
    }

    private val onRetryClickListener = View.OnClickListener {
        binding.errorView.visibility = View.GONE
        charactersViewModel.getCharacters()
    }



    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        onItemClickListener: View.OnClickListener
    ) {
        adapter = CharacterListAdapter(
            onItemClickListener
        )
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun addObservers() {

        val charactersObserver =
            Observer<Resource<List<Character>>> { characterResource ->
                when (characterResource){
                    is Resource.Loading->{
                        startLoading()
                    }
                    is Resource.Success->{
                        stopLoading()
                        characterResource.data?.let { characters ->
                            if (characters.isNotEmpty()){
                                binding.marvelCharactersRecyclerview.visibility = View.VISIBLE
                            }else{
                                binding.marvelCharactersRecyclerview.visibility = View.INVISIBLE
                            }
                            adapter?.submitList(characters)
                        }
                    }
                    is Resource.GenericDataError->{
                        stopLoading()
                        binding.marvelCharactersRecyclerview.visibility = View.INVISIBLE
                        characterResource.errorMessage?.let {
                                errorMessage->binding.errorView.setData(errorMessage)
                        }
                        binding.errorView.setRetryClickListener(onRetryClickListener)
                        binding.errorView.visibility = View.VISIBLE
                    }
                }
            }
        charactersViewModel.characters.observe(viewLifecycleOwner, charactersObserver)

    }

    private fun startLoading(){
        binding.charactersLoading.visibility = View.VISIBLE
        binding.charactersLoading.startShimmerAnimation()
    }

    private fun stopLoading(){
        binding.charactersLoading.visibility = View.GONE
        binding.charactersLoading.stopShimmerAnimation()
    }
}