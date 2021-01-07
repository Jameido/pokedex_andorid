package dev.jameido.pokedex.presentation.list

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import dev.jameido.pokedex.R
import dev.jameido.pokedex.framework.datasource.network.NetworkException
import dev.jameido.pokedex.views.ErrorViewUtil

/**
 * Created by Jameido on 02/01/2021.
 */
class LoadStateViewHolder(parent: ViewGroup, retry: () -> Unit) :
        RecyclerView.ViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_load, parent, false)
        ) {

    private val imgLoading = itemView.findViewById<AppCompatImageView>(R.id.img_loading)
            .also {
                AnimatedVectorDrawableCompat.create(itemView.context, R.drawable.loading_animation)?.let { animated ->
                    animated.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
                        override fun onAnimationEnd(drawable: Drawable?) {
                            it.post { animated.start() }
                        }
                    })
                    it.setImageDrawable(animated)
                    animated.start()
                }
            }
    private val containerError: View = itemView.findViewById(R.id.container_list_error)
    private val txtError: AppCompatTextView = itemView.findViewById(R.id.txt_error)
    private val imgError = itemView.findViewById<AppCompatImageView>(R.id.img_error)
    private val btnRetry = itemView.findViewById<AppCompatButton>(R.id.btn_retry)
            .also {
                it.setOnClickListener { retry() }
            }

    fun bind(loadState: LoadState) {
        when (loadState) {
            is LoadState.Error -> {
                ErrorViewUtil.showErrorContent(loadState.error, txtError, imgError)
                containerError.visibility = View.VISIBLE
                imgLoading.visibility = View.GONE
            }
            is LoadState.Loading -> {
                containerError.visibility = View.GONE
                imgLoading.visibility = View.VISIBLE
            }
        }
    }
}