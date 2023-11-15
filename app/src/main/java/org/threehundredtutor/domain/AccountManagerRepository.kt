package org.threehundredtutor.domain

interface AccountManagerRepository {
    fun setAccountInfo(login: String, password: String)
    fun getAccountInfo(): Pair<String, String>
}