package dev.jameido.pokedex.di

import androidx.paging.PagingSource
import dev.jameido.pokedex.domain.entity.PkmnEntity
import dev.jameido.pokedex.framework.pagingsource.PkmnListPagingSource
import org.koin.dsl.module

/**
 * Created by Jameido on 08/01/2021.
 */
val pagingSourceModule = module {
    factory<PagingSource<Int, PkmnEntity>> { PkmnListPagingSource(get()) }
}