package com.taki.instagram.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.taki.instagram.data.models.Photo

@Database(entities = [Photo::class], version = 3, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}
