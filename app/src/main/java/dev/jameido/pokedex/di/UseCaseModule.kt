package dev.jameido.pokedex.di

import dev.jameido.pokedex.data.usecase.GetPkmnDetailImpl
import dev.jameido.pokedex.data.usecase.GetPkmnListPageImpl
import dev.jameido.pokedex.data.usecase.GetPkmnSpeciesImpl
import dev.jameido.pokedex.domain.usecase.GetPkmnDetail
import dev.jameido.pokedex.domain.usecase.GetPkmnListPage
import dev.jameido.pokedex.domain.usecase.GetPkmnSpecies
import org.koin.dsl.module

/**
 * Created by Jameido on 21/12/2020.
 */
val useCaseModule = module {
    factory<GetPkmnListPage> { GetPkmnListPageImpl(repository = get()) }
    factory<GetPkmnDetail> { GetPkmnDetailImpl(repository = get()) }
    factory<GetPkmnSpecies> { GetPkmnSpeciesImpl(repository = get()) }
}