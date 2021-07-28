package com.taki.instagram.utils

import kotlinx.coroutines.flow.*

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