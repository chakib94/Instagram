package com.taki.instagram.utils

import kotlinx.coroutines.flow.*

/* we want to implement the offline cache; we will something we call NetworkBoundResource for that
its a class or function  depending the implementation and its basically coordinates this hole caching process it display the cachedata from db
than it decides if its the time to update the cache if yes than it fetch data from rest api and save it into the sqllite db
and if not will display the cache later*/

/*NetworkBoundResource:  the way it works => we have to pass function arguments to this NetworkBoundResource
function, these  function arguments have different responsibilities one function will be responsible for loading data
from rest api db anther fun will decide if this data from db is stay and has to update or not then will have another fun
which only responsible for loading this new data from the rest api and lastly another fun that takes the data from rest api
and save it into the sql db*/

/*the NetworkBoundResource  fun will coordinates the work between these different functions it will actually call them at the correct
time and in this way we keep our fun reusable because we can later pass different function arguments to it depending
what data we are working with or what caching logic we want to implement*/
inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    //this fun will get one list of users from db
    val data = query().first()

    //check if its time to update this cache if the data is stay or not
    val flow = if (shouldFetch(data)) {
        //emit a value from this flow
        //if its time to update the cache it will first of all emit resource loadings so we can display a progress bar
        emit(Resource.Loading(data))

        try {
            //then it will go ahead make the network request save the result of the network request into sqllite database
            saveFetchResult(fetch())
            //then emit directly from this sqllite db as a single source of truth
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            //if anything goes wrong in the network request it will still emit the same data from the db
            //but it will wrap it into resource error so we know we are looking at the old cache data because somthing went wrong
                //when we try to update it
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        //if shouldFetch returns false then it will just go ahead and display the cache data without making any
            // network request
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}