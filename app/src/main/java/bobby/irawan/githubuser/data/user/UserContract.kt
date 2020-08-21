package bobby.irawan.githubuser.data.user

import bobby.irawan.githubuser.utils.Constants.Result

/**
 * Created by bobbyirawan09 on 04/07/20.
 */
interface UserContract {
    suspend fun getUser(userKeyword: String): Result
}