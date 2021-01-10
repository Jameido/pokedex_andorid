package dev.jameido.pokedex.framework.datasource.local.mappers

import dev.jameido.pokedex.framework.datasource.local.LocalDataSourceUnitTestUtil
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Jameido on 09/01/2021.
 */
class PkmnMapperTest {

    private val mapper = PkmnMapper()

    @Test
    fun testMapFromDb() {
        val dbModel = LocalDataSourceUnitTestUtil.getDbPkmn()
        val mapped = mapper.mapFromDb(dbModel)

        assertNotNull(mapped)
        assertEquals(mapped.name, dbModel.name)
        assertEquals(mapped.url, dbModel.url)
    }
}