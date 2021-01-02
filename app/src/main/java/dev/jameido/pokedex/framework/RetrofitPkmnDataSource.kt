package dev.jameido.pokedex.framework

import dev.jameido.pokedex.data.datasource.PkmnDataSource
import dev.jameido.pokedex.data.models.ResPkmnDetail
import dev.jameido.pokedex.data.models.ResPkmnList
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by Jameido on 17/12/2020.
 */
interface RetrofitPkmnDataSource : PkmnDataSource {

    @GET(ENDPOINT)
    @Headers("Content-type: application/json")
    override suspend fun list(@Query(QRY_LIMIT) limit: Int, @Query(QRY_OFFSET) offset: Int) : ResPkmnList

    @GET(ENDPOINT)
    @Headers("Content-type: application/json")
    override suspend fun detail(@Query(QRY_NAME) name: String) : ResPkmnDetail

    companion object {
        const val ENDPOINT = "pokemon"
        const val QRY_NAME = "name"
        const val QRY_LIMIT = "limit"
        const val QRY_OFFSET = "offset"
    }
}