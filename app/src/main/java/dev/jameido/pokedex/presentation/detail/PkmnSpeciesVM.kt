package dev.jameido.pokedex.presentation.detail

import dev.jameido.pokedex.domain.usecase.GetPkmnSpecies
import io.uniflow.androidx.flow.AndroidDataFlow
import org.koin.java.KoinJavaComponent.get

/**
 * Created by Jameido on 03/01/2021.
 */
class PkmnSpeciesVM() : AndroidDataFlow() {

    private var lastSpeciesName = ""

    init {
        action { setState(PkmnVarietyStates.Loading) }
    }

    fun reload() {
        load(lastSpeciesName)
    }

    fun load(name: String) {
        if (name != lastSpeciesName) {
            action(
                    onAction = {
                        setState(PkmnSpeciesStates.Loading)
                        val species = get(GetPkmnSpecies::class.java).load(name)
                        species.varieties.find { it.isDefault ?: false }?.pokemon?.name?.let {
                            sendEvent(PkmnSpeciesEvents.SpeciesLoaded(it))
                        }
                        setState(PkmnSpeciesStates.Loaded(species))
                        lastSpeciesName = name
                    },
                    onError = { error, _ ->
                        setState(PkmnSpeciesStates.Error(error.localizedMessage, name))
                    }
            )
        }
    }

}