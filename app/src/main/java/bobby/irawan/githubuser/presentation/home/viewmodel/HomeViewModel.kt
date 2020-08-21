package bobby.irawan.githubuser.presentation.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bobby.irawan.githubuser.data.user.UserContract
import bobby.irawan.githubuser.data.user.model.UsersResponse
import bobby.irawan.githubuser.presentation.model.User
import bobby.irawan.githubuser.utils.Constants.Result.Error
import bobby.irawan.githubuser.utils.Constants.Result.Success
import kotlinx.coroutines.launch

/**
 * Created by bobbyirawan09 on 12/07/20.
 */

class HomeViewModel(private val userContract: UserContract, private val usersMapper: UserMapper) :
    ViewModel() {

    private var users = listOf<User>()

    private var _searchResult = MutableLiveData<List<User>>()
    val searchResult: LiveData<List<User>>
        get() = _searchResult

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun getUsersData(keyword: String) {
        viewModelScope.launch {
            val result = userContract.getUser(keyword)
            when (result) {
                is Success<*> -> {
                    users = usersMapper.map(result.data as UsersResponse)
                    _searchResult.postValue(users)
                }
                is Error -> {
                    _errorMessage.postValue(result.message)
                }
            }
        }
    }

}