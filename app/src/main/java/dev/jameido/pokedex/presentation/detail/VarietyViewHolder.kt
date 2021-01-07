package dev.jameido.pokedex.presentation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.jameido.pokedex.R
import dev.jameido.pokedex.domain.entity.PkmnEntity
import dev.jameido.pokedex.domain.entity.PkmnVarietyEntity
import java.util.*

class VarietyViewHolder(parent: ViewGroup, private val onClick: (name: String) -> Unit) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_variety, parent, false)
) {

    private val imgSprite: AppCompatImageView = itemView.findViewById(R.id.img_variety_sprite)
    private val txtName: AppCompatTextView = itemView.findViewById(R.id.txt_variety_name)

    fun bind(pkmn: PkmnVarietyEntity?) {
        pkmn?.let { variety ->
            itemView.setOnClickListener { onClick.invoke(variety.pokemon.name) }
            imgSprite.setBackgroundResource(R.drawable.sprite_backgorund)
            Glide.with(imgSprite)
                    .load(variety.pokemon.spriteUrl)
                    .placeholder(R.drawable.ic_missingno)
                    .error(R.drawable.ic_missingno)
                    .into(imgSprite)

            txtName.text = variety.pokemon.name.capitalize(Locale.getDefault())
            txtName.background = null
        } ?: run {
            itemView.setOnClickListener(null)
            imgSprite.setImageResource(R.color.img_loading_shimmer)
            imgSprite.background = null
            txtName.text = ""
            txtName.setBackgroundResource(R.color.img_loading_shimmer)
        }
    }
}