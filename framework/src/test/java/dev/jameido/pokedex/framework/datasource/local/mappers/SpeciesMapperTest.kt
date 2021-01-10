package dev.jameido.pokedex.framework.datasource.local.mappers

import dev.jameido.pokedex.framework.datasource.DataTestUtil
import dev.jameido.pokedex.framework.datasource.local.LocalDataSourceTestUtil
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Jameido on 08/01/2021.
 */
class SpeciesMapperTest {

    private val mapper = SpeciesMapper()

    @Test
    fun testMapFromDb() {
        val dbModel = LocalDataSourceTestUtil.getDbPkmnSpecies()
        val mapped = mapper.mapFromDb(dbModel)

        assertNotNull(mapped)
        assertEquals(mapped.id, dbModel.speciesData.id)
        assertEquals(mapped.name, dbModel.speciesData.name)
        assertEquals(mapped.description, dbModel.speciesData.description)
        assertEquals(mapped.varieties.size, dbModel.varieties?.size)
        assertNotNull(mapped.varieties[0])
        assertEquals(mapped.varieties[0].isDefault, dbModel.varieties!![0].variety.isDefault)
        assertNotNull(mapped.varieties[0].pokemon)
        assertEquals(mapped.varieties[0].pokemon.url, dbModel.varieties!![0].pokemon.url)
        assertEquals(mapped.varieties[0].pokemon.name, dbModel.varieties!![0].pokemon.name)
    }

    @Test
    fun testMapFromDbNullVarieties() {
        val dbModel = LocalDataSourceTestUtil.getDbPkmnSpeciesNullVarieties()
        val mapped = mapper.mapFromDb(dbModel)

        assertNotNull(mapped)
        assertEquals(mapped.id, dbModel.speciesData.id)
        assertEquals(mapped.name, dbModel.speciesData.name)
        assertEquals(mapped.description, dbModel.speciesData.description)
        assertEquals(mapped.evolutionChain, dbModel.speciesData.evolutionChain)
        assertNotNull(mapped.varieties)
        assertEquals(mapped.varieties.size, 0)
    }

    @Test
    fun testMapToDb() {
        val dataModel = DataTestUtil.getPkmnSpecies()
        val mapped = mapper.mapToDb(dataModel)
        assertNotNull(mapped)
        assertNotNull(mapped.speciesData)
        assertEquals(mapped.speciesData.id, dataModel.id)
        assertEquals(mapped.speciesData.name, dataModel.name)
        assertEquals(mapped.speciesData.description, dataModel.description)
        assertEquals(mapped.speciesData.evolutionChain, dataModel.evolutionChain)
        assertNotNull(mapped.varieties)
        assertEquals(mapped.varieties!!.size, dataModel.varieties.size)
        assertNotNull(mapped.varieties!![0])
        assertNotNull(mapped.varieties!![0].pokemon)
        assertNotNull(mapped.varieties!![0].variety)
        assertEquals(mapped.varieties!![0].pokemon.name, dataModel.varieties[0].pokemon.name)
        assertEquals(mapped.varieties!![0].pokemon.url, dataModel.varieties[0].pokemon.url)
        assertEquals(mapped.varieties!![0].variety.isDefault, dataModel.varieties[0].isDefault)
        assertEquals(mapped.varieties!![0].variety.pokemonName, dataModel.varieties[0].pokemon.name)
        assertEquals(mapped.varieties!![0].variety.speciesName, dataModel.name)

    }
}