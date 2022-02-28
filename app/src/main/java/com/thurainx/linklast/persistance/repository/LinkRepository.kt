package com.thurainx.linklast.persistance.repository

import com.thurainx.linklast.persistance.LinkEntity
import kotlinx.coroutines.flow.Flow

interface LinkRepository {
    suspend fun insertLink(linkEntity: LinkEntity)

    suspend fun deleteLink(linkEntity: LinkEntity)

    fun getAllLinks() : Flow<List<LinkEntity>>
}