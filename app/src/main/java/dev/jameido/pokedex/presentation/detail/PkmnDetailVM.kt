package dev.jameido.pokedex.presentation.detail

import dev.jameido.pokedex.domain.usecase.GetPkmnDetail
import dev.jameido.pokedex.domain.usecase.GetPkmnSpecies
import io.uniflow.androidx.flow.AndroidDataFlow
import org.koin.java.KoinJavaComponent.get

/**
 * Created by Jameido on 03/01/2021.
 */
class PkmnDetailVM() : AndroidDataFlow() {

    private var detailName = ""
    private var speciesName = ""

    fun loadData(name: String) {
        loadDetail(name)
        loadSpecies(name)
    }

    fun reLoadData() {
        reLoadDetail()
        reLoadSpecies()
    }

    fun loadDetail(name: String) {
        detailName = name
        reLoadDetail()
    }

    fun reLoadDetail() {
        action(
                onAction = {
                    setState(PkmnDetailStates.Loading)
                    setState(PkmnDetailStates.Loaded(get(GetPkmnDetail::class.java).load(detailName)))
                },
                onError = { error, _ -> setState(PkmnDetailStates.Error(error.localizedMessage)) }
        )
    }

    fun loadSpecies(name: String) {
        speciesName = name
        reLoadSpecies()
    }

    fun reLoadSpecies() {
        action(
                onAction = {
                    setState(PkmnSpeciesStates.Loading)
                    setState(PkmnSpeciesStates.Loaded(get(GetPkmnSpecies::class.java).load(speciesName)))
                },
                onError = { error, _ -> setState(PkmnSpeciesStates.Error(error.localizedMessage)) }
        )
    }
}