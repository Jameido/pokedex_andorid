package dev.jameido.pokedex.framework.datasource.local.mappers

import dev.jameido.pokedex.data.models.RemotePageKey
import dev.jameido.pokedex.framework.datasource.local.models.DbRemotePageKey

/**
 * Created by Jameido on 06/01/2021.
 */
class RemotePageKeyMapper: DbMapper<DbRemotePageKey, RemotePageKey> {
    override fun mapFromDb(dbEntity: DbRemotePageKey) = RemotePageKey(dbEntity.listName, dbEntity.nextPage)

    override fun mapToDb(model: RemotePageKey) = DbRemotePageKey(model.listName, model.nextPage)
}