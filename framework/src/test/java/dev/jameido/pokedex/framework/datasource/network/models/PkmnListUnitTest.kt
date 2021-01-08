package dev.jameido.pokedex.framework.datasource.network.models

import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by Jameido on 17/12/2020.
 */
class PkmnListUnitTest {
    private val moshi = Moshi.Builder()
            .build()

    @Test
    fun parsePkmnListFirstElements() {
        try {
            val listResponse = moshi.adapter(ResPkmnList::class.java).fromJson(LIST_FIRST_ELEMENTS)
            assertNotNull(listResponse)
            assertEquals(listResponse!!.count, 898)
            assertEquals(listResponse!!.next, "https://pokeapi.co/api/v2/pokemon-species/?offset=20&limit=20")
            assertNull(listResponse!!.previous)
            assertNotNull(listResponse!!.results)
            assertEquals(listResponse!!.results.size, 20)
            assertEquals(listResponse!!.results[0], ResPkmnElement("bulbasaur", "https://pokeapi.co/api/v2/pokemon-species/1/"))
        } catch (ex: JsonDataException) {
            fail()
        }
    }

    @Test
    fun parsePkmnListLastElements() {
        try {
            val listResponse = moshi.adapter(ResPkmnList::class.java).fromJson(LIST_LAST_ELEMENTS)
            assertNotNull(listResponse)
            assertEquals(listResponse!!.count, 898)
            assertNull(listResponse!!.next)
            assertEquals(listResponse!!.previous, "https://pokeapi.co/api/v2/pokemon-species/?offset=860&limit=20")
            assertNotNull(listResponse!!.results)
            assertEquals(listResponse!!.results.size, 18)
            assertEquals(listResponse!!.results[0], ResPkmnElement("arctozolt", "https://pokeapi.co/api/v2/pokemon-species/881/"))
        } catch (ex: JsonDataException) {
            fail()
        }
    }

    @Test
    fun parsePkmnListIntermediate() {
        try {
            val listResponse = moshi.adapter(ResPkmnList::class.java).fromJson(LIST_INTERMEDIATE)
            assertNotNull(listResponse)
            assertEquals(listResponse!!.count, 898)
            assertEquals(listResponse!!.next, "https://pokeapi.co/api/v2/pokemon-species/?offset=520&limit=20")
            assertEquals(listResponse!!.previous, "https://pokeapi.co/api/v2/pokemon-species/?offset=480&limit=20")
            assertNotNull(listResponse!!.results)
            assertEquals(listResponse!!.results.size, 20)
            assertEquals(listResponse!!.results[0], ResPkmnElement("oshawott", "https://pokeapi.co/api/v2/pokemon-species/501/"))
        } catch (ex: JsonDataException) {
            fail()
        }
    }

    @Test
    fun parsePkmnListOutOfBounds() {
        try {
            val listResponse = moshi.adapter(ResPkmnList::class.java).fromJson(LIST_OUT_BOUNDS)
            assertNotNull(listResponse)
            assertEquals(listResponse!!.count, 898)
            assertNull(listResponse!!.next)
            assertEquals(listResponse!!.previous, "https://pokeapi.co/api/v2/pokemon-species/?offset=1980&limit=20")
            assertNotNull(listResponse!!.results)
            assertEquals(listResponse!!.results.size, 0)
        } catch (ex: JsonDataException) {
            fail()
        }
    }


    @Test
    fun parsePkmnListElement() {
        try {
            val bulbasaur = moshi.adapter(ResPkmnElement::class.java).fromJson(ELEMENT)
            assertNotNull(bulbasaur)
            assertEquals(bulbasaur!!.name, "bulbasaur")
            assertEquals(bulbasaur!!.url, "https://pokeapi.co/api/v2/pokemon/1/")
        } catch (ex: JsonDataException) {
            fail()
        }
    }

    @Test
    fun parseNullContentPkmnListElement() {
        try {
            moshi.adapter(ResPkmnElement::class.java).fromJson(NULL_CONTENT_ELEMENT)
            fail()
        } catch (ex: JsonDataException) {
        }
    }

    @Test
    fun parseEmptyPkmnListElement() {
        try {
            val bulbasaur = moshi.adapter(ResPkmnElement::class.java).fromJson(EMPTY_ELEMENT)
            assertNotNull(bulbasaur)
            assertEquals(bulbasaur!!.name, "")
            assertNull(bulbasaur!!.url)
        } catch (ex: JsonDataException) {
            fail()
        }
    }

    companion object {
        //Response of pokemon-species?limit=20&offset=0
        const val LIST_FIRST_ELEMENTS = """{"count":898,"next":"https://pokeapi.co/api/v2/pokemon-species/?offset=20&limit=20","previous":null,"results":[{"name":"bulbasaur","url":"https://pokeapi.co/api/v2/pokemon-species/1/"},{"name":"ivysaur","url":"https://pokeapi.co/api/v2/pokemon-species/2/"},{"name":"venusaur","url":"https://pokeapi.co/api/v2/pokemon-species/3/"},{"name":"charmander","url":"https://pokeapi.co/api/v2/pokemon-species/4/"},{"name":"charmeleon","url":"https://pokeapi.co/api/v2/pokemon-species/5/"},{"name":"charizard","url":"https://pokeapi.co/api/v2/pokemon-species/6/"},{"name":"squirtle","url":"https://pokeapi.co/api/v2/pokemon-species/7/"},{"name":"wartortle","url":"https://pokeapi.co/api/v2/pokemon-species/8/"},{"name":"blastoise","url":"https://pokeapi.co/api/v2/pokemon-species/9/"},{"name":"caterpie","url":"https://pokeapi.co/api/v2/pokemon-species/10/"},{"name":"metapod","url":"https://pokeapi.co/api/v2/pokemon-species/11/"},{"name":"butterfree","url":"https://pokeapi.co/api/v2/pokemon-species/12/"},{"name":"weedle","url":"https://pokeapi.co/api/v2/pokemon-species/13/"},{"name":"kakuna","url":"https://pokeapi.co/api/v2/pokemon-species/14/"},{"name":"beedrill","url":"https://pokeapi.co/api/v2/pokemon-species/15/"},{"name":"pidgey","url":"https://pokeapi.co/api/v2/pokemon-species/16/"},{"name":"pidgeotto","url":"https://pokeapi.co/api/v2/pokemon-species/17/"},{"name":"pidgeot","url":"https://pokeapi.co/api/v2/pokemon-species/18/"},{"name":"rattata","url":"https://pokeapi.co/api/v2/pokemon-species/19/"},{"name":"raticate","url":"https://pokeapi.co/api/v2/pokemon-species/20/"}]}"""

        //Response of pokemon-species?limit=20&offset=880
        const val LIST_LAST_ELEMENTS = """{"count":898,"next":null,"previous":"https://pokeapi.co/api/v2/pokemon-species/?offset=860&limit=20","results":[{"name":"arctozolt","url":"https://pokeapi.co/api/v2/pokemon-species/881/"},{"name":"dracovish","url":"https://pokeapi.co/api/v2/pokemon-species/882/"},{"name":"arctovish","url":"https://pokeapi.co/api/v2/pokemon-species/883/"},{"name":"duraludon","url":"https://pokeapi.co/api/v2/pokemon-species/884/"},{"name":"dreepy","url":"https://pokeapi.co/api/v2/pokemon-species/885/"},{"name":"drakloak","url":"https://pokeapi.co/api/v2/pokemon-species/886/"},{"name":"dragapult","url":"https://pokeapi.co/api/v2/pokemon-species/887/"},{"name":"zacian","url":"https://pokeapi.co/api/v2/pokemon-species/888/"},{"name":"zamazenta","url":"https://pokeapi.co/api/v2/pokemon-species/889/"},{"name":"eternatus","url":"https://pokeapi.co/api/v2/pokemon-species/890/"},{"name":"kubfu","url":"https://pokeapi.co/api/v2/pokemon-species/891/"},{"name":"urshifu","url":"https://pokeapi.co/api/v2/pokemon-species/892/"},{"name":"zarude","url":"https://pokeapi.co/api/v2/pokemon-species/893/"},{"name":"regieleki","url":"https://pokeapi.co/api/v2/pokemon-species/894/"},{"name":"regidrago","url":"https://pokeapi.co/api/v2/pokemon-species/895/"},{"name":"glastrier","url":"https://pokeapi.co/api/v2/pokemon-species/896/"},{"name":"spectrier","url":"https://pokeapi.co/api/v2/pokemon-species/897/"},{"name":"calyrex","url":"https://pokeapi.co/api/v2/pokemon-species/898/"}]}"""

        //Response of pokemon-species?limit=20&offset=500
        const val LIST_INTERMEDIATE = """{"count":898,"next":"https://pokeapi.co/api/v2/pokemon-species/?offset=520&limit=20","previous":"https://pokeapi.co/api/v2/pokemon-species/?offset=480&limit=20","results":[{"name":"oshawott","url":"https://pokeapi.co/api/v2/pokemon-species/501/"},{"name":"dewott","url":"https://pokeapi.co/api/v2/pokemon-species/502/"},{"name":"samurott","url":"https://pokeapi.co/api/v2/pokemon-species/503/"},{"name":"patrat","url":"https://pokeapi.co/api/v2/pokemon-species/504/"},{"name":"watchog","url":"https://pokeapi.co/api/v2/pokemon-species/505/"},{"name":"lillipup","url":"https://pokeapi.co/api/v2/pokemon-species/506/"},{"name":"herdier","url":"https://pokeapi.co/api/v2/pokemon-species/507/"},{"name":"stoutland","url":"https://pokeapi.co/api/v2/pokemon-species/508/"},{"name":"purrloin","url":"https://pokeapi.co/api/v2/pokemon-species/509/"},{"name":"liepard","url":"https://pokeapi.co/api/v2/pokemon-species/510/"},{"name":"pansage","url":"https://pokeapi.co/api/v2/pokemon-species/511/"},{"name":"simisage","url":"https://pokeapi.co/api/v2/pokemon-species/512/"},{"name":"pansear","url":"https://pokeapi.co/api/v2/pokemon-species/513/"},{"name":"simisear","url":"https://pokeapi.co/api/v2/pokemon-species/514/"},{"name":"panpour","url":"https://pokeapi.co/api/v2/pokemon-species/515/"},{"name":"simipour","url":"https://pokeapi.co/api/v2/pokemon-species/516/"},{"name":"munna","url":"https://pokeapi.co/api/v2/pokemon-species/517/"},{"name":"musharna","url":"https://pokeapi.co/api/v2/pokemon-species/518/"},{"name":"pidove","url":"https://pokeapi.co/api/v2/pokemon-species/519/"},{"name":"tranquill","url":"https://pokeapi.co/api/v2/pokemon-species/520/"}]}"""

        //Response of pokemon-species?limit=20&offset=2000
        const val LIST_OUT_BOUNDS = """{"count":898,"next":null,"previous":"https://pokeapi.co/api/v2/pokemon-species/?offset=1980&limit=20","results":[]}"""

        //Simple correct single element
        const val ELEMENT = "{\"name\":\"bulbasaur\",\"url\":\"https://pokeapi.co/api/v2/pokemon/1/\"}"

        //Element with null props
        const val NULL_CONTENT_ELEMENT = "{\"name\": null,\"url\": null}"

        //Empty element
        const val EMPTY_ELEMENT = "{}"

    }
}