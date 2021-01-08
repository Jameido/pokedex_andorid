package dev.jameido.pokedex.framework.datasource.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResPkmnElement(val name: String = "", val url: String?)