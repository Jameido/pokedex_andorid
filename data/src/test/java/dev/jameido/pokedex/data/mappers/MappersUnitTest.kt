package dev.jameido.pokedex.data.mappers

import dev.jameido.pokedex.data.DataUnitTestUtil
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Jameido on 04/01/2021.
 */
class MappersUnitTest {

    @Test
    fun speciesIdMapper() {
        val model = DataUnitTestUtil.getSpeciesUrl()
        val entity = IdMapper().map(model)
        assertNotNull(entity)
        assertEquals(entity, 6)
    }

    @Test
    fun varietyIdMapper() {
        val model = DataUnitTestUtil.getPokemonUrl()
        val entity = IdMapper().map(model)
        assertNotNull(entity)
        assertEquals(entity, 6)
    }

    @Test
    fun speciesSpriteMapper() {
        val model = DataUnitTestUtil.getSpeciesUrl()
        val entity = SpriteMapper().map(model)
        assertNotNull(entity)
        assertEquals(entity, "https://raw.githubusercontent.com/PokeAPI/sprites/368eb1ed07979ac00d6b91d2a5c1baaaf0e886bb/sprites/pokemon/other/official-artwork/6.png")
    }

    @Test
    fun varietySpriteMapper() {
        val model = DataUnitTestUtil.getPokemonUrl()
        val entity = SpriteMapper().map(model)
        assertNotNull(entity)
        assertEquals(entity, "https://raw.githubusercontent.com/PokeAPI/sprites/368eb1ed07979ac00d6b91d2a5c1baaaf0e886bb/sprites/pokemon/other/official-artwork/6.png")
    }

    @Test
    fun statMapper() {
        val model = DataUnitTestUtil.getSpeedStat()
        val entity = StatEntityMapper().map(model)
        assertNotNull(entity)
        assertEquals(entity.name, model.name)
        assertEquals(entity.value, model.value)
    }

    @Test
    fun pkmnMapper() {
        val model = DataUnitTestUtil.getPkmn()
        val entity = PkmnEntityMapper(IdMapper(), SpriteMapper()).map(model)
        assertNotNull(entity)
        assertEquals(entity.name, model.name)
        assertEquals(entity.url, model.url)
        assertEquals(entity.spriteUrl, "https://raw.githubusercontent.com/PokeAPI/sprites/368eb1ed07979ac00d6b91d2a5c1baaaf0e886bb/sprites/pokemon/other/official-artwork/6.png")
        assertEquals(entity.index, 6)
    }

    @Test
    fun pkmnDetailMapper() {
        val model = DataUnitTestUtil.getPkmnDetail()
        val entity = PkmnDetailEntityMapper(SpriteMapper(), StatEntityMapper()).map(model)
        assertNotNull(entity)
        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
        assertEquals(entity.height, 30F)
        assertEquals(entity.weight, 4F)
        assertNotNull(entity.stats)
        assertEquals(entity.stats.size, 6)
        assertEquals(entity.stats[0].name, "hp")
        assertEquals(entity.stats[0].value, 48)
        assertNotNull(entity.types)
        assertEquals(entity.types.size, 1)
        assertEquals(entity.types[0], "normal")
    }

    @Test
    fun speciesMapper() {
        val model = DataUnitTestUtil.getPkmnSpecies()
        val entity = PkmnSpeciesEntityMapper(PkmnEntityMapper(IdMapper(), SpriteMapper())).map(model)

        assertNotNull(entity)
        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
        assertEquals(entity.evolutionChain, model.evolutionChain)
        assertEquals(entity.description, model.description)
        assertEquals(entity.varieties.size, model.varieties.size)
        assertNotNull(entity.varieties[0])
        assertNotNull(entity.varieties[0].pokemon)
        assertEquals(entity.varieties[0].pokemon.url, model.varieties[0].pokemon.url)
        assertEquals(entity.varieties[0].pokemon.name, model.varieties[0].pokemon.name)
        assertEquals(entity.varieties[0].pokemon.index, 6)
        assertEquals(entity.varieties[0].pokemon.spriteUrl, "https://raw.githubusercontent.com/PokeAPI/sprites/368eb1ed07979ac00d6b91d2a5c1baaaf0e886bb/sprites/pokemon/other/official-artwork/6.png")
        assertEquals(entity.varieties[0].isDefault, model.varieties[0].isDefault)
    }
}
