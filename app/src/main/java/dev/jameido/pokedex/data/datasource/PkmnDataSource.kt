package dev.jameido.pokedex.data.datasource

import dev.jameido.pokedex.data.models.*

/**
 * Created by Jameido on 19/12/2020.
 */
interface PkmnDataSource {
    suspend fun list(pageSize: Int, page: Int) : PkmnListModel?

    suspend fun detail(name: String) : PkmnDetailModel?

    suspend fun species(name: String) : PkmnSpeciesModel?
}

interface NetworkPkmnDataSource : PkmnDataSource

interface LocalPkmnDataSource : PkmnDataSource {

    suspend fun insertDetail(detail: PkmnDetailModel)

    suspend fun insertSpecies(species: PkmnSpeciesModel)

    suspend fun insertPokemon(pokemon: List<PkmnModel>)

    suspend fun insertNextRemotePageKey(pageKey: RemotePageKey)

    suspend fun getNextRemotePageKey(pageKey: String) : RemotePageKey?

}