package dev.jameido.pokedex.data.mappers

import android.net.Uri
import dev.jameido.pokedex.data.models.PkmnElement
import dev.jameido.pokedex.domain.entity.PkmnEntity

/**
 * Created by Jameido on 04/01/2021.
 */
class PkmnMapper : EntityMapper<PkmnElement, PkmnEntity> {
    override fun map(model: PkmnElement): PkmnEntity {
        var index: Int? = null
        var sprite: String? = null
        model.url?.let { url ->
            index = Uri.parse(url)?.lastPathSegment?.toInt()
            index?.let { id ->
                sprite = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${index}.png"
            }
        }
        return PkmnEntity(model.name, model.url, index, sprite)
    }
}