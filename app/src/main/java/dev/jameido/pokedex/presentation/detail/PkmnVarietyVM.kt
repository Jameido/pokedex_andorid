package dev.jameido.pokedex.presentation.detail

import dev.jameido.pokedex.domain.usecase.GetPkmnDetail
import io.uniflow.androidx.flow.AndroidDataFlow

/**
 * Created by Jameido on 03/01/2021.
 */
class PkmnVarietyVM(private val getDetail: GetPkmnDetail) : AndroidDataFlow() {

    private var lastDetailName = ""

    init {
        action { setState(PkmnVarietyStates.Loading) }
    }

    fun reload() {
        load(lastDetailName)
    }

    fun load(name: String) {
        if(name != lastDetailName) {
            action(
                    onAction = {
                        setState(PkmnVarietyStates.Loading)
                        setState(PkmnVarietyStates.Loaded(getDetail.load(name)))
                        lastDetailName = name
                    },
                    onError = { error, _ ->
                        setState(PkmnVarietyStates.Error(error, name))
                    }
            )
        }
    }
}