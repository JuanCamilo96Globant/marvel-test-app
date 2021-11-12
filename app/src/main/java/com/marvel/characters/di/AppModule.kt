package com.marvel.characters.di

import com.marvel.characters.datasource.CharacterDataSource
import com.marvel.characters.datasource.CharacterDataSourceInterface
import com.marvel.characters.repository.CharacterRepository
import com.marvel.characters.repository.CharacterRepositoryInterface
import com.marvel.characters.service.ApiServiceGenerator
import com.marvel.characters.ui.characters.CharactersViewModel
import com.marvel.characters.ui.charactersdetails.CharacterDetailViewModel
import com.marvel.characters.utils.network.NetworkConnectivityUtils
import com.marvel.characters.utils.network.NetworkConnectivityUtilsInterface
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    val viewModelModule = module {

        viewModel{CharactersViewModel(androidContext(),get<CharacterRepositoryInterface>())}

        viewModel{CharacterDetailViewModel(androidContext(),get<CharacterRepositoryInterface>())}

    }

    val repositoryModule = module {

        single {CharacterRepository(get<CharacterDataSourceInterface> (),Dispatchers.IO)}
        single<CharacterRepositoryInterface> {return@single CharacterRepository(get <CharacterDataSourceInterface>(),Dispatchers.IO)}

    }

    val dataSourceModule = module {

        single {CharacterDataSource(get<ApiServiceGenerator>())}
        single<CharacterDataSourceInterface> {return@single CharacterDataSource(get<ApiServiceGenerator>())}

    }

    val networkApiServiceModule = module {

        single {NetworkConnectivityUtils(androidContext())}
        single<NetworkConnectivityUtilsInterface> {return@single NetworkConnectivityUtils(androidContext())}

        single { ApiServiceGenerator("http://gateway.marvel.com/v1/public/",get <NetworkConnectivityUtilsInterface>()) }

    }


}
