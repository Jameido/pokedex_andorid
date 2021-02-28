package dev.jameido.pokedex.presentation.detail

import dev.jameido.pokedex.domain.usecase.GetPkmnSpecies
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIState

/**
 * Created by Jameido on 03/01/2021.
 */
class PkmnSpeciesVM(private val getSpecies: GetPkmnSpecies) : AndroidDataFlow() {

    init {
        action { setState(UIState.Empty) }
    }

    fun load(name: String) = action(
            onAction = {
                if (getCurrentState() !is PkmnSpeciesStates.Loading && (getCurrentState() as? PkmnSpeciesStates.Loaded)?.name != name) {
                    setState(PkmnSpeciesStates.Loading)
                    val species = getSpecies.load(name)
                    species.varieties.find { it.isDefault ?: false }?.pokemon?.name?.let {
                        sendEvent(PkmnSpeciesEvents.SpeciesLoaded(it))
                    }
                    setState(PkmnSpeciesStates.Loaded(species, name))
                }
            },
            onError = { error, _ ->
                setState(PkmnSpeciesStates.Error(error, name))
            }
    )
}