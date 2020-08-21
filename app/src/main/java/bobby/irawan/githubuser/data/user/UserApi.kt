package bobby.irawan.githubuser.data.user

import bobby.irawan.githubuser.data.user.model.UsersResponse
import bobby.irawan.githubuser.utils.Constants.PATH_SEARCH_USER
import bobby.irawan.githubuser.utils.Constants.QUERY_PARAM_SEARCH_USER
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by bobbyirawan09 on 04/07/20.
 */
interface UserApi {

    @GET(PATH_SEARCH_USER)
    suspend fun getUsersByName(
        @Query(QUERY_PARAM_SEARCH_USER) userKeyword: String
    ): Response<UsersResponse>

}