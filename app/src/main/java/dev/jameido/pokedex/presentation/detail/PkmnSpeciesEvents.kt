package dev.jameido.pokedex.presentation.detail

import io.uniflow.core.flow.data.UIEvent

/**
 * Created by Jameido on 07/01/2021.
 */
sealed class PkmnSpeciesEvents : UIEvent() {
    data class SpeciesLoaded(val defaultVariety: String) : PkmnSpeciesEvents()
}