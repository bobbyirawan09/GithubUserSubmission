package bobby.irawan.githubuser.data.detailuser

import bobby.irawan.githubuser.data.detailuser.model.DetailUserFollowerResponse
import bobby.irawan.githubuser.data.detailuser.model.DetailUserFollowingResponse
import bobby.irawan.githubuser.data.detailuser.model.DetailUserResponse
import bobby.irawan.githubuser.utils.Constants
import bobby.irawan.githubuser.utils.Constants.PATH_USER_NAME
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by bobbyirawan09 on 04/07/20.
 */
interface DetailUserApi {

    @GET(Constants.PATH_USERS + "{" + PATH_USER_NAME + "}")
    suspend fun getDetailUsers(
        @Path(PATH_USER_NAME) username: String
    ): Response<DetailUserResponse>

    @GET(Constants.PATH_USERS + "{" + PATH_USER_NAME + "}" + Constants.PATH_FOLLOWERS)
    suspend fun getDetailUserFollower(
        @Path(PATH_USER_NAME) username: String
    ): Response<List<DetailUserFollowerResponse>>

    @GET(Constants.PATH_USERS + "{" + PATH_USER_NAME + "}" + Constants.PATH_FOLLOWING)
    suspend fun getDetailUserFollowing(
        @Path(PATH_USER_NAME) username: String
    ): Response<List<DetailUserFollowingResponse>>

}