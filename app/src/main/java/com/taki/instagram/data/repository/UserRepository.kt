package com.taki.instagram.data.repository

import androidx.room.withTransaction
import com.taki.instagram.data.cache.UserDatabase
import com.taki.instagram.data.network.UserApi
import com.taki.instagram.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi,
    private val db: UserDatabase
) {
    //automatically be a singleton
    private val userDao = db.userDao()

    fun getUsers() = networkBoundResource(
        //return a flow of list of users
        query = {
            userDao.getAllUsers()
        },
        // get data from rest api
        fetch = {
            delay(2000)
            api.getUsers(16)
        },
        saveFetchResult = { users ->
            db.withTransaction {
                userDao.deleteAllUsers()
                userDao.insertUsers(users)
            }
        }
    )
}