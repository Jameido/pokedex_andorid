package dev.jameido.pokedex.framework.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.jameido.pokedex.framework.datasource.local.models.DbPkmn
import dev.jameido.pokedex.framework.datasource.local.models.DbPkmnSpecies
import dev.jameido.pokedex.framework.datasource.local.models.DbPkmnSpeciesData
import dev.jameido.pokedex.framework.datasource.local.models.DbPkmnSpeciesVariety

/**
 * Created by Jameido on 05/01/2021.
 */
@Dao
interface PkmnDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpecies(pokemon: List<DbPkmn>, speciesData: List<DbPkmnSpeciesData>, varieties: List<DbPkmnSpeciesVariety>)

    @Query("SELECT * FROM species WHERE name = :name LIMIT 1")
    suspend fun getByName(name: String): DbPkmnSpecies?
}