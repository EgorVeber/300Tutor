package org.threehundredtutor.data.common.data_source

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.threehundredtutor.domain.common.AuthorizationInfoAndDomainModel
import org.threehundredtutor.ui_common.EMPTY_STRING
import javax.inject.Inject

class PrivateDataSource @Inject constructor(
    context: Context
) {
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

    fun getAccountAuthorizationInfo(): Pair<String, String> =
        encryptedSharedPreferences.getString(PREFS_ACCOUNT_LOGIN_KEY, EMPTY_STRING).orEmpty() to
                encryptedSharedPreferences.getString(PREFS_ACCOUNT_PASSWORD_KEY, EMPTY_STRING)
                    .orEmpty()

    fun clearAuthorizationInfo() {
        encryptedSharedPreferences.edit()
            .putString(PREFS_ACCOUNT_LOGIN_KEY, "")
            .putString(PREFS_ACCOUNT_PASSWORD_KEY, "").apply()
    }

    fun setAuthDomainInfo(list: List<AuthorizationInfoAndDomainModel>) {
        encryptedSharedPreferences.edit().putString(PREFS_ACCOUNTS_INFO_KEY, Gson().toJson(list))
            .apply()
    }

    fun getAccountsAuthInfo(): List<AuthorizationInfoAndDomainModel> {
        val jsonAccountsList: String =
            encryptedSharedPreferences.getString(PREFS_ACCOUNTS_INFO_KEY, "") ?: ""
        runCatching {
            return if (jsonAccountsList.isNotEmpty()) {
                val type = object : TypeToken<List<AuthorizationInfoAndDomainModel?>?>() {}.type
                Gson().fromJson(jsonAccountsList, type)
            } else {
                listOf()
            }
        }.getOrElse { return listOf() }
    }

    companion object {
        private const val PRIVATE_PREFS_FILE = "PRIVATE_PREFS_FILE"

        private const val PREFS_ACCOUNT_LOGIN_KEY = "PREFS_ACCOUNT_LOGIN_KEY"
        private const val PREFS_ACCOUNT_PASSWORD_KEY = "PREFS_ACCOUNT_PASSWORD_KEY"

        private const val PREFS_ACCOUNTS_INFO_KEY = "PREFS_ACCOUNTS_INFO_KEY"
    }
}