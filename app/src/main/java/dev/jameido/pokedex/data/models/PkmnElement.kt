package dev.jameido.pokedex.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PkmnElement(val name: String = "", val url: String?)