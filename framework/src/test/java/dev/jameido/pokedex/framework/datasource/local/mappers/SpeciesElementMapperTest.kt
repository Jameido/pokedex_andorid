package dev.jameido.pokedex.framework.datasource.local.mappers

import dev.jameido.pokedex.framework.datasource.DataTestUtil
import dev.jameido.pokedex.framework.datasource.local.LocalDataSourceUnitTestUtil
import org.junit.Assert
import org.junit.Test

/**
 * Created by Jameido on 10/01/2021.
 */
class SpeciesElementMapperTest {

    private val mapper = SpeciesElementMapper()

    @Test
    fun testMapFromDb() {
        val dbModel = LocalDataSourceUnitTestUtil.getDbPkmnSpeciesElement()
        val mapped = mapper.mapFromDb(dbModel)

        Assert.assertNotNull(mapped)
        Assert.assertEquals(mapped.name, dbModel.name)
        Assert.assertEquals(mapped.url, dbModel.url)
    }

    @Test
    fun testMapToDb() {
        val dataModel = DataTestUtil.getPkmn()
        val mapped = mapper.mapToDb(dataModel)

        Assert.assertNotNull(mapped)
        Assert.assertEquals(mapped.name, dataModel.name)
        Assert.assertEquals(mapped.url, dataModel.url)
    }
}