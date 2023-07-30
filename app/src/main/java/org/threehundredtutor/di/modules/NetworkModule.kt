package org.threehundredtutor.di.modules

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threehundredtutor.base.network.ServerErrorInterceptor
import org.threehundredtutor.common.BASE_KURSBIO_URL
import org.threehundredtutor.data.authorization.AuthorizationService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class NetworkModule {
    fun createAuthorizationService(): AuthorizationService =
        Retrofit.Builder().baseUrl(BASE_KURSBIO_URL)
            .client(getOkHttp())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(AuthorizationService::class.java)

    private fun getOkHttp() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor(ServerErrorInterceptor())
        .build()
}
