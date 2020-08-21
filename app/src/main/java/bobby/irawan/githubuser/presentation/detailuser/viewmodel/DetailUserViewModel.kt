package bobby.irawan.githubuser.presentation.detailuser.viewmodel

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bobby.irawan.githubuser.data.detailuser.DetailUserContract
import bobby.irawan.githubuser.data.detailuser.model.DetailUserFollowerResponse
import bobby.irawan.githubuser.data.detailuser.model.DetailUserFollowingResponse
import bobby.irawan.githubuser.data.detailuser.model.DetailUserResponse
import bobby.irawan.githubuser.data.favorite.FavoriteService
import bobby.irawan.githubuser.data.favorite.model.FavoriteEntity
import bobby.irawan.githubuser.presentation.detailuser.view.DetailUserActivity.Companion.EXTRA_USER_NAME
import bobby.irawan.githubuser.presentation.model.DetailUser
import bobby.irawan.githubuser.presentation.model.Follower
import bobby.irawan.githubuser.presentation.model.Following
import bobby.irawan.githubuser.presentation.model.favorite
import bobby.irawan.githubuser.utils.Constants
import kotlinx.coroutines.launch

/**
 * Created by bobbyirawan09 on 10/07/20.
 */
class DetailUserViewModel(
    private val detailUserContract: DetailUserContract,
    private val detailUserMapper: DetailUserMapper,
    private val followerMapper: FollowerMapper,
    private val followingMapper: FollowingMapper,
    private val favoriteService: FavoriteService
) : ViewModel() {

    private var username = ""
    private var detailUser = DetailUser()
    private var isfavorite = false
    private var follower = listOf<Follower>()
    private var following = listOf<Following>()

    private var _detailUserResult = MutableLiveData<DetailUser>()
    val detailUserhResult = _detailUserResult as LiveData<DetailUser>

    private var _followerResult = MutableLiveData<List<Follower>>()
    val followerResult = _followerResult as LiveData<List<Follower>>

    private var _followingResult = MutableLiveData<List<Following>>()
    val followingResult = _followingResult as LiveData<List<Following>>

    private var _errorLoadDetail = MutableLiveData<String>()
    val errorLoadDetail = _errorLoadDetail as LiveData<String>

    private var _errorLoadFollower = MutableLiveData<String>()
    val errorLoadFollower = _errorLoadFollower as LiveData<String>

    private var _errorLoadFollowing = MutableLiveData<String>()
    val errorLoadFollowing = _errorLoadFollowing as LiveData<String>

    private var _favoriteState = MutableLiveData<Boolean>()
    val favoriteState = _favoriteState as LiveData<Boolean>

    fun getDataFromIntent(intent: Intent?) {
        username = intent?.getStringExtra(EXTRA_USER_NAME) ?: ""
    }

    fun getUserFromDatabase() {
        viewModelScope.launch {
            val result = favoriteService.getfavoriteByUsername(username)
            when (result) {
                is Constants.Result.Success<*> -> {
                    val data = result.data as favorite
                    isfavorite = data.username.isNotEmpty()
                }
                is Constants.Result.Error -> {
                    isfavorite = false
                }
            }
            _favoriteState.postValue(isfavorite)
        }
    }

    fun getDetailUserData() {
        viewModelScope.launch {
            val result = detailUserContract.getDetailUser(username)
            when (result) {
                is Constants.Result.Success<*> -> {
                    detailUser = detailUserMapper.map(result.data as DetailUserResponse)
                    _detailUserResult.postValue(detailUser)
                }
                is Constants.Result.Error -> {
                    _errorLoadDetail.postValue(result.message)
                }
            }
        }
    }

    fun getFollowerData() {
        viewModelScope.launch {
            val result = detailUserContract.getDetailUserFollowers(username)
            when (result) {
                is Constants.Result.Success<*> -> {
                    follower = followerMapper.map(result.data as List<DetailUserFollowerResponse>)
                    _followerResult.postValue(follower)
                }
                is Constants.Result.Error -> {
                    _errorLoadFollower.postValue(result.message)
                }
            }
        }
    }

    fun getFollowingData() {
        viewModelScope.launch {
            val result = detailUserContract.getDetailUserFollowing(username)
            when (result) {
                is Constants.Result.Success<*> -> {
                    following =
                        followingMapper.map(result.data as List<DetailUserFollowingResponse>)
                    _followingResult.postValue(following)
                }
                is Constants.Result.Error -> {
                    _errorLoadFollowing.postValue(result.message)
                }
            }
        }
    }

    fun onChangefavoriteState() {
        viewModelScope.launch {
            if (isfavorite) {
                isfavorite = false
                favoriteService.deleteByUsername(detailUser.username.orEmpty())
            } else {
                isfavorite = true
                favoriteService.addfavorite(
                    FavoriteEntity(
                        name = detailUser.name.orEmpty(),
                        username = detailUser.username.orEmpty(),
                        imageUrl = detailUser.imageUrl.orEmpty(),
                        bio = detailUser.bio.orEmpty()
                    )
                )
            }
        }
        _favoriteState.postValue(isfavorite)
    }

}