package dev.jameido.pokedex.framework.datasource.local.mappers

import dev.jameido.pokedex.framework.datasource.DataTestUtil
import dev.jameido.pokedex.framework.datasource.local.LocalDataSourceTestUtil
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Jameido on 10/01/2021.
 */
class DetailMapperTest {

    private val mapper = DetailMapper()

    @Test
    fun testMapFromDb() {
        val dbModel = LocalDataSourceTestUtil.getDbPkmnDetail()
        val mapped = mapper.mapFromDb(dbModel)

        assertNotNull(mapped)
        assertEquals(mapped.id, dbModel.detail.id)
        assertEquals(mapped.name, dbModel.detail.name)
        assertEquals(mapped.height, dbModel.detail.height)
        assertEquals(mapped.weight, dbModel.detail.weight)
        assertNotNull(mapped.stats)
        assertEquals(mapped.stats.size, dbModel.stats.size)
        assertNotNull(mapped.stats[0])
        assertEquals(mapped.stats[0].name, dbModel.stats[0].statName)
        assertEquals(mapped.stats[0].value, dbModel.stats[0].value)
        assertNotNull(mapped.types)
        assertEquals(mapped.types.size, dbModel.types.size)
        assertNotNull(mapped.types[0])
        assertEquals(mapped.types[0], dbModel.types[0].typeName)
    }

    @Test
    fun testMapToDb() {
        val dataModel = DataTestUtil.getPkmnDetail()
        val mapped = mapper.mapToDb(dataModel)
        
        assertNotNull(mapped)
        assertEquals(mapped.detail.id, dataModel.id)
        assertEquals(mapped.detail.name, dataModel.name)
        assertEquals(mapped.detail.height, dataModel.height)
        assertEquals(mapped.detail.weight, dataModel.weight)
        assertNotNull(mapped.stats)
        assertEquals(mapped.stats.size, dataModel.stats.size)
        assertNotNull(mapped.stats[0])
        assertEquals(mapped.stats[0].statName, dataModel.stats[0].name)
        assertEquals(mapped.stats[0].pokemonName, dataModel.name)
        assertEquals(mapped.stats[0].value, dataModel.stats[0].value)
        assertNotNull(mapped.types)
        assertEquals(mapped.types.size, dataModel.types.size)
        assertNotNull(mapped.types[0])
        assertEquals(mapped.types[0].pokemonName, dataModel.name)
        assertEquals(mapped.types[0].typeName, dataModel.types[0])

    }
}