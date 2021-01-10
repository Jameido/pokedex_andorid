package dev.jameido.pokedex.presentation.list

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import dev.jameido.pokedex.domain.entity.PkmnEntity
import dev.jameido.pokedex.domain.usecase.GetPkmnListPage
import dev.jameido.pokedex.framework.pagingsource.PkmnListPagingSource
import io.uniflow.androidx.flow.AndroidDataFlow

/**
 * Created by Jameido on 21/12/2020.
 */
class PkmnListVM(getPkmnListPage: GetPkmnListPage) : AndroidDataFlow() {

    private var query: String? = null

    private val factory: () -> PagingSource<Int, PkmnEntity> = {
        PkmnListPagingSource(query, getPkmnListPage)
    }

    val list = Pager(
            config = PagingConfig(pageSize = 20, initialLoadSize = 20),
            pagingSourceFactory = factory
    )
            .flow
            .cachedIn(viewModelScope)

    /**
     * Updates the vm query filter and if it was updates fires an event
     */
    fun applyFilter(query: String?) {
        if (query != this.query) {
            this.query = query
            action { sendEvent(PkmnListEvents.QueryChanged) }
        }
    }
}

