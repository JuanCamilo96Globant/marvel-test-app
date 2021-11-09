package com.marvel.characters.di

import com.marvel.characters.repository.CharacterRepository
import com.marvel.characters.repository.CharacterRepositoryInterface
import com.marvel.characters.ui.characters.CharactersViewModel
import com.marvel.characters.ui.charactersdetails.CharacterDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel{CharactersViewModel()}

    viewModel{CharacterDetailViewModel()}

    single<CharacterRepositoryInterface> {CharacterRepository()}

}