package dev.jameido.pokedex.views

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import dev.jameido.pokedex.R
import dev.jameido.pokedex.data.Type
import java.util.*

/**
 * Created by Jameido on 03/01/2021.
 */
class TypeTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        background = buildTypeDrawable(text).apply { setCornerRadius(8F) }
    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (background is TypeDrawable) {
            val colors = getBackgroundColors(text)
            (background as TypeDrawable).setColors(colors.first, colors.second)
        }
        setTextColor(getTypeTextColor(text))
    }

    private fun buildTypeDrawable(type: CharSequence?): TypeDrawable {
        val colors = getBackgroundColors(type)
        return TypeDrawable(colors.first, colors.second)
    }

    private fun getBackgroundColors(type: CharSequence?): Pair<Int, Int> {
        var topColor = R.color.type_unknown
        var bottomColor = R.color.type_unknown
        when (type?.toString()?.toLowerCase(Locale.getDefault())) {
            Type.bug -> {
                topColor = R.color.type_bug
                bottomColor = R.color.type_bug
            }
            Type.dark -> {
                topColor = R.color.type_dark
                bottomColor = R.color.type_dark
            }
            Type.dragon -> {
                topColor = R.color.type_drag_top
                bottomColor = R.color.type_drag_bottom
            }
            Type.electric -> {
                topColor = R.color.type_elec
                bottomColor = R.color.type_elec
            }
            Type.fairy -> {
                topColor = R.color.type_fairy
                bottomColor = R.color.type_fairy
            }
            Type.fighting -> {
                topColor = R.color.type_fight
                bottomColor = R.color.type_fight
            }
            Type.fire -> {
                topColor = R.color.type_fire
                bottomColor = R.color.type_fire
            }
            Type.flying -> {
                topColor = R.color.type_fly_top
                bottomColor = R.color.type_fly_bottom
            }
            Type.ghost -> {
                topColor = R.color.type_ghost
                bottomColor = R.color.type_ghost
            }
            Type.ground -> {
                topColor = R.color.type_ground_top
                bottomColor = R.color.type_ground_bottom
            }
            Type.grass -> {
                topColor = R.color.type_grass
                bottomColor = R.color.type_grass
            }
            Type.ice -> {
                topColor = R.color.type_ice
                bottomColor = R.color.type_ice
            }
            Type.normal -> {
                topColor = R.color.type_normal
                bottomColor = R.color.type_normal
            }
            Type.poison -> {
                topColor = R.color.type_poison
                bottomColor = R.color.type_poison
            }
            Type.psychic -> {
                topColor = R.color.type_psy
                bottomColor = R.color.type_psy
            }
            Type.rock -> {
                topColor = R.color.type_rock
                bottomColor = R.color.type_rock
            }
            Type.shadow -> {
                topColor = R.color.type_shadow
                bottomColor = R.color.type_shadow
            }
            Type.steel -> {
                topColor = R.color.type_steel
                bottomColor = R.color.type_steel
            }
            Type.unknown -> {
                topColor = R.color.type_unknown
                bottomColor = R.color.type_unknown
            }
            Type.water -> {
                topColor = R.color.type_water
                bottomColor = R.color.type_water
            }
        }
        return Pair(ContextCompat.getColor(context, topColor), ContextCompat.getColor(context, bottomColor))
    }

    private fun getTypeTextColor(type: CharSequence?): Int {
        return ContextCompat.getColor(
                context,
                when (type?.toString()?.toLowerCase(Locale.getDefault())) {
                    Type.dark, Type.fighting, Type.ghost, Type.shadow, Type.water, Type.unknown ->
                        R.color.type_text_light

                    else ->
                        R.color.type_text_dark
                }
        )
    }
}