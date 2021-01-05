package dev.jameido.pokedex.data.mappers

import android.net.Uri
import dev.jameido.pokedex.data.models.PkmnModel
import dev.jameido.pokedex.domain.entity.PkmnEntity

/**
 * Created by Jameido on 04/01/2021.
 */
class PkmnEntityMapper : EntityMapper<PkmnModel, PkmnEntity> {
    override fun map(model: PkmnModel): PkmnEntity {
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