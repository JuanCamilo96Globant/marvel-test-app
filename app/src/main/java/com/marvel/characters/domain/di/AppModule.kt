package com.marvel.characters.domain.di

import com.marvel.characters.R
import com.marvel.characters.data.datasource.CharacterDataSourceImpl
import com.marvel.characters.data.datasource.CharacterDataSource
import com.marvel.characters.domain.repository.CharacterRepositoryImpl
import com.marvel.characters.domain.repository.CharacterRepository
import com.marvel.characters.data.service.ApiServiceGenerator
import com.marvel.characters.domain.mappers.*
import com.marvel.characters.domain.usecases.GetCharacterDetailsUseCase
import com.marvel.characters.domain.usecases.GetCharacterDetailsUseCaseImpl
import com.marvel.characters.domain.usecases.GetCharactersUseCase
import com.marvel.characters.domain.usecases.GetCharactersUseCaseImpl
import com.marvel.characters.ui.characters.CharactersFragment
import com.marvel.characters.ui.characters.CharactersViewModel
import com.marvel.characters.ui.charactersdetails.CharacterDetailFragment
import com.marvel.characters.ui.charactersdetails.CharacterDetailViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val viewModelModule = module {

        viewModel{CharactersViewModel(androidContext(),get())}
        scope<CharactersFragment> {
            viewModel{CharactersViewModel(androidContext(),get())}
        }

        viewModel{CharacterDetailViewModel(androidContext(),get())}
        scope<CharacterDetailFragment> {
            viewModel{CharacterDetailViewModel(androidContext(),get())}
        }

    }

    val useCasesModule = module {

        single {GetCharactersUseCaseImpl(get())}
        single<GetCharactersUseCase> {
            return@single GetCharactersUseCaseImpl(get())
        }

        single {GetCharacterDetailsUseCaseImpl(get())}
        single<GetCharacterDetailsUseCase> {
            return@single GetCharacterDetailsUseCaseImpl(get())
        }

    }

    val repositoryModule = module {

        single {CharacterRepositoryImpl(get(),get(),get(),Dispatchers.IO)}
        single<CharacterRepository> {
            return@single CharacterRepositoryImpl(get(),get(),get(),Dispatchers.IO)
        }

    }

    val mappersModule = module{

        single { ResourceCharacterListMapperImpl(get()) }
        single <ResourceCharacterListMapper>{
            return@single ResourceCharacterListMapperImpl(get())
        }

        single { CharacterListMapperImpl(get()) }
        single <CharacterListMapper>{
            return@single CharacterListMapperImpl(get())
        }

        single { CharacterMapperImpl() }
        single <CharacterMapper>{
            return@single CharacterMapperImpl()
        }

        single { ResourceCharacterDetailMapperImpl(get()) }
        single <ResourceCharacterDetailMapper>{
            return@single ResourceCharacterDetailMapperImpl(get())
        }

        single { CharacterDetailMapperImpl(get()) }
        single <CharacterDetailMapper>{
            return@single CharacterDetailMapperImpl(get())
        }

        single { ItemListToNameListMapperImpl(get()) }
        single <ItemListToNameListMapper>{
            return@single ItemListToNameListMapperImpl(get())
        }

        single { ItemToNameMapperImpl() }
        single <ItemToNameMapper>{
            return@single ItemToNameMapperImpl()
        }

    }

    val dataSourceModule = module {

        single {CharacterDataSourceImpl(get(),androidContext())}
        single<CharacterDataSource> {return@single CharacterDataSourceImpl(get(),androidContext())}

    }


    val networkApiServiceModule = module {

        single { ApiServiceGenerator(androidContext().getString(R.string.url_api)) }

    }

}
