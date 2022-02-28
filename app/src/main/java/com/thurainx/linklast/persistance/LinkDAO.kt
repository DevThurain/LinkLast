package com.thurainx.linklast.persistance

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LinkDAO {
    @Insert
    suspend fun insertLink(linkEntity: LinkEntity)

    @Delete
    suspend fun deleteLink(linkEntity: LinkEntity)

    @Query("SELECT * FROM link_table")
    fun getAllLinks() : Flow<List<LinkEntity>>
}