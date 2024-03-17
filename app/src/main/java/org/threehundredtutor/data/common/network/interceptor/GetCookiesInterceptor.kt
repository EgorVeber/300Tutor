package org.threehundredtutor.data.common.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import org.threehundredtutor.data.common.data_source.AccountLocalDataSource

const val HEADER_SET_COOKIE = "Set-Cookie"

class GetCookiesInterceptor(private val accountLocalDataSource: AccountLocalDataSource) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        if (response.headers(HEADER_SET_COOKIE).isNotEmpty()) {
            val cookies = HashSet<String>()
            for (header in response.headers(HEADER_SET_COOKIE)) {
                cookies.add(header)
            }
            accountLocalDataSource.setCookie(cookies)
        }
        return response
    }
}