package dev.jameido.pokedex.framework.datasource

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dev.jameido.pokedex.data.datasource.LocalPkmnDataSource
import dev.jameido.pokedex.data.datasource.NetworkPkmnDataSource
import dev.jameido.pokedex.framework.datasource.local.LocalPkmnDataSourceImpl
import dev.jameido.pokedex.framework.datasource.network.NetworkPkmnDataSourceImpl
import javax.inject.Singleton

/**
 * Created by Luca Rossi on 01/03/2021.
 */
@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun provideNetworkDataSource(networkPkmnDataSourceImpl: NetworkPkmnDataSourceImpl) : NetworkPkmnDataSource

    @Binds
    @Singleton
    abstract fun provideLocalDataSource(localPkmnDataSourceImpl: LocalPkmnDataSourceImpl) : LocalPkmnDataSource
}