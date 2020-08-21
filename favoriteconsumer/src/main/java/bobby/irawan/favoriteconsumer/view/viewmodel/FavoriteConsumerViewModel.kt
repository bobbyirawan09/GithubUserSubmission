package bobby.irawan.githubuser.presentation.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import bobby.irawan.favoriteconsumer.AppFavoriteController
import bobby.irawan.favoriteconsumer.R
import bobby.irawan.favoriteconsumer.helper.MappingHelper
import bobby.irawan.favoriteconsumer.model.Favorite
import bobby.irawan.githubuser.utils.Constants.CONTENT_URI

/**
 * Created by bobbyirawan09 on 26/07/20.
 */

class FavoriteConsumerViewModel : ViewModel() {

    private val contentResolver = AppFavoriteController.getInstance().contentResolver

    private var _favoriteResult = MutableLiveData<List<Favorite>>()
    val favoriteResult = _favoriteResult as LiveData<List<Favorite>>

    private var _errorMessage = MutableLiveData<String>()
    val errorMessage = _errorMessage as LiveData<String>

    fun getAllfavorite() {
        val cursor = contentResolver.query(CONTENT_URI, null, null, null, null)
        if (cursor != null) {
            _favoriteResult.postValue(MappingHelper.mapCursorToList(cursor))
            cursor.close()
        } else {
            _errorMessage.postValue(
                AppFavoriteController.getInstance().getString(R.string.favorite_not_found)
            )
        }
    }
}