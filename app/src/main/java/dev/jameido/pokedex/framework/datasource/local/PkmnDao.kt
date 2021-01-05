package dev.jameido.pokedex.framework.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.jameido.pokedex.framework.datasource.local.models.*

/**
 * Created by Jameido on 05/01/2021.
 */
@Dao
interface PkmnDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPkmn(pokemon: List<DbPkmn>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecies(pokemon: List<DbPkmn>, speciesData: List<DbPkmnSpeciesData>, varieties: List<DbPkmnSpeciesVariety>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(
            detail: List<DbPkmnDetailData>,
            stats: List<DbStat>,
            pkmnStats: List<DbPkmnStats>,
            types: List<DbType>,
            pkmnTypes: List<DbPkmnTypes>)

    @Query("SELECT * FROM species WHERE name = :name LIMIT 1")
    suspend fun getSpeciesByName(name: String): DbPkmnSpecies?

    @Query("SELECT * FROM detail WHERE name = :name LIMIT 1")
    suspend fun getDetailByName(name: String): DbPkmnDetail?

    @Query("SELECT * FROM pokemon LIMIT :limit OFFSET :offset")
    suspend fun getPaginatedPokemon(limit: Int, offset: Int): List<DbPkmn>?
}