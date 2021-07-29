package com.taki.instagram.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.taki.instagram.data.models.Photo
import com.taki.instagram.data.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM photo")
    fun getAllUsers(): Flow<List<Photo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<Photo>)

    @Query("DELETE FROM photo")
    suspend fun deleteAllUsers()
}
