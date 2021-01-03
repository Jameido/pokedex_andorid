package dev.jameido.pokedex.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.jameido.pokedex.R
import dev.jameido.pokedex.domain.entity.PkmnEntity
import java.util.*

class PkmnViewHolder(parent: ViewGroup, private val onClick: (name: String) -> Unit) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_pkmn, parent, false)
) {

    private val imgIcon: AppCompatImageView = itemView.findViewById(R.id.img_list_sprite)
    private val txtNumber: AppCompatTextView = itemView.findViewById(R.id.txt_list_nr)
    private val txtName: AppCompatTextView = itemView.findViewById(R.id.txt_list_name)

    fun bind(pkmn: PkmnEntity?) {
        pkmn?.let { pokemon ->
            itemView.setOnClickListener { onClick.invoke(pokemon.name) }
            Glide.with(imgIcon)
                    .load(pokemon.spriteUrl)
                    .placeholder(R.drawable.missingno)
                    .error(R.drawable.missingno)
                    .into(imgIcon)

            txtNumber.text = itemView.context.getString(R.string.nr, pkmn.index)
            txtName.text = pokemon.name.capitalize(Locale.getDefault())
        } ?: run {
            itemView.setOnClickListener(null)
            Glide.with(imgIcon).clear(imgIcon)
            txtName.text = ""
        }
    }
}