package dev.jameido.pokedex.presentation.list

import io.uniflow.core.flow.data.UIEvent

/**
 * Created by Jameido on 10/01/2021.
 */
sealed class PkmnListEvents : UIEvent() {
    object QueryChanged : PkmnListEvents()
    object RefreshContent : PkmnListEvents()
}