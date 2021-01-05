package dev.jameido.pokedex.framework.datasource.local

import dev.jameido.pokedex.data.datasource.LocalPkmnDataSource
import dev.jameido.pokedex.data.models.PkmnDetailModel
import dev.jameido.pokedex.data.models.PkmnListModel
import dev.jameido.pokedex.data.models.PkmnSpeciesModel

/**
 * Created by Jameido on 05/01/2021.
 */
class LocalPkmnDataSourceImpl(database: PkmnDatabase) : LocalPkmnDataSource {
    override suspend fun list(pageSize: Int, page: Int): PkmnListModel {
        TODO("Not yet implemented")
    }

    override suspend fun detail(name: String): PkmnDetailModel {
        TODO("Not yet implemented")
    }

    override suspend fun species(name: String): PkmnSpeciesModel {
        TODO("Not yet implemented")
    }

}