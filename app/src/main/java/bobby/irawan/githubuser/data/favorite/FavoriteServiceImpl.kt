package bobby.irawan.githubuser.data.favorite

import bobby.irawan.githubuser.data.favorite.model.FavoriteEntity
import bobby.irawan.githubuser.presentation.model.favorite
import bobby.irawan.githubuser.utils.Constants.Result
import bobby.irawan.githubuser.utils.Constants.Result.Error
import bobby.irawan.githubuser.utils.Constants.Result.Success
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

/**
 * Created by bobbyirawan09 on 26/07/20.
 */
class FavoriteServiceImpl(private val favoriteDao: FavoriteDao) : FavoriteService {
    override suspend fun getAllfavorite() = flow {
        try {
            favoriteDao.getAllfavorite().collect { favoriteEntities ->
                emit(Success(favoriteEntities))
            }
        } catch (e: Exception) {
            emit(Error(e.localizedMessage))
        }
    }

    override suspend fun getfavoriteByUsername(username: String): Result =
        try {
            val result = favoriteDao.getfavoriteByUsername(username)
            Success(favorite.from(result))
        } catch (e: Exception) {
            Error(e.localizedMessage)
        }

    override suspend fun addfavorite(favoriteEntity: FavoriteEntity) =
        favoriteDao.addfavorite(favoriteEntity)

    override suspend fun deleteByUsername(username: String): Int =
        favoriteDao.deleteByUsername(username)

}