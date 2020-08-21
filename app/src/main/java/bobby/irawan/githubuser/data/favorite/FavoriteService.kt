package bobby.irawan.githubuser.data.favorite

import bobby.irawan.githubuser.data.favorite.model.FavoriteEntity
import bobby.irawan.githubuser.utils.Constants.Result
import kotlinx.coroutines.flow.Flow

/**
 * Created by bobbyirawan09 on 26/07/20.
 */
interface FavoriteService {
    suspend fun getAllfavorite(): Flow<Result>
    suspend fun getfavoriteByUsername(username: String): Result
    suspend fun addfavorite(favoriteEntity: FavoriteEntity)
    suspend fun deleteByUsername(username: String): Int
}