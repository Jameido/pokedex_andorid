package dev.jameido.pokedex.di

import dev.jameido.pokedex.presentation.detail.PkmnDetailVM
import dev.jameido.pokedex.presentation.list.PkmnListVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Jameido on 03/01/2021.
 */
val viewModelModule = module {
    viewModel { PkmnListVM(get()) }
    viewModel { PkmnDetailVM() }
}