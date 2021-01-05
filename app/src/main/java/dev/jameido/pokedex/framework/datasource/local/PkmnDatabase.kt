package dev.jameido.pokedex.framework.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.jameido.pokedex.framework.datasource.local.models.DbPkmn
import dev.jameido.pokedex.framework.datasource.local.models.DbPkmnDetail
import dev.jameido.pokedex.framework.datasource.local.models.DbPkmnSpeciesData
import dev.jameido.pokedex.framework.datasource.local.models.DbPkmnSpeciesVariety

/**
 * Created by Jameido on 05/01/2021.
 */
@Database(entities = [DbPkmn::class, DbPkmnSpeciesVariety::class, DbPkmnDetail::class, DbPkmnSpeciesData::class], version = 1)
abstract class PkmnDatabase : RoomDatabase() {
    abstract fun pkmnDao(): PkmnDao
}