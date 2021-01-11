package dev.jameido.pokedex.presentation.list

import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Jameido on 10/01/2021.
 */
fun <T : Any, VH : RecyclerView.ViewHolder> PagingDataAdapter<T, VH>.withLoadStateLoaderHeaderFooter(
        loader: LoadStateAdapter<*>,
        header: LoadStateAdapter<*>,
        footer: LoadStateAdapter<*>
): ConcatAdapter {
    addLoadStateListener { loadStates ->
        header.loadState = loadStates.prepend
        footer.loadState = loadStates.append
        loader.loadState = loadStates.refresh
    }
    return ConcatAdapter(header, loader, this, footer)
}
