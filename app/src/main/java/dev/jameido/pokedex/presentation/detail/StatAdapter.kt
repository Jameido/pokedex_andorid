package dev.jameido.pokedex.presentation.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.jameido.pokedex.domain.entity.StatEntity

/**
 * Created by Jameido on 11/01/2021.
 */
class StatAdapter : RecyclerView.Adapter<StatViewHolder>() {

    private var stats: List<StatEntity?> = emptyList()

    override fun onBindViewHolder(holder: StatViewHolder, position: Int) = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatViewHolder = StatViewHolder(parent)

    override fun getItemCount() = stats.size

    fun updateItems(items: List<StatEntity?>){
        stats = items
        notifyDataSetChanged()
    }

    private fun getItem(index: Int): StatEntity? {
        return if (index >= 0 && index < stats.size) {
            stats[index]
        } else {
            null
        }
    }
}

