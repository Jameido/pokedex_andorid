package dev.jameido.pokedex.presentation.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dev.jameido.pokedex.domain.usecase.GetPkmnListPage
import org.koin.java.KoinJavaComponent.get

/**
 * Created by Jameido on 21/12/2020.
 */
class PkmnListVM(application: Application) : AndroidViewModel(application) {

    val list = Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { get(GetPkmnListPage::class.java) }
    )
            .flow
            .cachedIn(viewModelScope)
}