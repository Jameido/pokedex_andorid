package dev.jameido.pokedex.presentation.detail

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.jameido.pokedex.domain.usecase.GetPkmnDetail
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIState
import javax.inject.Inject

/**
 * Created by Jameido on 03/01/2021.
 */
@HiltViewModel
class PkmnVarietyVM @Inject constructor(val getDetail: GetPkmnDetail) : AndroidDataFlow() {

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