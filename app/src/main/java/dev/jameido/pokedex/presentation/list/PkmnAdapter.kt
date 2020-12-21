package dev.jameido.pokedex.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
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

class PkmnViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
) {

    fun bind(pkmn: PkmnEntity?) {
        (itemView as TextView).text = pkmn?.name
    }
}