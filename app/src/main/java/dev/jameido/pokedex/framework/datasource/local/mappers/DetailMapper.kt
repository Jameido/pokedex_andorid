package dev.jameido.pokedex.framework.datasource.local.mappers

import dev.jameido.pokedex.data.models.PkmnDetailModel
import dev.jameido.pokedex.data.models.StatModel
import dev.jameido.pokedex.framework.datasource.local.models.*

/**
 * Created by Jameido on 05/01/2021.
 */
class DetailMapper : DbMapper<DbPkmnDetail, PkmnDetailModel> {
    override fun mapFromDb(dbEntity: DbPkmnDetail): PkmnDetailModel {
        val stats = dbEntity.stats.map { StatModel(it.statName, it.value) }
        val type = dbEntity.types.map { it.typeName }
        return PkmnDetailModel(
                dbEntity.detail.id,
                dbEntity.detail.name,
                dbEntity.detail.height,
                dbEntity.detail.weight,
                stats,
                type
        )
    }

    override fun mapToDb(model: PkmnDetailModel): DbPkmnDetail {
        val stats = model.stats.map { DbPkmnStats(model.name, it.name, it.value) }
        val type = model.types.map { DbPkmnTypes(model.name, it) }
        val detail = DbPkmnDetailData(
                model.name,
                model.id,
                model.height,
                model.weight,
        )
        return DbPkmnDetail(detail, stats, type)
    }
}