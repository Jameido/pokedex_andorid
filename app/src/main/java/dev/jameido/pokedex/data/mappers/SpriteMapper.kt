package dev.jameido.pokedex.data.mappers

import android.net.Uri

/**
 * Created by Jameido on 07/01/2021.
 */
class SpriteMapper {
    fun map(url: String?): String? {
        return url?.let {
            map(Uri.parse(it))
        }
    }

    fun map(url: Uri?): String? {
        return map(url?.lastPathSegment?.toInt())
    }

    fun map(index: Int?): String? {
        return index?.let {
            "https://raw.githubusercontent.com/PokeAPI/sprites/368eb1ed07979ac00d6b91d2a5c1baaaf0e886bb/sprites/pokemon/other/official-artwork/${it}.png"
        }
    }
}