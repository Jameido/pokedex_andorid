package dev.jameido.pokedex.presentation.detail

import dev.jameido.pokedex.domain.usecase.GetPkmnDetail
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
}