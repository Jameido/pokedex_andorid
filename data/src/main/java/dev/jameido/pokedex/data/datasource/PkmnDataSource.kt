package dev.jameido.pokedex.data.datasource

import dev.jameido.pokedex.data.models.*

/**
 * Created by Jameido on 19/12/2020.
 */
/**
 * Data source interface with common methods between network and local DS
 */
interface PkmnDataSource {
    /**
     * Get list of pokemon species page
     */
    suspend fun list(query: String, pageSize: Int, page: Int) : PkmnListModel?

    /**
     * Get pokemon detail data
     */
    suspend fun detail(name: String) : PkmnDetailModel?

    /**
     * Get pokemon species detail data
     */
    suspend fun species(name: String) : PkmnSpeciesModel?
}

/**
 * Dedicated network data source interface
 */
interface NetworkPkmnDataSource : PkmnDataSource

/**
 * Dedicated local data source interface with specific methods to write and read from local storage
 */
interface LocalPkmnDataSource : PkmnDataSource {

    /**
     * Insert pokemon detail data into local storage
     */
    suspend fun insertDetail(detail: PkmnDetailModel)

    /**
     * Insert pokemon species detail data into local storage
     */
    suspend fun insertSpecies(species: PkmnSpeciesModel)

    /**
     * Insert list of pokemon species page into local storage
     */
    suspend fun insertPokemon(pokemon: List<PkmnModel>)

    /**
     * Insert the next remote page key to load into local storage
     */
    suspend fun insertNextRemotePageKey(pageKey: RemotePageKey)

    /**
     * Get the next remote page key to load from local storage
     */
    suspend fun getNextRemotePageKey(pageKey: String) : RemotePageKey?

}