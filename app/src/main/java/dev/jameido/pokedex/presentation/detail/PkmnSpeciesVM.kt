package dev.jameido.pokedex.presentation.detail

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jameido.pokedex.domain.usecase.GetPkmnSpecies
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIState
import javax.inject.Inject

/**
 * Created by Jameido on 03/01/2021.
 */
@HiltViewModel
class PkmnSpeciesVM @Inject constructor(val getSpecies: GetPkmnSpecies) : AndroidDataFlow() {

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