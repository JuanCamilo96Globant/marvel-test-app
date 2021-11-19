package com.marvel.characters

import android.app.Application
import com.marvel.characters.domain.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@MarvelApplication)
            modules(
                AppModule.networkApiServiceModule,
                AppModule.dataSourceModule,
                AppModule.repositoryModule,
                AppModule.mappersModule,
                AppModule.useCasesModule,
                AppModule.viewModelModule
            )
        }
    }
}