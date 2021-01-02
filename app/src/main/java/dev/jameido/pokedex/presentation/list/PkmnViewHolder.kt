package dev.jameido.pokedex.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout
import dev.jameido.pokedex.R
import dev.jameido.pokedex.domain.entity.PkmnEntity

class PkmnViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_pkmn, parent, false)
) {

    //private val shimmerContainer: ShimmerFrameLayout = itemView.findViewById(R.id.shimmer_container)
    private val imgIcon: AppCompatImageView = itemView.findViewById(R.id.img_icon)
    private val txtName: AppCompatTextView = itemView.findViewById(R.id.txt_name)

    fun bind(pkmn: PkmnEntity?) {
        pkmn?.let {
            //TODO: placeholders shimmer
            //shimmerContainer.hideShimmer()
            Glide.with(imgIcon)
                    .load(it.spriteUrl)
                    .placeholder(R.drawable.missingno)
                    .error(R.drawable.missingno)
                    .into(imgIcon)
            txtName.text = it.name
        } ?: run {
            //TODO: placeholders shimmer
            //shimmerContainer.showShimmer(true)
            Glide.with(imgIcon).clear(imgIcon)
            txtName.text = ""
        }
    }
}