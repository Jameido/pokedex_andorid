package dev.jameido.pokedex.presentation.detail

import dev.jameido.pokedex.domain.usecase.GetPkmnDetail
import dev.jameido.pokedex.domain.usecase.GetPkmnSpecies
import io.uniflow.androidx.flow.AndroidDataFlow
import org.koin.java.KoinJavaComponent.get

/**
 * Created by Jameido on 03/01/2021.
 */
class PkmnDetailVM() : AndroidDataFlow() {

    fun loadDetail(name: String) = action(
            onAction = {
                setState(PkmnDetailStates.Loading)
                setState(PkmnDetailStates.Loaded(get(GetPkmnDetail::class.java).load(name)))
            },
            onError = { error, _ -> setState(PkmnDetailStates.Error(error.localizedMessage)) }
    )

    fun loadSpecies(name: String) = action(
            onAction = {
                setState(PkmnSpeciesStates.Loading)
                setState(PkmnSpeciesStates.Loaded(get(GetPkmnSpecies::class.java).load(name)))
            },
            onError = { error, _ -> setState(PkmnSpeciesStates.Error(error.localizedMessage)) }
    )
}