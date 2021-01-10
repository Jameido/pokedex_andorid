package dev.jameido.pokedex.framework.datasource.local

import androidx.room.*
import dev.jameido.pokedex.data.models.RemotePageKey
import dev.jameido.pokedex.framework.datasource.local.models.*

/**
 * Created by Jameido on 05/01/2021.
 */
@Dao
interface PkmnDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpeciesElement(pokemon: List<DbPkmnSpeciesElement>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpeciesVarieties(pokemon: List<DbPkmn>, varieties: List<DbPkmnSpeciesVarietyData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpeciesData(speciesData: DbPkmnSpeciesData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(
            detail: List<DbPkmnDetailData>,
            stats: List<DbStat>,
            pkmnStats: List<DbPkmnStat>,
            types: List<DbType>,
            pkmnTypes: List<DbPkmnType>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemotePageKey(remotePageKey: DbRemotePageKey)

    @Query("SELECT * FROM species_data WHERE name = :name LIMIT 1")
    suspend fun getSpeciesByName(name: String): DbPkmnSpecies?

    @Query("SELECT * FROM detail WHERE name = :name LIMIT 1")
    suspend fun getDetailByName(name: String): DbPkmnDetail?

    @Query("SELECT * FROM species LIMIT :limit OFFSET :offset")
    suspend fun getPaginatedSpecies(limit: Int, offset: Int): List<DbPkmnSpeciesElement>?

    @Query("SELECT * FROM remote_page_key WHERE listName = :listName LIMIT 1")
    suspend fun getRemotePageKey(listName: String): RemotePageKey?

}