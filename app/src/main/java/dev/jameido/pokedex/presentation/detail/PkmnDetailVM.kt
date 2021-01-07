package dev.jameido.pokedex.presentation.detail

import dev.jameido.pokedex.domain.usecase.GetPkmnDetail
import dev.jameido.pokedex.domain.usecase.GetPkmnSpecies
import io.uniflow.androidx.flow.AndroidDataFlow
import org.koin.java.KoinJavaComponent.get

/**
 * Created by Jameido on 03/01/2021.
 */
class PkmnDetailVM() : AndroidDataFlow() {

    private var lastDetailName = ""
    private var lastSpeciesName = ""

    fun loadData(name: String) {
        loadSpecies(name)
    }

    fun reLoadData() {
        loadDetail(lastDetailName)
        loadSpecies(lastSpeciesName)
    }

    fun loadDetail(name: String) {
        if(name != lastDetailName) {
            action(
                    onAction = {
                        setState(PkmnDetailStates.Loading)
                        setState(PkmnDetailStates.Loaded(get(GetPkmnDetail::class.java).load(name)))
                        lastDetailName = name
                    },
                    onError = { error, _ ->
                        setState(PkmnDetailStates.Error(error.localizedMessage, name))
                    }
            )
        }
    }

    fun loadSpecies(name: String) {
        if(name != lastSpeciesName) {
            action(
                    onAction = {
                        //TODO: use events instead of stage
                        setState(PkmnDetailStates.Loading)
                        setState(PkmnSpeciesStates.Loading)
                        val species = get(GetPkmnSpecies::class.java).load(name)
                        species.varieties.find { it.isDefault ?: false }?.pokemon?.name?.let {
                            loadDetail(it)
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