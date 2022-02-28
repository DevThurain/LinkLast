package com.thurainx.linklast.persistance.repository

import com.thurainx.linklast.persistance.LinkDAO
import com.thurainx.linklast.persistance.LinkEntity
import kotlinx.coroutines.flow.Flow

class LinkRepositoryImpl(private val dao: LinkDAO): LinkRepository{
    override suspend fun insertLink(linkEntity: LinkEntity) {
        dao.insertLink(linkEntity = linkEntity)
    }

    override suspend fun deleteLink(linkEntity: LinkEntity) {
        dao.deleteLink(linkEntity = linkEntity)
    }

    override fun getAllLinks(): Flow<List<LinkEntity>> {
        return dao.getAllLinks()
    }

}