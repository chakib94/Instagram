package com.taki.instagram.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.taki.instagram.data.models.Photo

/*Room its alibrary for architeture component that makes it easier to use sqlite Databases in our android apps
gives us compile life safety because it will through a compile time error when the queries are not valid*/
@Database(entities = [Photo::class], version = 3, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}
