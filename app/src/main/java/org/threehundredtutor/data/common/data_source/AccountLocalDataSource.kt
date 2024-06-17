package org.threehundredtutor.data.common.data_source

import android.util.Log
import org.threehundredtutor.domain.account.models.AccountModel
import javax.inject.Inject

class AccountLocalDataSource @Inject constructor() {
    private var lastUpdate = 0L

    private var accountModel: AccountModel = AccountModel.EMPTY

    init {
        Log.d("LocalDataSource", "AccountLocalDataSource" + this.toString())
    }

    fun setAccount(account: AccountModel) {
        accountModel = account
    }

    fun getAccount(): AccountModel = accountModel

    fun clearAccount() {
        accountModel = AccountModel.EMPTY
        lastUpdate = 0
    }

    fun updateTimer() {
        lastUpdate = System.currentTimeMillis()
    }

    fun getLastUpdate() = lastUpdate
}