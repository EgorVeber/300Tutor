package org.threehundredtutor.ui_common.util

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