package dev.jameido.pokedex.views

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import dev.jameido.pokedex.R
import dev.jameido.pokedex.framework.datasource.network.NetworkException

/**
 * Created by Jameido on 07/01/2021.
 */
object ErrorViewUtil {
    fun showErrorContent(throwable: Throwable, txt: AppCompatTextView, img: AppCompatImageView) {
        when (throwable) {
            is NetworkException -> {
                txt.setText(R.string.error_connection)
                img.setImageResource(R.drawable.ic_team_rocket)
            }
            else -> {
                txt.setText(R.string.error_generic)
                img.setImageResource(R.drawable.ic_tired_pikachu)
            }
        }
    }
}