package com.v_points.app.data.common.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import com.v_points.app.ui_common.util.BadGatewayException
import com.v_points.app.ui_common.util.BadRequestException
import com.v_points.app.ui_common.util.ForbiddenServerException
import com.v_points.app.ui_common.util.GatewayTimeoutException
import com.v_points.app.ui_common.util.InternalServerException
import com.v_points.app.ui_common.util.NotFoundServerException
import com.v_points.app.ui_common.util.ServiceUnavailableException
import com.v_points.app.ui_common.util.UnauthorizedUserException
import com.v_points.app.ui_common.util.UnknownServerException

class ServerErrorInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        return when {
            response.code == 400 -> throw BadRequestException()
            response.code == 401 -> throw UnauthorizedUserException()
            response.code == 403 -> throw ForbiddenServerException()
            response.code == 404 -> throw NotFoundServerException()
            response.code == 500 -> throw InternalServerException()
            response.code == 502 -> throw BadGatewayException()
            response.code == 503 -> throw ServiceUnavailableException()
            response.code == 504 -> throw GatewayTimeoutException()
            response.isSuccessful -> response
            else -> throw UnknownServerException()
        }
    }
}