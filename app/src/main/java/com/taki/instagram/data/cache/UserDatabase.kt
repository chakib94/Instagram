package com.taki.instagram.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.taki.instagram.data.models.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}
