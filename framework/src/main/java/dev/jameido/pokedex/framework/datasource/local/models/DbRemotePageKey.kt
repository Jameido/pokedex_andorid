package dev.jameido.pokedex.framework.datasource.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Jameido on 06/01/2021.
 */
@Entity(tableName = "remote_page_key")
class DbRemotePageKey(
        @PrimaryKey
        val listName: String,
        val nextPage: Int?
)