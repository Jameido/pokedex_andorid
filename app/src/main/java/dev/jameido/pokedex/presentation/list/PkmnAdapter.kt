package dev.jameido.pokedex.presentation.list

import android.view.ViewGroup
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ConcatAdapter
import dev.jameido.pokedex.domain.entity.PkmnEntity

/**
 * Created by Jameido on 21/12/2020.
 */
class PkmnAdapter(private val onItemClick: (name: String) -> Unit) : PagingDataAdapter<PkmnEntity, PkmnViewHolder>(PkmnComparator()) {

    override fun onBindViewHolder(holder: PkmnViewHolder, position: Int) = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PkmnViewHolder = PkmnViewHolder(parent, onItemClick)
}

