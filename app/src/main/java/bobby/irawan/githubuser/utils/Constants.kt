package bobby.irawan.githubuser.utils

/**
 * Created by bobbyirawan09 on 04/07/20.
 */
object Constants {

    const val TOKEN = "token 349065b3aac58393f0baa535375ff6298f45a5d1"
    const val BASE_URL = "https://api.github.com/"
    const val QUERY_PARAM_SEARCH_USER = "q"
    const val PATH_SEARCH_USER = "search/users"
    const val PATH_USERS = "users/"
    const val PATH_USER_NAME = "username"
    const val PATH_FOLLOWERS = "/followers"
    const val PATH_FOLLOWING = "/following"
    const val HEADER_AUTHORIZATION = "Authorization"
    const val TOKEN_INTERCEPTOR = "TokenInterceptor"
    const val LOGGING_INTERCEPTOR = "LoggingInterceptor"


    sealed class Result {
        data class Success<T>(val data: T?) : Result()
        data class Error(val message: String?) : Result()
    }

}