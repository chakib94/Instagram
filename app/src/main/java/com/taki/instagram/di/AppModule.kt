package com.taki.instagram.di

import UserApi
import android.app.Application
import androidx.room.Room
import com.taki.instagram.data.cache.UserDatabase
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

/*    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApi =
        retrofit.create(UserApi::class.java)*/

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : UserDatabase =
        Room.databaseBuilder(app, UserDatabase::class.java, "user_database")
            .build()

}