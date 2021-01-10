package dev.jameido.pokedex.presentation.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import dev.jameido.pokedex.domain.entity.PkmnEntity
import dev.jameido.pokedex.domain.usecase.GetPkmnListPage
import dev.jameido.pokedex.framework.pagingsource.PkmnListPagingSource

/**
 * Created by Jameido on 21/12/2020.
 */
class PkmnListVM(application: Application, getPkmnListPage: GetPkmnListPage) : AndroidViewModel(application) {

    private val query: String? = null

    private val factory: () -> PagingSource<Int, PkmnEntity> = {
        PkmnListPagingSource(query, getPkmnListPage)
    }

    val list = Pager(
            config = PagingConfig(pageSize = 20, initialLoadSize = 20),
            pagingSourceFactory = factory
    )
            .flow
            .cachedIn(viewModelScope)
}

