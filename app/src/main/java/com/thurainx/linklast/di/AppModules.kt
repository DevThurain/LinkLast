package com.thurainx.linklast.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.thurainx.linklast.others.Constants.LINK_DATABASE_NAME
import com.thurainx.linklast.persistance.LinkDatabase
import com.thurainx.linklast.persistance.repository.LinkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {
    @Singleton
    @Provides
    fun provideLinkDatabase(app: Application): LinkDatabase =
        Room.databaseBuilder(
            app,
            LinkDatabase::class.java,
            LINK_DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideLinkRepository(db: LinkDatabase) : LinkRepositoryImpl{
        return LinkRepositoryImpl(db.linkDAO)
    }
}