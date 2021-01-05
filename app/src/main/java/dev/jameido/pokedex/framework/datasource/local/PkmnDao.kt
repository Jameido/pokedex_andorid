package dev.jameido.pokedex.framework.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.jameido.pokedex.framework.datasource.local.models.DbPkmnSpecies
import dev.jameido.pokedex.framework.datasource.local.models.DbPkmnSpeciesData
import dev.jameido.pokedex.framework.datasource.local.models.DbPkmnSpeciesVariety

/**
 * Created by Jameido on 05/01/2021.
 */
@Dao
interface PkmnDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecies(speciesData: List<DbPkmnSpecies>)

    @Query("SELECT * FROM species WHERE name = :name")
    fun getByName(name: String): List<DbPkmnSpecies>
}