package dev.jameido.pokedex.framework.datasource.local

import androidx.room.*
import dev.jameido.pokedex.framework.datasource.local.models.*

/**
 * Created by Jameido on 05/01/2021.
 */
@Dao
interface PkmnDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpeciesElement(pokemon: List<DbPkmnSpeciesElement>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpeciesVarieties(pokemon: List<DbPkmn>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpeciesData(speciesData: DbPkmnSpeciesData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(
            detail: List<DbPkmnDetailData>,
            stats: List<DbStat>,
            pkmnStats: List<DbPkmnStats>,
            types: List<DbType>,
            pkmnTypes: List<DbPkmnTypes>)

    @Query("SELECT * FROM species_data WHERE name = :name LIMIT 1")
    suspend fun getSpeciesByName(name: String): DbPkmnSpecies?

    @Query("SELECT * FROM detail WHERE name = :name LIMIT 1")
    suspend fun getDetailByName(name: String): DbPkmnDetail?

    @Query("SELECT * FROM species LIMIT :limit OFFSET :offset")
    suspend fun getPaginatedSpecies(limit: Int, offset: Int): List<DbPkmnSpeciesElement>?
}