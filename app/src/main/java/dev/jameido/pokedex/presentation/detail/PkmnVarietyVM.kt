package dev.jameido.pokedex.presentation.detail

import dev.jameido.pokedex.usecase.GetPkmnDetail
import io.uniflow.androidx.flow.AndroidDataFlow
import org.koin.java.KoinJavaComponent.get

/**
 * Created by Jameido on 03/01/2021.
 */
class PkmnVarietyVM() : AndroidDataFlow() {

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
                        setState(PkmnVarietyStates.Loaded(get(GetPkmnDetail::class.java).load(name)))
                        lastDetailName = name
                    },
                    onError = { error, _ ->
                        setState(PkmnVarietyStates.Error(error, name))
                    }
            )
        }
    }
}