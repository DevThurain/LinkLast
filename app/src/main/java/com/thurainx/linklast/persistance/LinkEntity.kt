package com.thurainx.linklast.persistance

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "link_table")
data class LinkEntity(
    val linkUrl: String?,

){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}