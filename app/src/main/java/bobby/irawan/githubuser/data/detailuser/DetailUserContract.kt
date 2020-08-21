package bobby.irawan.githubuser.data.detailuser

import bobby.irawan.githubuser.utils.Constants.Result

/**
 * Created by bobbyirawan09 on 04/07/20.
 */
interface DetailUserContract {
    suspend fun getDetailUser(username: String): Result
    suspend fun getDetailUserFollowing(username: String): Result
    suspend fun getDetailUserFollowers(username: String): Result
}