package dev.jameido.pokedex.presentation.detail

import dev.jameido.pokedex.domain.usecase.GetPkmnDetail
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIState

/**
 * Created by Jameido on 03/01/2021.
 */
class PkmnVarietyVM(private val getDetail: GetPkmnDetail) : AndroidDataFlow() {

    init {
        action { setState(UIState.Empty) }
    }

    fun load(name: String) = action(
            onAction = {
                if (getCurrentState() !is PkmnVarietyStates.Loading && (getCurrentState() as? PkmnVarietyStates.Loaded)?.name != name) {
                    setState(PkmnVarietyStates.Loading)
                    setState(PkmnVarietyStates.Loaded(getDetail.load(name), name))
                }
            },
            onError = { error, _ ->
                setState(PkmnVarietyStates.Error(error, name))
            }
    )
}