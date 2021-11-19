package com.marvel.characters.ui.charactersdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marvel.characters.databinding.FragmentCharacterDetailsBinding
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.AppBarLayout
import com.marvel.characters.R
import com.marvel.characters.data.model.CharacterDetail
import com.marvel.characters.ui.utils.Resource
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailFragment : Fragment() {

    private val characterDetailViewModel by viewModel<CharacterDetailViewModel>()
    private lateinit var binding: FragmentCharacterDetailsBinding
    private val args: CharacterDetailFragmentArgs by navArgs()
    private lateinit var characterId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)

        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        binding.appBar.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (Math.abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                    //Collapse
                    binding.cvProfile.visibility = View.VISIBLE
                } else {
                    //Expanded
                    binding.cvProfile.visibility = View.INVISIBLE
                }
            }
        )
        return binding.root
    }

    private val onRetryClickListener = View.OnClickListener {
        binding.errorView.visibility = View.GONE
        characterDetailViewModel.getCharacter(characterId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addObservers()
        characterId = args.characterId
        characterDetailViewModel.getCharacter(characterId)
    }

    private fun updateContent(characterDetail: CharacterDetail) {
        binding.toolbarLayout.title = characterDetail.name
        Picasso.get().load(characterDetail.photo).into(binding.ivCharacterPhoto)
        Picasso.get().load(characterDetail.photo).into(binding.ivProfilePhoto)
        binding.tvDescription.text = characterDetail.description
        binding.tlvComics.setUp(getString(R.string.comics), characterDetail.comics)
        binding.tlvSeries.setUp(getString(R.string.series), characterDetail.series)
        binding.tlvEvents.setUp(getString(R.string.events), characterDetail.events)
        binding.tlvStories.setUp(getString(R.string.stories), characterDetail.stories)
    }


    private fun addObservers() {

        val characterDetailObserver =
            Observer<Resource<CharacterDetail>> { characterResource ->
                when (characterResource) {
                    is Resource.Loading -> {
                        startLoading()
                    }
                    is Resource.Success -> {
                        stopLoading()
                        characterResource.data?.let { characterDetail ->
                            updateContent(characterDetail)
                            binding.characterDetailContainer.visibility = View.VISIBLE
                        }
                    }
                    is Resource.GenericDataError -> {
                        stopLoading()
                        characterResource.errorMessage?.let { errorMessage ->
                            binding.errorView.setData(
                                errorMessage
                            )
                        }
                        binding.errorView.setRetryClickListener(onRetryClickListener)
                        binding.errorView.visibility = View.VISIBLE

                    }
                }
            }
        characterDetailViewModel.characterDetail.observe(
            viewLifecycleOwner,
            characterDetailObserver
        )

    }

    private fun startLoading(){
        binding.characterDetailLoading.visibility = View.VISIBLE
        binding.characterDetailLoading.startShimmerAnimation()
    }

    private fun stopLoading(){
        binding.characterDetailLoading.visibility = View.GONE
        binding.characterDetailLoading.stopShimmerAnimation()
    }
}