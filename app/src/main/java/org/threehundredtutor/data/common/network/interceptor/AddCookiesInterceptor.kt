package org.threehundredtutor.data.common.network.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.threehundredtutor.data.common.data_source.AccountLocalDataSource

class AddCookiesInterceptor(private val accountLocalDataSource: AccountLocalDataSource) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        builder.addHeader(HEADER_SET_COOKIE, accountLocalDataSource.getCookie().toString())
        return chain.proceed(builder.build())
    }
}