package com.taki.instagram.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.taki.instagram.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*for viewmodels we need to inject the dependencies into the constructor and for that we will need a viewmodel factory
its a special class that we need to declare and we dont want to do that with old dagger
With Hilt it actually make that for us*/

/*the reason that we have @inject in constructor in viewmodel and not in activities/fragments its because the android system is
responsible for instantiating activities whenever call an activity constructor also we cant pass any argument to it*/

//Thanks to @HiltViewModel annotation dagger hilt will write this viewModel factory behind the scenes for us
@HiltViewModel
class UserViewModel @Inject constructor(
    repository: UserRepository
) : ViewModel() {
/*    private val usersLiveData = MutableLiveData<List<Photo>>()
    val users: LiveData<List<Photo>> = usersLiveData*/

    //return flow that we get back into liveData
    //Flow is more flexible than liveData but liveData is easier to use between the viewModel and ui layer
    //by turning this flow into liveData we d'ont have to lunch a coroutine anymore to collect it this is already handled
    //for us and we also don't have to handle the lifecycle manually this is also done by liveData internally
    val users = repository.getUsers().asLiveData()

/*    init {
        viewModelScope.launch {
            val users = api.getUsers()
            delay(2000)
            usersLiveData.value = users
        }
    }*/
}