package dev.jameido.pokedex.presentation.list

import android.view.ViewGroup
import androidx.paging.*

/**
 * Created by Jameido on 02/01/2021.
 */
class PkmnLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState) = LoadStateViewHolder(parent, retry)

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) = holder.bind(loadState)
}