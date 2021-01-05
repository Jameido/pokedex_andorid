package dev.jameido.pokedex.di

import dev.jameido.pokedex.data.repository.PkmnRepository
import dev.jameido.pokedex.framework.repository.PkmnRepositoryImpl
import org.koin.dsl.module

/**
 * Created by Jameido on 21/12/2020.
 */
val repositoryModule = module {
    single<PkmnRepository> { PkmnRepositoryImpl(networkDataSource = get(), localPkmnDataSource = get()) }
}