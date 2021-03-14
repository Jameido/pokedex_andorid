package dev.jameido.pokedex.framework.datasource.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Luca Rossi on 28/02/2021.
 */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun providePkmnDao(appDatabase: PkmnDatabase): PkmnDao {
        return appDatabase.pkmnDao()
    }

    @Provides
    @Singleton
    fun providePkmnDatabase(@ApplicationContext appContext: Context): PkmnDatabase {
        return Room.databaseBuilder(appContext, PkmnDatabase::class.java, "pokemon-db")
                .fallbackToDestructiveMigration()
                .build()
    }
}