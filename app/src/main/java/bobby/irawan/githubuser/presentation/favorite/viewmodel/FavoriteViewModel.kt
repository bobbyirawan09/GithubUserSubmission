package bobby.irawan.githubuser.presentation.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bobby.irawan.githubuser.AppController
import bobby.irawan.githubuser.R
import bobby.irawan.githubuser.data.favorite.FavoriteService
import bobby.irawan.githubuser.data.favorite.model.FavoriteEntity
import bobby.irawan.githubuser.presentation.model.favorite
import bobby.irawan.githubuser.utils.Constants.Result.Error
import bobby.irawan.githubuser.utils.Constants.Result.Success
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by bobbyirawan09 on 26/07/20.
 */

class FavoriteViewModel(
    private val favoriteService: FavoriteService,
    private val favoriteMapper: FavoriteMapper
) : ViewModel() {

    private var _favoriteResult = MutableLiveData<List<favorite>>()
    val favoriteResult = _favoriteResult as LiveData<List<favorite>>

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage = _errorMessage as LiveData<String>

    private var _successMessage = MutableLiveData<String>()
    val successMessage = _successMessage as LiveData<String>

    init {
        getAllfavorite()
    }

    private fun getAllfavorite() {
        viewModelScope.launch {
            favoriteService.getAllfavorite()
                .catch { e ->
                    _errorMessage.postValue(e.localizedMessage)
                }
                .collect { result ->
                    when (result) {
                        is Success<*> -> {
                            val data = favoriteMapper.map(result.data as List<FavoriteEntity>)
                            _favoriteResult.postValue(data)
                        }
                        is Error -> _errorMessage.postValue(result.message)
                    }
                }
        }
    }

    fun deletefavorite(username: String) {
        viewModelScope.launch {
            val effectedRow = favoriteService.deleteByUsername(username)
            if (effectedRow > 0) {
                _successMessage.postValue(
                    AppController.getInstance().getString(R.string.success_delete_data)
                )
            }
        }
    }
}