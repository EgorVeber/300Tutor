package org.threehundredtutor.domain.account.models

import org.threehundredtutor.common.DEFAULT_NOT_VALID_VALUE_INT
import org.threehundredtutor.common.EMPTY_STRING

data class AccountModel(
    var isAuthenticated: Boolean,
    var userId: String,
    var email: String,
    var roles: ArrayList<String>,
    var avatarFileId: Int,
    var name: String,
    var surname: String,
    var phoneNumber: String,
    var noEmail: Boolean,
    var noPhoneNumber: Boolean
) {
    fun isEmpty(): Boolean = this == EMPTY

    companion object {
        val EMPTY = AccountModel(
            isAuthenticated = false,
            userId = EMPTY_STRING,
            email = EMPTY_STRING,
            roles = arrayListOf(),
            avatarFileId = DEFAULT_NOT_VALID_VALUE_INT,
            name = EMPTY_STRING,
            surname = EMPTY_STRING,
            phoneNumber = EMPTY_STRING,
            noEmail = false,
            noPhoneNumber = false,
        )
    }
}
