package org.threehundredtutor.data.common.network

enum class ErrorType {
    REGISTRATION_NOT_ENABLED,
    AlreadyAuthenticated,
    EMAIL_SHOULD_BE_SET,
    PHONE_NUMBER_SHOULD_BE_SET,
    USER_EMAIL_ALREADY_EXISTS,
    USER_PHONE_ALREADY_EXISTS,
    USER_MANAGER_ERROR,
    CLIENT_ADDING_ERROR,
    UNACCEPTABLE_PASSWORD,
    NONE
}