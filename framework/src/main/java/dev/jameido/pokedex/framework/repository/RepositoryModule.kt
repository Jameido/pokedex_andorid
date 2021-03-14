package dev.jameido.pokedex.framework.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import dev.jameido.pokedex.data.repository.PkmnRepository
import javax.inject.Singleton

/**
 * Created by Luca Rossi on 01/03/2021.
 */
@Module
@InstallIn(ViewModelComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun providePkmnRepository(pkmnRepositoryImpl: PkmnRepositoryImpl) : PkmnRepository
}