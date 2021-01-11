package dev.jameido.pokedex.presentation.detail

import dev.jameido.pokedex.domain.entity.PkmnDetailEntity
import io.uniflow.core.flow.data.UIState

/**
 * Created by Jameido on 03/01/2021.
 */
open class PkmnVarietyStates : UIState() {
    object Loading : PkmnVarietyStates()
    data class Loaded(val detail: PkmnDetailEntity) : PkmnVarietyStates()
    data class Error(val error: Throwable, val name: String) : PkmnVarietyStates()
}