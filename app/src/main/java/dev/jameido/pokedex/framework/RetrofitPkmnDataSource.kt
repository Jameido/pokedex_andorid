package dev.jameido.pokedex.framework

import dev.jameido.pokedex.data.datasource.PkmnDataSource
import dev.jameido.pokedex.data.models.ResPkmnDetail
import dev.jameido.pokedex.data.models.ResPkmnList
import dev.jameido.pokedex.data.models.ResPkmnSpecies
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Jameido on 17/12/2020.
 */
interface RetrofitPkmnDataSource : PkmnDataSource {

    @GET(LIST_ENDPOINT)
    @Headers("Content-type: application/json")
    override suspend fun list(@Query(QRY_LIMIT) limit: Int, @Query(QRY_OFFSET) offset: Int) : ResPkmnList

    @GET(DETAIL_ENDPOINT)
    @Headers("Content-type: application/json")
    override suspend fun detail(@Path(PATH_NAME) name: String) : ResPkmnDetail

    @GET(SPECIES_ENDPOINT)
    @Headers("Content-type: application/json")
    override suspend fun species(@Path(PATH_NAME) name: String) : ResPkmnSpecies

    companion object {
        const val LIST_ENDPOINT = "pokemon-species"
        const val PATH_NAME = "name"
        const val DETAIL_ENDPOINT = "pokemon/{$PATH_NAME}"
        const val SPECIES_ENDPOINT = "pokemon-species/{$PATH_NAME}"
        const val QRY_LIMIT = "limit"
        const val QRY_OFFSET = "offset"
    }
}