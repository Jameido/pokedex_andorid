package dev.jameido.pokedex.presentation.list

import androidx.recyclerview.widget.DiffUtil
import dev.jameido.pokedex.domain.entity.PkmnEntity

/**
 * Created by Jameido on 21/12/2020.
 */
class PkmnComparator : DiffUtil.ItemCallback<PkmnEntity>() {
    override fun areItemsTheSame(oldItem: PkmnEntity, newItem: PkmnEntity): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: PkmnEntity, newItem: PkmnEntity): Boolean {
        return oldItem == newItem
    }
}