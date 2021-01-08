package dev.jameido.pokedex

import android.app.Application
import dev.jameido.pokedex.framework.di.*
import dev.jameido.pokedex.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Jameido on 16/12/2020.
 */
class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokedexApplication)
            modules(listOf(
                    retrofitModule,
                    roomModule,
                    dataSourceModule,
                    repositoryModule,
                    useCaseModule,
                    pagingSourceModule,
                    viewModelModule
            ))
        }
    }
}