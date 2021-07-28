package com.taki.instagram.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.taki.instagram.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    repository: UserRepository
) : ViewModel() {
/*    private val usersLiveData = MutableLiveData<List<Photo>>()
    val users: LiveData<List<Photo>> = usersLiveData*/

    //return flow that we get back into livedata
    //Flow is more flexible than livedata but livedata is easier to use between the viewmodel and ui layer
    //by turning this flow into livedata we dont have to lunch a coroutine anymore to collect it this is already handled
    //for us and we also dont have to handle the lifecycle manually this is also done by livedata internally
    val users = repository.getUsers().asLiveData()


/*    init {
        viewModelScope.launch {
            val users = api.getUsers()
            delay(2000)
            usersLiveData.value = users
        }
    }*/
}