package org.threehundredtutor.domain

interface AccountManagerRepository {
    fun setAccountInfo(login: String, password: String, userId: String)
    fun getAccountInfo(): Triple<String, String, String>
}