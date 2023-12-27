package org.threehundredtutor.base.network

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.threehundredtutor.common.utils.AccountManager

class AddCookiesInterceptor(private val accountManager: AccountManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        builder.addHeader(HEADER_SET_COOKIE, accountManager.getCookie().toString())
        return chain.proceed(builder.build())
    }
}