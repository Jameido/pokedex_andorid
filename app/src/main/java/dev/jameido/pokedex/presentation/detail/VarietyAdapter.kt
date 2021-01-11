package dev.jameido.pokedex.presentation.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.jameido.pokedex.domain.entity.PkmnVarietyEntity

/**
 * Created by Jameido on 21/12/2020.
 */
class VarietyAdapter(private val onItemClick: (name: String) -> Unit) : RecyclerView.Adapter<VarietyViewHolder>() {

    private var varieties: List<PkmnVarietyEntity?> = emptyList()

    override fun onBindViewHolder(holder: VarietyViewHolder, position: Int) = holder.bind(getItem(position))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VarietyViewHolder = VarietyViewHolder(parent, onItemClick)

    override fun getItemCount() = varieties.size

    fun updateItems(items: List<PkmnVarietyEntity?>){
        varieties = items
        notifyDataSetChanged()
    }

    private fun getItem(index: Int): PkmnVarietyEntity? {
        return if (index >= 0 && index < varieties.size) {
            varieties[index]
        } else {
            null
        }
    }
}

