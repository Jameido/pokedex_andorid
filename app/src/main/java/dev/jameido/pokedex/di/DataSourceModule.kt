package dev.jameido.pokedex.di

import dev.jameido.pokedex.data.datasource.LocalPkmnDataSource
import dev.jameido.pokedex.data.datasource.NetworkPkmnDataSource
import dev.jameido.pokedex.framework.datasource.local.LocalPkmnDataSourceImpl
import dev.jameido.pokedex.framework.datasource.network.NetworkPkmnDataSourceImpl
import org.koin.dsl.module

/**
 * Created by Jameido on 05/01/2021.
 */
val dataSourceModule = module {
    single<NetworkPkmnDataSource> { NetworkPkmnDataSourceImpl(api = get()) }
    single<LocalPkmnDataSource> { LocalPkmnDataSourceImpl(database = get()) }
}