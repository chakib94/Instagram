package com.taki.instagram.di

import android.app.Application
import androidx.room.Room
import com.taki.instagram.data.cache.UserDatabase
import com.taki.instagram.data.network.UserApi
import com.taki.instagram.data.network.RemoteDataSource

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideTargetApi(
        remoteDataSource: RemoteDataSource
    ): UserApi {
        return remoteDataSource.buildApi(UserApi::class.java)
    }

    /*Room its alibrary for architeture component that makes it easier to use sqlite Databases in our android apps
        gives us compile life safety because it will through a compile time error when the queries are not valid*/
    @Provides
    @Singleton
    fun provideDatabase(app: Application) : UserDatabase =
        Room.databaseBuilder(app, UserDatabase::class.java, "photo_database")
            .build()

}