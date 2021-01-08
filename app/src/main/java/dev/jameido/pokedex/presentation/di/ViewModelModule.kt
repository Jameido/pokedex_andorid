package dev.jameido.pokedex.presentation.di

import dev.jameido.pokedex.presentation.detail.PkmnSpeciesVM
import dev.jameido.pokedex.presentation.detail.PkmnVarietyVM
import dev.jameido.pokedex.presentation.list.PkmnListVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Jameido on 03/01/2021.
 */
val viewModelModule = module {
    viewModel { PkmnListVM(get()) { get() } }
    viewModel { PkmnSpeciesVM(get()) }
    viewModel { PkmnVarietyVM(get()) }
}