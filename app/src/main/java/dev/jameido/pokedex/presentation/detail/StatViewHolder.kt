package dev.jameido.pokedex.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import dev.jameido.pokedex.R
import dev.jameido.pokedex.domain.entity.StatEntity
import java.util.*

/**
 * Created by Jameido on 11/01/2021.
 */
class StatViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_stat, parent, false)
) {

    private val txtName: AppCompatTextView = itemView.findViewById(R.id.txt_stat_name)
    private val prgValue: ProgressBar = itemView.findViewById(R.id.prg_stat_value)

    fun bind(stat: StatEntity?) {
        stat?.let { it ->
            txtName.text = it.name.capitalize(Locale.getDefault())
            txtName.background = null
            prgValue.progress = min(it.value, prgValue.max)
        } ?: run {
            itemView.setOnClickListener(null)
            txtName.text = ""
            txtName.setBackgroundResource(R.color.img_loading_shimmer)
            prgValue.progress = prgValue.max
        }
    }
}