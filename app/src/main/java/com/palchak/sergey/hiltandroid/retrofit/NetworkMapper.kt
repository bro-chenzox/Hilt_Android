package com.palchak.sergey.hiltandroid.retrofit

import com.palchak.sergey.hiltandroid.model.Blog
import com.palchak.sergey.hiltandroid.util.EntityMapper
import javax.inject.Inject

class NetworkMapper
@Inject constructor() : EntityMapper<BlogNetworkEntity, Blog> {
    override fun mapFromEntity(entity: BlogNetworkEntity): Blog {
        return Blog(
            entity.id,
            entity.title,
            entity.body,
            entity.image,
            entity.category
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogNetworkEntity {
        return BlogNetworkEntity(
            domainModel.id,
            domainModel.title,
            domainModel.body,
            domainModel.image,
            domainModel.category
        )
    }

    fun mapFromEntityList(entities: List<BlogNetworkEntity>): List<Blog> {
        return entities.map {
            mapFromEntity(it)
        }
    }
}