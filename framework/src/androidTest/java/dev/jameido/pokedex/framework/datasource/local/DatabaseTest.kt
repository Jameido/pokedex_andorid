package dev.jameido.pokedex.framework.datasource.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Created by Jameido on 08/01/2021.
 */
@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var dao: PkmnDao
    private lateinit var db: PkmnDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
                context, PkmnDatabase::class.java).build()
        dao = db.pkmnDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeAndReadSpecies() {
        runBlocking {
            val insertSpecies = LocalDataSourceAndroidTestUtil.getDbPkmnSpecies()
            dao.insertSpecies(
                    insertSpecies.speciesData,
                    insertSpecies.varieties.orEmpty().map { speciesVariety -> speciesVariety.pokemon },
                    insertSpecies.varieties.orEmpty().map { speciesVariety -> speciesVariety.variety }
            )

            val readSpecies = runBlocking {
                dao.getSpeciesByName("charizard")
            }
            
            assertNotNull(readSpecies)
            assertEquals(readSpecies!!.speciesData.id, insertSpecies.speciesData.id)
            assertEquals(readSpecies.speciesData.name, insertSpecies.speciesData.name)
            assertEquals(readSpecies.speciesData.description, insertSpecies.speciesData.description)
            assertNotNull(readSpecies.varieties)
            assertEquals(readSpecies.varieties!!.size, insertSpecies.varieties!!.size)
            assertNotNull(readSpecies.varieties!![0])
            assertEquals(readSpecies.varieties!![0].variety.isDefault, insertSpecies.varieties!![0].variety.isDefault)
            assertEquals(readSpecies.varieties!![0].variety.speciesName, insertSpecies.varieties!![0].variety.speciesName)
            assertEquals(readSpecies.varieties!![0].variety.pokemonName, insertSpecies.varieties!![0].variety.pokemonName)
            assertNotNull(readSpecies.varieties!![0].pokemon)
            assertEquals(readSpecies.varieties!![0].pokemon.url, insertSpecies.varieties!![0].pokemon.url)
            assertEquals(readSpecies.varieties!![0].pokemon.name, insertSpecies.varieties!![0].pokemon.name)
        }

    }
}