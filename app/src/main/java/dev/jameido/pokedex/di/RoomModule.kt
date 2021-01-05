package dev.jameido.pokedex.di

import androidx.room.Room
import dev.jameido.pokedex.framework.datasource.local.PkmnDatabase
import org.koin.dsl.module

/**
 * Created by Jameido on 05/01/2021.
 */
val roomModule = module {
    single {
        Room.databaseBuilder(get(), PkmnDatabase::class.java, "pokemon-db")
                .fallbackToDestructiveMigration()
                .build()
    }

    single { get<PkmnDatabase>().pkmnDao() }
}