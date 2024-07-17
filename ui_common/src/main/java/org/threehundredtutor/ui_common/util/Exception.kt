package org.threehundredtutor.ui_common.util

import java.io.IOException

open class ServerException : IOException()

class UnauthorizedUserException : ServerException()

class NotFoundServerException : ServerException()

class ForbiddenServerException : ServerException()

class BadRequestException : ServerException()

class InternalServerException : ServerException()

class BadGatewayException : ServerException()

class ServiceUnavailableException : ServerException()

class GatewayTimeoutException : ServerException()

class UnknownServerException : ServerException()
