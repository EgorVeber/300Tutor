package org.threehundredtutor.data.common.data_source

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import org.threehundredtutor.ui_common.EMPTY_STRING
import javax.inject.Inject

class PrivateDataSource @Inject constructor(
    private val context: Context
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
        context.getSharedPreferences(PRIVATE_PREFS_FILE, Context.MODE_PRIVATE).edit().clear()
            .apply()
    }

    companion object {
        private const val PRIVATE_PREFS_FILE = "PRIVATE_PREFS_FILE"

        private const val PREFS_ACCOUNT_LOGIN_KEY = "PREFS_ACCOUNT_LOGIN_KEY"
        private const val PREFS_ACCOUNT_PASSWORD_KEY = "PREFS_ACCOUNT_PASSWORD_KEY"
    }
}