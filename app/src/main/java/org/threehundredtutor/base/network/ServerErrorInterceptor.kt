package org.threehundredtutor.base.network

import okhttp3.Interceptor
import okhttp3.Response

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



// TODO вынести в отдельные классы
open class ServerException : Exception()

class UnauthorizedUserException : ServerException()

class NotFoundServerException : ServerException()

class ForbiddenServerException : ServerException()

class BadRequestException : ServerException()

class InternalServerException : ServerException()

class BadGatewayException : ServerException()

class ServiceUnavailableException : ServerException()

class GatewayTimeoutException : ServerException()

class UnknownServerException : ServerException()



