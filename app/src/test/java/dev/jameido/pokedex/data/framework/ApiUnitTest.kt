package dev.jameido.pokedex.data.framework

import dev.jameido.pokedex.data.models.PkmnElement
import dev.jameido.pokedex.framework.RetrofitPkmnDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * Created by Jameido on 18/12/2020.
 */
class ApiUnitTest {

    lateinit var server: MockWebServer
    lateinit var mockApi: RetrofitPkmnDataSource

    @Test
    fun getPkmnList() {

        server.enqueue(MockResponse().setBody(LIST_FIRST_ELEMENTS))
        val response = runBlocking {
            mockApi.list(0, 20)
        }

        assertNotNull(response)
        assertEquals(response.count, 1118)
        assertEquals(response.next, "https://pokeapi.co/api/v2/pokemon?offset=20&limit=20")
        assertNull(response.previous)
        assertNotNull(response.results)
        assertEquals(response.results.size, 20)
        assertEquals(response.results[0], PkmnElement("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"))

    }

    @Before
    fun setUp() {
        server = MockWebServer().apply {
            start()
        }
        mockApi = Retrofit.Builder()
                .baseUrl(server.url("/"))
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(RetrofitPkmnDataSource::class.java)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    companion object {
        //Response of pokemon?limit=20&offset=0
        const val LIST_FIRST_ELEMENTS = """{"count":1118,"next":"https://pokeapi.co/api/v2/pokemon?offset=20&limit=20","previous":null,"results":[{"name":"bulbasaur","url":"https://pokeapi.co/api/v2/pokemon/1/"},{"name":"ivysaur","url":"https://pokeapi.co/api/v2/pokemon/2/"},{"name":"venusaur","url":"https://pokeapi.co/api/v2/pokemon/3/"},{"name":"charmander","url":"https://pokeapi.co/api/v2/pokemon/4/"},{"name":"charmeleon","url":"https://pokeapi.co/api/v2/pokemon/5/"},{"name":"charizard","url":"https://pokeapi.co/api/v2/pokemon/6/"},{"name":"squirtle","url":"https://pokeapi.co/api/v2/pokemon/7/"},{"name":"wartortle","url":"https://pokeapi.co/api/v2/pokemon/8/"},{"name":"blastoise","url":"https://pokeapi.co/api/v2/pokemon/9/"},{"name":"caterpie","url":"https://pokeapi.co/api/v2/pokemon/10/"},{"name":"metapod","url":"https://pokeapi.co/api/v2/pokemon/11/"},{"name":"butterfree","url":"https://pokeapi.co/api/v2/pokemon/12/"},{"name":"weedle","url":"https://pokeapi.co/api/v2/pokemon/13/"},{"name":"kakuna","url":"https://pokeapi.co/api/v2/pokemon/14/"},{"name":"beedrill","url":"https://pokeapi.co/api/v2/pokemon/15/"},{"name":"pidgey","url":"https://pokeapi.co/api/v2/pokemon/16/"},{"name":"pidgeotto","url":"https://pokeapi.co/api/v2/pokemon/17/"},{"name":"pidgeot","url":"https://pokeapi.co/api/v2/pokemon/18/"},{"name":"rattata","url":"https://pokeapi.co/api/v2/pokemon/19/"},{"name":"raticate","url":"https://pokeapi.co/api/v2/pokemon/20/"}]}"""

        //Response of pokemon?limit=20&offset=1100
        const val LIST_LAST_ELEMENTS = """{"count":1118,"next":null,"previous":"https://pokeapi.co/api/v2/pokemon?offset=1080&limit=20","results":[{"name":"corviknight-gmax","url":"https://pokeapi.co/api/v2/pokemon/10203/"},{"name":"orbeetle-gmax","url":"https://pokeapi.co/api/v2/pokemon/10204/"},{"name":"drednaw-gmax","url":"https://pokeapi.co/api/v2/pokemon/10205/"},{"name":"coalossal-gmax","url":"https://pokeapi.co/api/v2/pokemon/10206/"},{"name":"flapple-gmax","url":"https://pokeapi.co/api/v2/pokemon/10207/"},{"name":"appletun-gmax","url":"https://pokeapi.co/api/v2/pokemon/10208/"},{"name":"sandaconda-gmax","url":"https://pokeapi.co/api/v2/pokemon/10209/"},{"name":"toxtricity-amped-gmax","url":"https://pokeapi.co/api/v2/pokemon/10210/"},{"name":"centiskorch-gmax","url":"https://pokeapi.co/api/v2/pokemon/10211/"},{"name":"hatterene-gmax","url":"https://pokeapi.co/api/v2/pokemon/10212/"},{"name":"grimmsnarl-gmax","url":"https://pokeapi.co/api/v2/pokemon/10213/"},{"name":"alcremie-gmax","url":"https://pokeapi.co/api/v2/pokemon/10214/"},{"name":"copperajah-gmax","url":"https://pokeapi.co/api/v2/pokemon/10215/"},{"name":"duraludon-gmax","url":"https://pokeapi.co/api/v2/pokemon/10216/"},{"name":"eternatus-eternamax","url":"https://pokeapi.co/api/v2/pokemon/10217/"},{"name":"urshifu-single-strike-gmax","url":"https://pokeapi.co/api/v2/pokemon/10218/"},{"name":"urshifu-rapid-strike-gmax","url":"https://pokeapi.co/api/v2/pokemon/10219/"},{"name":"toxtricity-low-key-gmax","url":"https://pokeapi.co/api/v2/pokemon/10220/"}]}"""

        //Response of pokemon?limit=20&offset=500
        const val LIST_INTERMEDIATE = """{"count":1118,"next":"https://pokeapi.co/api/v2/pokemon?offset=520&limit=20","previous":"https://pokeapi.co/api/v2/pokemon?offset=480&limit=20","results":[{"name":"oshawott","url":"https://pokeapi.co/api/v2/pokemon/501/"},{"name":"dewott","url":"https://pokeapi.co/api/v2/pokemon/502/"},{"name":"samurott","url":"https://pokeapi.co/api/v2/pokemon/503/"},{"name":"patrat","url":"https://pokeapi.co/api/v2/pokemon/504/"},{"name":"watchog","url":"https://pokeapi.co/api/v2/pokemon/505/"},{"name":"lillipup","url":"https://pokeapi.co/api/v2/pokemon/506/"},{"name":"herdier","url":"https://pokeapi.co/api/v2/pokemon/507/"},{"name":"stoutland","url":"https://pokeapi.co/api/v2/pokemon/508/"},{"name":"purrloin","url":"https://pokeapi.co/api/v2/pokemon/509/"},{"name":"liepard","url":"https://pokeapi.co/api/v2/pokemon/510/"},{"name":"pansage","url":"https://pokeapi.co/api/v2/pokemon/511/"},{"name":"simisage","url":"https://pokeapi.co/api/v2/pokemon/512/"},{"name":"pansear","url":"https://pokeapi.co/api/v2/pokemon/513/"},{"name":"simisear","url":"https://pokeapi.co/api/v2/pokemon/514/"},{"name":"panpour","url":"https://pokeapi.co/api/v2/pokemon/515/"},{"name":"simipour","url":"https://pokeapi.co/api/v2/pokemon/516/"},{"name":"munna","url":"https://pokeapi.co/api/v2/pokemon/517/"},{"name":"musharna","url":"https://pokeapi.co/api/v2/pokemon/518/"},{"name":"pidove","url":"https://pokeapi.co/api/v2/pokemon/519/"},{"name":"tranquill","url":"https://pokeapi.co/api/v2/pokemon/520/"}]}"""

        //Response of pokemon?limit=20&offset=2000
        const val LIST_OUT_BOUNDS = """{"count":1118,"next":null,"previous":"https://pokeapi.co/api/v2/pokemon?offset=1980&limit=20","results":[]}"""
    }
}
