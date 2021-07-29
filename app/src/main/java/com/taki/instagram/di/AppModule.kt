package com.taki.instagram.di

import android.app.Application
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.taki.instagram.R
import com.taki.instagram.data.cache.UserDatabase
import com.taki.instagram.data.network.RemoteDataSource
import com.taki.instagram.data.network.UserApi
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
    ): UserApi = remoteDataSource.buildApi(UserApi::class.java)


    /*Room its alibrary for architecture component that makes it easier to use sqlite Databases in our android apps
       gives us compile life safety because it will through a compile time error when the queries are not valid*/
    @Provides
    @Singleton
    fun provideDatabase(application: Application): UserDatabase =
        Room.databaseBuilder(application, UserDatabase::class.java, "photo_database")
            .build()

    //@Singleton meaning its part of the application level dependencies so all of these objects(Retrofit, RequestOptions...) will exist as long
    //as the application is alive
    @Singleton
    @Provides
    fun provideRequestOptions(): RequestOptions =
        RequestOptions.placeholderOf(R.drawable.placeholder)
                      .error(R.drawable.ic_close)

    //By default dagger is going to look and see whatever the objects you passed through the constructors(exp RequestOptions, Retrofit) and see if that dependency
    //exist anywhere inside the module or inside the other modules in the component(i can change provideGlideInstance method to other modules and it will work)
    @Singleton
    @Provides
    fun provideGlideInstance(application: Application, requestOptions: RequestOptions): RequestManager =
        Glide.with(application)
             .setDefaultRequestOptions(requestOptions)

}