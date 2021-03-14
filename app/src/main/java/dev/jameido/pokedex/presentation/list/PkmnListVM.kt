package dev.jameido.pokedex.presentation.list

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jameido.pokedex.domain.entity.PkmnEntity
import dev.jameido.pokedex.domain.usecase.GetPkmnListPage
import dev.jameido.pokedex.framework.pagingsource.PkmnListPagingSource
import io.uniflow.androidx.flow.AndroidDataFlow
import javax.inject.Inject

/**
 * Created by Jameido on 21/12/2020.
 */
@HiltViewModel
class PkmnListVM @Inject constructor(getPkmnListPage: GetPkmnListPage) : AndroidDataFlow() {

    private var query: String? = null
    private var refresh: Boolean = false

    private val factory: () -> PagingSource<Int, PkmnEntity> = {
        PkmnListPagingSource(query, refresh, getPkmnListPage)
    }

    val list = Pager(
            config = PagingConfig(pageSize = 20, initialLoadSize = 20),
            pagingSourceFactory = factory
    )
            .flow
            .cachedIn(viewModelScope)

    /**
     * Updates the query filter and if it was updated fires an event
     */
    fun applyFilter(query: String?) {
        if (query != this.query) {
            this.query = query
            action { sendEvent(PkmnListEvents.QueryChanged) }
        }
    }

    /**
     * Updates the refresh flag and fires an event
     */
    fun refreshContent() {
        if (!refresh) {
            this.refresh = true
            action { sendEvent(PkmnListEvents.RefreshContent) }
        }
    }

    fun contentRefreshed() {
        this.refresh = false
    }
}

