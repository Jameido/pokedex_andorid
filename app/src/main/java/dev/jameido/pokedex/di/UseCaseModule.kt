package dev.jameido.pokedex.di

import dev.jameido.pokedex.domain.usecase.GetPkmnListPage
import org.koin.dsl.module

/**
 * Created by Jameido on 21/12/2020.
 */
val useCaseModule = module {
    factory { GetPkmnListPage(get()) }
}