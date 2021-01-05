package dev.jameido.pokedex.framework.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.jameido.pokedex.framework.datasource.local.models.*

/**
 * Created by Jameido on 05/01/2021.
 */
@Database(entities = [
    DbStat::class,
    DbType::class,
    DbPkmn::class,
    DbPkmnSpeciesVariety::class,
    DbPkmnSpeciesData::class,
    DbPkmnDetailData::class,
    DbPkmnStats::class,
    DbPkmnTypes::class
], version = 1)
abstract class PkmnDatabase : RoomDatabase() {
    abstract fun pkmnDao(): PkmnDao
}