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
    DbPkmnSpeciesElement::class,
    DbPkmnSpeciesData::class,
    DbPkmn::class,
    DbPkmnSpeciesVarietyData::class,
    DbPkmnDetailData::class,
    DbPkmnStat::class,
    DbPkmnType::class,
    DbRemotePageKey::class
], version = 4)
abstract class PkmnDatabase : RoomDatabase() {
    abstract fun pkmnDao(): PkmnDao
}