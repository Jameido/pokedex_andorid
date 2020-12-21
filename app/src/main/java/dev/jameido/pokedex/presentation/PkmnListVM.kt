package dev.jameido.pokedex.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.jameido.pokedex.domain.entity.PkmnEntity
import dev.jameido.pokedex.domain.usecase.GetPkmnListPage
import kotlinx.coroutines.flow.Flow
import org.koin.java.KoinJavaComponent.get

/**
 * Created by Jameido on 21/12/2020.
 */
class PkmnListVM(application: Application) : AndroidViewModel(application) {

    fun getPkmnList(): Flow<PagingData<PkmnEntity>> {
        return Pager(
                config = PagingConfig(
                        pageSize = 5,
                        enablePlaceholders = true
                ),
                pagingSourceFactory = { get(GetPkmnListPage::class.java) }
        ).flow
    }
}