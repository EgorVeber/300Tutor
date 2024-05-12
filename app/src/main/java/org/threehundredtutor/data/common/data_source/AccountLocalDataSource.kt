package org.threehundredtutor.data.common.data_source

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.gson.Gson
import org.threehundredtutor.domain.account.models.AccountModel
import org.threehundredtutor.ui_common.EMPTY_STRING
import javax.inject.Inject

class AccountLocalDataSource @Inject constructor(
    private val context: Context
) {
    private var lastUpdate = 0L

    private val sharedPreferences =
        context.getSharedPreferences(PRIVATE_PREFS_FILE, Context.MODE_PRIVATE)

    private val encryptedSharedPreferences =
        EncryptedSharedPreferences.create(
            PRIVATE_PREFS_FILE,
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

    fun setAccountAuthorizationInfo(login: String, password: String) {
        encryptedSharedPreferences.edit()
            .putString(PREFS_ACCOUNT_LOGIN_KEY, login)
            .putString(PREFS_ACCOUNT_PASSWORD_KEY, password).apply()
    }

    fun getAccountAuthorizationInfo(): Pair<String, String> {
        val login =
            encryptedSharedPreferences.getString(PREFS_ACCOUNT_LOGIN_KEY, EMPTY_STRING)
        val password =
            encryptedSharedPreferences.getString(PREFS_ACCOUNT_PASSWORD_KEY, EMPTY_STRING)
        return login.orEmpty() to password.orEmpty()
    }

    fun setAccount(account: AccountModel) {
        sharedPreferences
            .edit()
            .putString(PREFS_ACCOUNT_MODEL_KEY, Gson().toJson(account)).apply()
    }

    fun getAccount(): AccountModel {
        return with(
            sharedPreferences
        ) {
            val jsonAccountModel: String =
                getString(PREFS_ACCOUNT_MODEL_KEY, EMPTY_STRING) ?: EMPTY_STRING
            if (jsonAccountModel.isNotEmpty()) {
                try {
                    Gson().fromJson(jsonAccountModel, AccountModel::class.java)
                } catch (e: Exception) {
                    AccountModel.EMPTY
                }
            } else {
                AccountModel.EMPTY
            }
        }
    }

    fun clear() {
        context.getSharedPreferences(PRIVATE_PREFS_FILE, Context.MODE_PRIVATE).edit().clear()
            .apply()
        lastUpdate = 0
    }

    fun updateTimer() {
        lastUpdate = System.currentTimeMillis()
    }

    fun getLastUpdate() = lastUpdate

    companion object {
        private const val PRIVATE_PREFS_FILE = "PRIVATE_PREFS_FILE"

        private const val PREFS_ACCOUNT_LOGIN_KEY = "PREFS_ACCOUNT_LOGIN_KEY"
        private const val PREFS_ACCOUNT_PASSWORD_KEY = "PREFS_ACCOUNT_PASSWORD_KEY"
        private const val PREFS_ACCOUNT_MODEL_KEY = "PREFS_ACCOUNT_MODEL_KEY"
    }
}