package bobby.irawan.githubuser.data.user

import bobby.irawan.githubuser.utils.Constants.Result
import bobby.irawan.githubuser.utils.convertResponseApi

/**
 * Created by bobbyirawan09 on 04/07/20.
 */
class UserImpl(private val api: UserApi) : UserContract {
    override suspend fun getUser(userKeyword: String): Result {
        return try {
            api.getUsersByName(userKeyword).convertResponseApi()
        } catch (e: Exception) {
            Result.Error(e.localizedMessage)
        }
    }
}