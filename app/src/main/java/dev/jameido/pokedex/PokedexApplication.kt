package dev.jameido.pokedex

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Jameido on 16/12/2020.
 */
@HiltAndroidApp
class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}