package dev.jameido.pokedex.framework.datasource.network

import dev.jameido.pokedex.data.datasource.NetworkPkmnDataSource
import dev.jameido.pokedex.data.models.PkmnDetailModel
import dev.jameido.pokedex.data.models.PkmnListModel
import dev.jameido.pokedex.data.models.PkmnSpeciesModel
import dev.jameido.pokedex.framework.datasource.network.mappers.PkmnDetailMapper
import dev.jameido.pokedex.framework.datasource.network.mappers.PkmnMapper
import dev.jameido.pokedex.framework.datasource.network.mappers.PkmnSpeciesMapper

/**
 * Created by Jameido on 04/01/2021.
 */
class NetworkPkmnDataSourceImpl(val api: PkmnApi) : NetworkPkmnDataSource {
    override suspend fun list(pageSize: Int, page: Int): PkmnListModel? {
        val offset = page * pageSize
        val response = api.list(pageSize, offset)
        val pkmnMapper = PkmnMapper()
        return PkmnListModel(response.next?.let { page + 1 }, response.previous?.let { page - 1 }, response.results.map { pkmnMapper.map(it) })
    }

    override suspend fun detail(name: String): PkmnDetailModel? {
        return PkmnDetailMapper().map(api.detail(name))
    }

    override suspend fun species(name: String): PkmnSpeciesModel? {
        return PkmnSpeciesMapper().map(api.species(name))
    }
}