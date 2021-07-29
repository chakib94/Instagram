package com.taki.instagram.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.taki.instagram.data.models.Photo
import com.taki.instagram.data.models.User
import kotlinx.coroutines.flow.Flow

//Dao its an interface where we need to declare methods for all differant db operations we wat tp ake on db CRUD
@Dao
interface UserDao {

    @Query("SELECT * FROM photo")
    fun getAllUsers(): Flow<List<Photo>>

    //(onConflict = OnConflictStrategy.REPLACE : metadata  defines what we should do when we try to insert a Photo that has the same id in this case well just replace it
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<Photo>)

    @Query("DELETE FROM photo")
    suspend fun deleteAllUsers()
}
