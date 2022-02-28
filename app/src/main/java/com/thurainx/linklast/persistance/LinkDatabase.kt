package com.thurainx.linklast.persistance

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LinkEntity::class], version = 1)
abstract class LinkDatabase : RoomDatabase(){
    abstract val linkDAO : LinkDAO

}