package org.threehundredtutor.data.core

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.threehundredtutor.BuildConfig
import org.threehundredtutor.base.network.AddCookiesInterceptor
import org.threehundredtutor.base.network.GetCookiesInterceptor
import org.threehundredtutor.base.network.ServerErrorInterceptor
import org.threehundredtutor.common.utils.AccountManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.reflect.KClass

class ServiceGenerator @Inject constructor(
    accountManager: AccountManager
) : ServiceGeneratorProvider {

    private val retrofit: Retrofit

    init {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val builder = OkHttpClient.Builder()
            .addInterceptor(logging)
            .cookieJar(JavaNetCookieJar(CookieManager()))
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(ServerErrorInterceptor())
            .addInterceptor(GetCookiesInterceptor(accountManager))
            .addInterceptor(AddCookiesInterceptor(accountManager))

        val okHttpClient = builder.build()

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    override fun <T : Any> getService(serviceClass: KClass<T>): T =
        retrofit.create(serviceClass.java)
}