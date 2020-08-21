package bobby.irawan.githubuser.data.interceptor

import bobby.irawan.githubuser.utils.Constants.HEADER_AUTHORIZATION
import bobby.irawan.githubuser.utils.Constants.TOKEN
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by bobbyirawan09 on 06/07/20.
 */
class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader(
                    HEADER_AUTHORIZATION, TOKEN
                )
                .build()
        )
    }
}