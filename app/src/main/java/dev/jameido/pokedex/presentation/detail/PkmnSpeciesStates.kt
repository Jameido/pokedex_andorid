package dev.jameido.pokedex.presentation.detail

import dev.jameido.pokedex.domain.entity.PkmnSpeciesEntity
import io.uniflow.core.flow.data.UIState

/**
 * Created by Jameido on 04/01/2021.
 */
open class PkmnSpeciesStates : UIState() {
    object Loading : PkmnSpeciesStates()
    data class Loaded(val species: PkmnSpeciesEntity) : PkmnSpeciesStates()
    data class Error(val message: String?, val name: String) : PkmnSpeciesStates()
}