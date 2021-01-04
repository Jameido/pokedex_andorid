package dev.jameido.pokedex.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.jameido.pokedex.R
import dev.jameido.pokedex.domain.entity.PkmnEntity
import java.util.*

class VarietyViewHolder(parent: ViewGroup, private val onClick: (name: String) -> Unit) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_variety, parent, false)
) {

    private val imgIcon: AppCompatImageView = itemView.findViewById(R.id.img_variety_sprite)
    private val txtName: AppCompatTextView = itemView.findViewById(R.id.txt_variety_name)

    fun bind(pkmn: PkmnEntity?) {
        pkmn?.let { pokemon ->
            itemView.setOnClickListener { onClick.invoke(pokemon.name) }
            Glide.with(imgIcon)
                    .load(pokemon.spriteUrl)
                    .placeholder(R.drawable.missingno)
                    .error(R.drawable.missingno)
                    .into(imgIcon)

            txtName.text = pokemon.name.capitalize(Locale.getDefault())
        } ?: run {
            itemView.setOnClickListener(null)
            Glide.with(imgIcon).clear(imgIcon)
            txtName.text = ""
        }
    }
}