package bobby.irawan.githubuser.data.detailuser

import bobby.irawan.githubuser.utils.Constants.Result
import bobby.irawan.githubuser.utils.convertResponseApi

/**
 * Created by bobbyirawan09 on 04/07/20.
 */
class DetailUserImpl(private val api: DetailUserApi) : DetailUserContract {
    override suspend fun getDetailUser(username: String): Result =
        try {
            api.getDetailUsers(username).convertResponseApi()
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }

    override suspend fun getDetailUserFollowing(username: String): Result =
        try {
            api.getDetailUserFollowing(username).convertResponseApi()
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }

    override suspend fun getDetailUserFollowers(username: String): Result =
        try {
            api.getDetailUserFollower(username).convertResponseApi()
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
}
