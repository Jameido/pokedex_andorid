package dev.jameido.pokedex.presentation.detail

import dev.jameido.pokedex.domain.entity.PkmnDetailEntity
import io.uniflow.core.flow.data.UIState

/**
 * Created by Jameido on 03/01/2021.
 */
open class PkmnDetailStates : UIState() {
    object Loading : PkmnDetailStates()
    data class Loaded(val detail: PkmnDetailEntity) : PkmnDetailStates()
    data class Error(val message: String?, val name: String) : PkmnDetailStates()
}