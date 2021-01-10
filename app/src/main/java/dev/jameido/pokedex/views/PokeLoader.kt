package dev.jameido.pokedex.views

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import dev.jameido.pokedex.R

/**
 * Created by Jameido on 10/01/2021.
 */
class PokeLoader @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    init {
        AnimatedVectorDrawableCompat.create(context, R.drawable.loading_animation)?.let { animated ->
            animated.registerAnimationCallback(object : Animatable2Compat.AnimationCallback() {
                override fun onAnimationEnd(drawable: Drawable?) {
                    post { animated.start() }
                }
            })
            setImageDrawable(animated)
            animated.start()
        }
    }
}