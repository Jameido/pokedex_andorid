package dev.jameido.pokedex.presentation.list

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import dev.jameido.pokedex.domain.entity.PkmnEntity

/**
 * Created by Jameido on 21/12/2020.
 */
class PkmnAdapter() : PagingDataAdapter<PkmnEntity, PkmnViewHolder>(PkmnComparator()) {
    override fun onBindViewHolder(holder: PkmnViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PkmnViewHolder {
        return PkmnViewHolder(parent)
    }
}

