package dev.jameido.pokedex.framework.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
    suspend fun insertSpecies(speciesData: DbPkmnSpeciesData, pokemon: List<DbPkmn>, varieties: List<DbPkmnSpeciesVarietyData>)

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

    //TODO: use Fuzzy Matching if it will be ever added to Room/standard SQLite
    @Query("SELECT * FROM species WHERE name LIKE :query COLLATE NOCASE LIMIT :limit OFFSET :offset")
    suspend fun getPaginatedSpeciesByName(query: String, limit: Int, offset: Int): List<DbPkmnSpeciesElement>?

    @Query("SELECT * FROM species LIMIT :limit OFFSET :offset")
    suspend fun getPaginatedSpecies(limit: Int, offset: Int): List<DbPkmnSpeciesElement>?

    @Query("SELECT * FROM remote_page_key WHERE listName = :listName LIMIT 1")
    suspend fun getRemotePageKey(listName: String): RemotePageKey?

    @Query("DELETE FROM remote_page_key")
    suspend fun deleteRemotePageKeys()

    @Query("DELETE FROM pokemon_type")
    suspend fun deletePokemonTypes()

    @Query("DELETE FROM pokemon_stat")
    suspend fun deletePokemonStats()

    @Query("DELETE FROM type")
    suspend fun deleteTypes()

    @Query("DELETE FROM stat")
    suspend fun deleteStats()

    @Query("DELETE FROM species")
    suspend fun deleteSpeciesElements()

    @Query("DELETE FROM species_data")
    suspend fun deleteSpeciesData()

    @Query("DELETE FROM species_variety")
    suspend fun deleteSpeciesVariety()

    @Query("DELETE FROM pokemon")
    suspend fun deletePokemons()

    @Query("DELETE FROM detail")
    suspend fun deleteDetailData()
}

suspend fun PkmnDao.deleteAll(){
    deleteRemotePageKeys()
    deletePokemonTypes()
    deletePokemonStats()
    deleteTypes()
    deleteStats()
    deleteSpeciesElements()
    deleteSpeciesData()
    deleteSpeciesVariety()
    deletePokemons()
    deleteDetailData()
}