package org.threehundredtutor.base.network
import okhttp3.Interceptor
import okhttp3.Response
import org.threehundredtutor.common.HEADER_SET_COOKIE
import org.threehundredtutor.common.utils.PrefsCookie

class GetCookiesInterceptor(private val prefsCookie: PrefsCookie) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
        if (response.headers(HEADER_SET_COOKIE).isNotEmpty()) {
            val cookies = HashSet<String>()
            for (header in response.headers(HEADER_SET_COOKIE)) {
                cookies.add(header)
            }
            prefsCookie.setCookie(cookies)
        }
        return response
    }
}